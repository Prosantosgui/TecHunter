package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.config.security.TokenService;
import com.prosantosgui.techunter.exceptions.AuthenticationException;
import com.prosantosgui.techunter.exceptions.NoSuchElementException;
import com.prosantosgui.techunter.exceptions.NotAuthorizedException;
import com.prosantosgui.techunter.model.user.AuthenticationDTO;
import com.prosantosgui.techunter.model.user.LoginResponseDTO;
import com.prosantosgui.techunter.model.user.RegisterDTO;
import com.prosantosgui.techunter.model.user.User;
import com.prosantosgui.techunter.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username).orElseThrow(()-> new UsernameNotFoundException("No User found for this Username!"));
    }

    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) throws NoSuchElementException, NotAuthorizedException{
        UserDetails user = this.repository.findByLogin(data.login()).orElseThrow(()-> new NoSuchElementException("User not found!"));

        if (passwordEncoder.matches(data.password(), user.getPassword())) {
            String token = this.tokenService.generateToken((User) user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token));
        }else{
            throw new NotAuthorizedException("Bad credentials!");
        }
    }

    public ResponseEntity<String> register (@RequestBody @Valid RegisterDTO registerDto) throws AuthenticationException{
        Optional<UserDetails> user = this.repository.findByLogin(registerDto.login());
        if (user.isPresent()) throw new AuthenticationException("User for this login is taken!");

        String encryptedPassword = passwordEncoder.encode(registerDto.password());
        User newUser = new User(registerDto.login(), encryptedPassword, registerDto.role());
        this.repository.save(newUser);
        return new ResponseEntity<String>("User Created!", HttpStatus.CREATED);
    }
}
