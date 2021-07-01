package com.skantuz.mutants.identify_mutants;

import com.skantuz.mutants.model.dna.Dna;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdentifyMutantServiceTest {

    private IdentifyMutantService identifyMutantService;
    private Dna dna;
    @BeforeEach
    void setUp() {
        identifyMutantService=new IdentifyMutantService();
        dna = Dna.builder().build();

    }

    @Test
    void validateMutantHorizontal() {
        String[] data = {"ATGGGG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        dna.setId("asdf");
        dna.setDna(data);
        assertTrue(identifyMutantService.validateMutant(dna));
    }

    @Test
    void validateMutantVertical() {
        String[] data = {"ATGTCG","AAGTGC","ATATGT","AGAAGG","CCGCTA","TCACTG"};
        dna.setId("asdf");
        dna.setDna(data);
        assertTrue(identifyMutantService.validateMutant(dna));
    }
    @Test
    void validateMutantOblique() {
        String[] data = {"ATGTCG","AAGTGC","GAATGT","AAGAGG","CCGCTA","TCACTG"};
        dna.setId("asdf");
        dna.setDna(data);
        assertTrue(identifyMutantService.validateMutant(dna));
    }

    @Test
    void validateMutantHuman() {
        String[] data = {"ATGTCG","AAGTGC","GAATGT","AAAGGG","CCGCTA","TCACTG"};
        dna.setId("asdf");
        dna.setDna(data);
        assertFalse(identifyMutantService.validateMutant(dna));
    }
}