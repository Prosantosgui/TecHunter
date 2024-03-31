package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.config.security.TokenService;
import com.prosantosgui.techunter.model.user.AuthenticationDTO;
import com.prosantosgui.techunter.model.user.LoginResponseDTO;
import com.prosantosgui.techunter.model.user.RegisterDTO;
import com.prosantosgui.techunter.model.user.User;
import com.prosantosgui.techunter.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

//    @Autowired
//    private ApplicationContext context;

    @Autowired
    UserRepository repository;
    
//    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username).orElseThrow(()-> new NoSuchElementException("No User found for this Username!"));
    }

//    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data){
//        authenticationManager = context.getBean(AuthenticationManager.class);
//
//        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
//        var auth = authenticationManager.authenticate(usernamePassword);
//
//        var token = tokenService.generateToken((User) auth.getPrincipal());
//
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//    }
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data){
        UserDetails user = this.repository.findByLogin(data.login()).orElseThrow(()-> new RuntimeException("User not found!"));
        if (passwordEncoder.matches(data.password(), user.getPassword())) {
            String token = this.tokenService.generateToken((User) user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Object> register (@RequestBody RegisterDTO registerDto){
        Optional<UserDetails> user = this.repository.findByLogin(registerDto.login());
        if (user.isPresent()) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        User newUser = new User(registerDto.login(), encryptedPassword, registerDto.role());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
