package com.example.app.exception;

public class NotFoundMovieExc extends RuntimeException{
    public NotFoundMovieExc(String message){
        super(message);
    }
}
