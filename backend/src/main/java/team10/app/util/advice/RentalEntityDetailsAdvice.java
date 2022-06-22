package team10.app.util.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import team10.app.util.exceptions.RentalEntityDetailsException;

@ControllerAdvice
public class RentalEntityDetailsAdvice {

    @ResponseBody
    @ExceptionHandler(RentalEntityDetailsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String rentalEntityDetailsHandler(RentalEntityDetailsException ex) { return ex.getMessage(); }

}
