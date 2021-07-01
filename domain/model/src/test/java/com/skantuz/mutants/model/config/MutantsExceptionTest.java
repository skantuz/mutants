package com.skantuz.mutants.model.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutantsExceptionTest {
    private MutantsException mutantsException;

    @BeforeEach
    void setUp() {
        mutantsException = new MutantsException(MutantsErrorCode.E000,"prueba de log", "prueba de mensaje");
    }


    @Test
    void getStatus() {
        assertEquals(500,mutantsException.getStatus());
    }

    @Test
    void getLog() {
        assertEquals(500,mutantsException.getStatus());
    }

    @Test
    void getCode() {
        assertEquals(500,mutantsException.getStatus());
    }

    @Test
    void getTitle() {
    }
    @Test
    void getMessage() {
    }
}