package com.skantuz.mutants.apirest.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skantuz.mutants.apirest.provider.BodyProvider;
import com.skantuz.mutants.model.dna.Dna;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DnaMapperTest {

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void jsonToDna() throws JsonProcessingException {
        JsonNode node = mapper.readTree(BodyProvider.bodyOk());
        Dna dna = DnaMapper.JsonToDna(node);
        String[] checks = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        IntStream.range(0, checks.length).forEach(i -> assertEquals(checks[i], dna.getDna()[i]));
        assertNull(dna.getMutant());
        assertEquals("ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG", dna.getId());
    }
}