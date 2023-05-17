package com.jwland.jwland.util;

import org.springframework.validation.Errors;

public class ErrorUtil {


    public static void validate(Errors error){

        if(error.hasErrors()){
            throw new IllegalArgumentException(error.getFieldError().getDefaultMessage());
        }
    }
}
