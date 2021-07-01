package com.skantuz.mutants.model.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutantsErrorCodeTest {

    @Test
    void status() {
        assertEquals(400,MutantsErrorCode.B000.status());
        assertEquals(500,MutantsErrorCode.E000.status());
    }

    @Test
    void errorCode() {
        assertEquals("B000",MutantsErrorCode.B000.errorCode());
    }

    @Test
    void title() {
        assertEquals("Upps esto no deberia ocurrir",MutantsErrorCode.E000.title());
    }

    @Test
    void valueOf() {
        assertEquals("E000",MutantsErrorCode.valueOf("E000").errorCode());
    }

}