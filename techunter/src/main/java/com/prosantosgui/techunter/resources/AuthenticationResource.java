package com.prosantosgui.techunter.resources;

import com.prosantosgui.techunter.model.user.AuthenticationDTO;
import com.prosantosgui.techunter.model.user.LoginResponseDTO;
import com.prosantosgui.techunter.model.user.RegisterDTO;
import com.prosantosgui.techunter.services.AuthorizationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Bearer Token JWT")
public class AuthenticationResource {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        return authorizationService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        return authorizationService.register(data);
    }

}
