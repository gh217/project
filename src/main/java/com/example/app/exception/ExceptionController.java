package com.example.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<?> notFoundUser(NotFoundUserExc NOT_FOUND){
        return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> notFoundMovie(NotFoundMovieExc NOT_FOUND){
        return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

}
