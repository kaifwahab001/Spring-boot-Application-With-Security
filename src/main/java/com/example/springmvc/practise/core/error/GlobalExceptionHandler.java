package com.example.springmvc.practise.core.error;

import com.example.springmvc.practise.core.exceptions.ApiError;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// this the way using for providing the global exception through controllerAdvice


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handelUserNotFound(UsernameNotFoundException ex ){
        ApiError error = new ApiError("UserName not found with username : "+ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error,error.getStatus());
    }


    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> jwtException(JwtException ex ){
        ApiError error = new ApiError("Invalid Jwt Exception : "+ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error,error.getStatus());
    }
}
