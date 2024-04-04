package com.prosantosgui.techunter.exceptions.handler;

import com.prosantosgui.techunter.exceptions.ExceptionResponse;
import com.prosantosgui.techunter.exceptions.AuthenticationException;
import com.prosantosgui.techunter.exceptions.NoSuchElementException;
import com.prosantosgui.techunter.exceptions.NotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
            Exception ex, WebRequest request
    ){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NoSuchElementException.class, UsernameNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleAllNotFoundExceptions(
            Exception ex, WebRequest request
    ){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleAllForbiddenExceptions(
            Exception ex, WebRequest request
    ){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(NotAuthorizedException.class)
    public final ResponseEntity<ExceptionResponse> handleAllUnauthorizedExceptions(
            Exception ex, WebRequest request
    ){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }
}
