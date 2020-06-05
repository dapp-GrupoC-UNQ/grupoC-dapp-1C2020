package com.example.demo.handlers;
import com.example.demo.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.demo.model.exceptions.NotAvailableUserNameException;
import com.example.demo.model.exceptions.NotFoundStoreException;
import com.example.demo.model.exceptions.NotFoundUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestControllerAdvice
@EnableWebMvc
public class ExceptionsAPIHandler {

    @ExceptionHandler({ NotFoundUserException.class})
    public ResponseEntity<String> notFoundUser(Exception exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotFoundStoreException.class})
    public ResponseEntity<String> notFoundStore(Exception exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidUsernameOrPasswordException.class})
    public ResponseEntity<String> invalidUsernameOrPassword(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotAvailableUserNameException.class})
    public ResponseEntity<String> notAvailableUserName(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
