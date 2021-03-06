package pl.edu.pg.booksharing.Booksharing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class BorrowingControllerExceptionHandler {

    @ExceptionHandler(BorrowedAlreadyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, String> ResourceNotFoundExceptionHandler (BorrowedAlreadyException e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        return response;
    }
}
