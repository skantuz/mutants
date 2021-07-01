package com.skantuz.mutants.mongodb.provider;

import com.skantuz.mutants.model.dna.Dna;

public class DnaProvider {

    public static Dna getDna(){
        return Dna.builder().id("abc").mutant(true).dna(new String[]{"abc","abc","abc"}).build();
    }
}
