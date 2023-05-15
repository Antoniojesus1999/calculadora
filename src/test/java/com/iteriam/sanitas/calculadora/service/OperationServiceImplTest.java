package com.iteriam.sanitas.calculadora.service;

import com.iteriam.sanitas.calculadora.controllers.impl.CalculatorControllerImpl;
import com.iteriam.sanitas.calculadora.log.Logger;
import com.iteriam.sanitas.calculadora.services.OperationService;
import com.iteriam.sanitas.calculadora.services.impl.OperationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


/**
 * OperationServiceImpl
 */

class OperationServiceImplTest {

    @InjectMocks
    private OperationServiceImpl operationService;

    @BeforeEach
    public void setUp() {
        operationService = new OperationServiceImpl();
    }

    /**
     * runOperation Test
     */
    @Test
    void runOperationAddTest() {

        BigDecimal expected = BigDecimal.valueOf(2.4);
        BigDecimal result = operationService.runOperation(BigDecimal.valueOf(1.2),"+",BigDecimal.valueOf(1.2));
        Assertions.assertEquals(expected,result);
    }

    /**
     * runOperationSub Test
     */
    @Test
    void runOperationSubTest() {
        BigDecimal expected = BigDecimal.valueOf(1.1);
        BigDecimal result = operationService.runOperation(BigDecimal.valueOf(3.3),"-",BigDecimal.valueOf(2.2));
        Assertions.assertEquals(expected,result);
    }


}
