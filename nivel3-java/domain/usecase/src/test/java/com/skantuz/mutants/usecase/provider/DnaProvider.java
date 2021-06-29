package com.skantuz.mutants.usecase.provider;

import com.skantuz.mutants.model.dna.Dna;

import java.util.Arrays;

public class DnaProvider {

    public static Dna getDna(){
        return Dna.builder()
                .id("ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG")
                .dna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"})
                .build();
    }

    public static Dna getDna(Boolean mutant){
        return Dna.builder()
                .id("ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG")
                .dna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"})
                .mutant(mutant)
                .build();
    }
}
