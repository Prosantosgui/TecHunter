package com.prosantosgui.techunter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String ex){super(ex);}

    @Serial
    private static final long serialVersionUID = 1L;
}
