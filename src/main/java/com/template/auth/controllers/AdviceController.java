package com.template.auth.controllers;

import com.template.auth.dto.ErroDto;
import com.template.auth.exceptions.RequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class AdviceController {
    Logger logger = LoggerFactory.getLogger(AdviceController.class);
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErroDto> runtimeExceptionHandler(RuntimeException ex){
        logger.error(ex.getMessage());
        ErroDto error = ErroDto.builder().code("P-500").message("Ups: a error has ocurred").build();
        return  new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErroDto>  requestExceptionHandler(RequestException ex){
        ErroDto error = ErroDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return  new ResponseEntity<>(error, ex.getHttpStatus());
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErroDto>  accessDeniedExceptionHandler(AccessDeniedException ex){
        ErroDto error = ErroDto.builder().code("P-403").message(ex.getMessage()).build();
        return  new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException ex){
        List<ErroDto> errors = new ArrayList<>();
        if (ex.getBindingResult().hasErrors()) {
            ex.getBindingResult().getFieldErrors().forEach(error -> {
                errors.add(ErroDto.builder().message(error.getField() + " " + error.getDefaultMessage()).code("P-400").build());
            });
        }
        return ResponseEntity.unprocessableEntity().body(errors);
    }
}
