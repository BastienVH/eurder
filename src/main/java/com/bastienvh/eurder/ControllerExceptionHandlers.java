package com.bastienvh.eurder;

import com.bastienvh.eurder.exceptions.InvalidCustomerException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandlers {
    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandlers.class);

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        logger.warn("user tried to provide invalid data", exception);
        return exception.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }

    @ExceptionHandler(InvalidCustomerException.class)
    protected void handleInvalidCustomerExceptions(InvalidCustomerException exception,
                                                   HttpServletResponse response) throws IOException {
        logger.warn("searched for user, but non found", exception);
        response.sendError(HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
    }

}
