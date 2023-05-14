package com.iteriam.sanitas.calculadora.controllers;

import com.iteriam.sanitas.calculadora.controllers.responses.ResponseBase;
import com.iteriam.sanitas.calculadora.controllers.responses.get.OperationResult;
import com.iteriam.sanitas.calculadora.models.Operator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Operations controller
 */
public interface CalculatorController {

    /**
     * Get operation result
     *
     * @return operation result
     */
    @Operation(
            summary = "Calculadora",
            description = "Calculadora suma y resta")
    @Tag(name = "API CALCULADORA", description = "Operations")
    @GetMapping(value = "/operations", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ResponseBase<OperationResult>> getOperation(
            @RequestParam("operand1") BigDecimal operand1,
            @RequestParam("operator")  Operator operator,
            @RequestParam("operand2") BigDecimal operand2);



}
