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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
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
            Collection<? extends GrantedAuthority> roles = user.getAuthorities();
            return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token,checkUserAuthorities(roles)));
        }else{
            throw new NotAuthorizedException("Bad credentials!");
        }
    }

    public ResponseEntity<User> register (@RequestBody @Valid RegisterDTO registerDto) throws AuthenticationException{
        Optional<UserDetails> user = this.repository.findByLogin(registerDto.login());
        if (user.isPresent()) throw new AuthenticationException("User for this login is taken!");

        String encryptedPassword = passwordEncoder.encode(registerDto.password());
        User newUser = new User(registerDto.login(), encryptedPassword, registerDto.role());
        this.repository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    private String checkUserAuthorities(Collection<? extends GrantedAuthority> roles){
        if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return "ADMIN";
        } else if (roles.contains(new SimpleGrantedAuthority("ROLE_RECRUITER"))) {
            return "RECRUITER";
        }
        return "CANDIDATE";
    }
}
