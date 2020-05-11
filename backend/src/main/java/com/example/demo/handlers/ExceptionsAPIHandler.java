package com.example.demo.handlers;
import com.example.demo.model.exceptions.NotFoundStoreException;
import com.example.demo.model.exceptions.NotFoundUserException;
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

    @ExceptionHandler({NotFoundStoreException.class})
    public ResponseEntity<String> notFoundStore(Exception exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
