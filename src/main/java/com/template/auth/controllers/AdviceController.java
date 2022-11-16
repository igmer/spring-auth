package com.template.auth.controllers;

import com.template.auth.dto.ErroDto;
import com.template.auth.exceptions.RequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LoggerGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


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
}
