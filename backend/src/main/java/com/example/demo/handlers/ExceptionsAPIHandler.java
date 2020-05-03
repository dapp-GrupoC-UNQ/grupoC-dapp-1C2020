package com.example.demo.handlers;
import com.example.demo.model.excepciones.NotFoundUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsAPIHandler {

    @ExceptionHandler({ NotFoundUserException.class})
    public ResponseEntity<String> notFoundUser(Exception exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
