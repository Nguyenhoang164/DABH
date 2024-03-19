package com.example.dabh.ExepcitionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class ExecutionHandler {
@ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorMessage(Exception e){
    System.out.println(e.getMessage());
    return new ResponseEntity<>("your access is denied", HttpStatus.EXPECTATION_FAILED);
}
}
