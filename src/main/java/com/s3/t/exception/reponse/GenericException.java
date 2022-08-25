package com.s3.t.exception.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericException extends RuntimeException{
    private  String message;
    private HttpStatus httpStatus;
}
