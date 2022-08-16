package com.s3.t.exception;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String message){
        super(message);

    }
}
