package com.vslab.account.utils;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomErrorResponse extends RuntimeException {
    public CustomErrorResponse(){   }

    public CustomErrorResponse(String message) {
        super(message);
    }
}
