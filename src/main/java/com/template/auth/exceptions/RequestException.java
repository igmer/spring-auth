package com.template.auth.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RequestException extends RuntimeException{
    private String code;
    private HttpStatus httpStatus;
    public  RequestException(HttpStatus httpStatus, String code, String message){
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}