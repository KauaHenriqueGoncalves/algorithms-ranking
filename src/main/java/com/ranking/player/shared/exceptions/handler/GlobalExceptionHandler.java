package com.ranking.player.shared.exceptions.handler;

import com.ranking.player.shared.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handleNotFound(NotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(
                status.value(),
                "NOT FOUND",
                ex.getMessage()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> handleBadSort(IllegalArgumentException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(
                status.value(),
                "BAD REQUEST",
                ex.getMessage()
        );
        return ResponseEntity.status(status).body(error);
    }
}
