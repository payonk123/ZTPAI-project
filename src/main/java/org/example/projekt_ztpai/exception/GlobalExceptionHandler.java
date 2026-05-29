package org.example.projekt_ztpai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(
                        error.getField(),
                        error.getDefaultMessage())
                );

        Map<String, Object> response = Map.of(
                "message", "Validation error",
                "errors", errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> handleNotFound(NotFound ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
    }


}
