package team10.app.util.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import team10.app.util.exceptions.BusinessClientDetailsException;
import team10.app.util.exceptions.RentalEntityNotFoundException;

@ControllerAdvice
public class BusinessClientDetailsAdvice {

    @ResponseBody
    @ExceptionHandler(BusinessClientDetailsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String businessClientDetailsHandler(BusinessClientDetailsException ex) { return ex.getMessage(); }
}
