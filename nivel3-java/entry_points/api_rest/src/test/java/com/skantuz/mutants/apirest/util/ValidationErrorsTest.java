package com.skantuz.mutants.apirest.util;

import com.skantuz.mutants.model.config.MutantsErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationErrorsTest {

    private ValidationErrors validationErrors;

    @BeforeEach
    void setUp() {
        validationErrors = new ValidationErrors();
    }

    @Test
    void addMessage() {
        validationErrors.addMessage();
        assertEquals(MutantsErrorCode.E000,validationErrors.getErrorCode());
    }

}