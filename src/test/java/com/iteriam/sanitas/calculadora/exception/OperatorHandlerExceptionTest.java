package com.iteriam.sanitas.calculadora.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OperatorHandlerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMethodArgumentTypeMismatchException() throws Exception {
        mockMvc.perform(get("/operations")
                        .param("operand1", "100")
                        .param("operator", "valor-incorrecto-para-operator") // Valor incorrecto para causar la excepción
                        .param("operand2", "200")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(content().string("Operación no soportada para el valor: valor-incorrecto-para-operator")); // Esperamos un estado HTTP 400 porque el handler retorna HttpStatus.BAD_REQUEST
    }
}