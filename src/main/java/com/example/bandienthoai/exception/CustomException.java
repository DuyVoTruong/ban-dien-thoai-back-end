package com.example.bandienthoai.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Getter
@Setter
public class CustomException extends RuntimeException{

    private String message;
    private String status;
    public CustomException(String status, String message){
        super(message);
        this.message = message;
        this.status = status;
    }
    public CustomException(){
        super();
    }
}
