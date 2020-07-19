package com.interview.chequer.web;

import com.interview.chequer.domain.exception.AlreadyExistingException;
import com.interview.chequer.domain.exception.NotFoundException;
import com.interview.chequer.domain.exception.UnauthorizedException;
import com.interview.chequer.domain.exception.ValidationException;
import com.interview.chequer.web.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyExistingException.class)
    public ResponseEntity handleAlreadyExistingException(AlreadyExistingException e) {
        return new ResponseEntity(new ErrorResponse(e), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException e) {
        return new ResponseEntity(new ErrorResponse(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity handleUnauthorizedException(UnauthorizedException e) {
        return new ResponseEntity(new ErrorResponse(e), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleValidationException(ValidationException e) {
        return new ResponseEntity(new ErrorResponse(e), HttpStatus.BAD_REQUEST);
    }
}
