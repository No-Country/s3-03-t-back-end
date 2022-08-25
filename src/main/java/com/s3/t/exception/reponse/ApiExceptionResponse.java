package com.s3.t.exception.reponse;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ApiExceptionResponse {
    private String message;
    private int httpStatus;
    private ZonedDateTime timestamp = ZonedDateTime.now();

    public ApiExceptionResponse(String message, int httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
