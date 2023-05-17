package com.iteriam.sanitas.calculadora.controllers;

import com.iteriam.sanitas.calculadora.controllers.constants.ConstantsController;
import com.iteriam.sanitas.calculadora.controllers.exception.model.OperandNullException;
import com.iteriam.sanitas.calculadora.controllers.impl.CalculatorControllerImpl;
import com.iteriam.sanitas.calculadora.controllers.responses.ResponseBase;
import com.iteriam.sanitas.calculadora.controllers.responses.get.OperationResult;
import com.iteriam.sanitas.calculadora.log.Logger;
import com.iteriam.sanitas.calculadora.models.Operator;
import com.iteriam.sanitas.calculadora.services.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * CalculatorController test
 */
@ExtendWith(MockitoExtension.class)
class CalculatorControllerImplTest {
    @InjectMocks
    private CalculatorControllerImpl operationsController;
    @Mock
    private OperationService operationsService;
    @Mock
    private Logger log;

    @BeforeEach
    void setUp() {
        doNothing().when(log).log(anyString());
    }

    /**
     * Test getOperation
     */
    @Test
    void getOperation() {
        when(operationsService.runOperation(any(), any(), any()))
                .thenReturn(BigDecimal.valueOf(2));

        BigDecimal operand1 = BigDecimal.ONE;
        BigDecimal operand2 = BigDecimal.ONE;
        Operator operator = Operator.ADD; // Asumiendo que ADD es un valor válido
        BigDecimal expectedResponse = BigDecimal.valueOf(2);

        Mockito.when(operationsService.runOperation(operand1, operator.getOperatorValue(), operand2))
                .thenReturn(expectedResponse);

        ResponseEntity<ResponseBase<OperationResult>> result = operationsController.getOperation(operand1, operator, operand2);

        Mockito.verify(operationsService, Mockito.times(1)).runOperation(operand1, operator.getOperatorValue(), operand2);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponse, Objects.requireNonNull(result.getBody()).getData().getResult());
    }

    @Test()
    @MockitoSettings(strictness = Strictness.LENIENT)
    void getOperationException() {

        BigDecimal operand1 = null;
        Operator operator = Operator.ADD; // Puedes ajustar el operador según tus necesidades
        BigDecimal operand2 = null;

        OperandNullException exception = assertThrows(OperandNullException.class, () -> {
            operationsController.getOperation(operand1, operator, operand2);
        });

        assertEquals(OperandNullException.class, exception.getClass());
        assertEquals(ConstantsController.ERROR_OPERATOR, exception.getMessage());

    }

}
