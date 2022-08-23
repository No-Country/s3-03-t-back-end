package com.s3.t.exception;

import com.s3.t.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException e){
        ErrorResponse errorResponse = buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "Entity not found.",
                e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException e){
        ErrorResponse errorResponse = buildErrorResponse(
                HttpStatus.CONFLICT,
                e.getMessage(),
                "The server could not complete the user registration because "
                        + "the email address entered is already in use.");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException e){
        ErrorResponse errorResponse = buildErrorResponse(
                HttpStatus.UNAUTHORIZED,
                e.getMessage(),
                "The server cannot return a response due to invalid credentials.");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);

    }

    private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message,
                                             List<String> moreInfo) {
        return ErrorResponse.builder()
                .statusCode(String.valueOf(httpStatus.value()))
                .message(message)
                .moreInfo(moreInfo)
                .build();
    }

    private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message, String moreInfo) {
        return buildErrorResponse(httpStatus, message, List.of(moreInfo));
    }
}
