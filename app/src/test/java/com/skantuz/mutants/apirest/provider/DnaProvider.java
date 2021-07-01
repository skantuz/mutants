package com.skantuz.mutants.apirest.provider;

import com.skantuz.mutants.model.dna.Dna;

public class DnaProvider {

    public static Dna getDna(Boolean mutant) {
        return Dna.builder()
                .id("ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG")
                .dna(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"})
                .mutant(mutant)
                .build();
    }
}
