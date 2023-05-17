package com.iteriam.sanitas.calculadora.controllers.exception.modelException;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class OperatorException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8852049938063608993L;

    public OperatorException(String message) {
        super(message);
    }
}
