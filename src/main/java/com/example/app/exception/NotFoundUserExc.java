package com.example.app.exception;

public class NotFoundUserExc extends RuntimeException{
    public NotFoundUserExc(String message){
        super(message);
    }
}
