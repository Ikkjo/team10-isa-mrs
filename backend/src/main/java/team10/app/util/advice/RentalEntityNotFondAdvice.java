package team10.app.util.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import team10.app.util.exceptions.RentalEntityNotFoundException;

@ControllerAdvice
public class RentalEntityNotFondAdvice {

    @ResponseBody
    @ExceptionHandler(RentalEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String rentalEntityNotFoundHandler(RentalEntityNotFoundException ex) { return ex.getMessage(); }
}
