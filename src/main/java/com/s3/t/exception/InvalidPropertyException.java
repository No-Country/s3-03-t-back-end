package com.s3.t.exception;

public class InvalidPropertyException extends RuntimeException{

    public InvalidPropertyException(String message){
        super(message);
    }
}
