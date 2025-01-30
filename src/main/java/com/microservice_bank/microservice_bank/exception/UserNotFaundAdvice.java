package com.microservice_bank.microservice_bank.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserNotFaundAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFaundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(UserNotFaundException exception){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());

        return errorMap;
    }
}

