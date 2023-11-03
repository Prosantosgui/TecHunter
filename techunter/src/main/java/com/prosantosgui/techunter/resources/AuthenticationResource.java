package com.prosantosgui.techunter.resources;

import com.prosantosgui.techunter.model.user.AuthenticationDTO;
import com.prosantosgui.techunter.model.user.RegisterDTO;
import com.prosantosgui.techunter.repositories.UserRepository;
import com.prosantosgui.techunter.services.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        return authorizationService.login(data);
       // var token = tokenService.generateToken((User) auth.getPrincipal());

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        return authorizationService.register(data);
    }

}
