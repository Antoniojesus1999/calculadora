package com.iteriam.sanitas.calculadora.controllers.exception;

import com.iteriam.sanitas.calculadora.controllers.exception.modelException.OperatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OperatorHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return handleOperatorException(new OperatorException("Operación no soportada para el valor: " + ex.getValue()));

    }

    @ExceptionHandler(value = {OperatorException.class})
    public ResponseEntity<String> handleOperatorException(OperatorException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
