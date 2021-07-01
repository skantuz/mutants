package com.skantuz.mutants.apirest.provider;

public class BodyProvider {

    public static String bodyOk(){
        return "{\n" +
                "\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n" +
                "}";
    }
    public static String bodyBadRequestInvalidedChart(){
        return "{\n" +
                "\"dna\":[\"ATGCBA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n" +
                "}";
    }
    public static String bodyBadRequestAdditional(){
        return "{\n" +
                "\"dna\":[\"ATGCBA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"],\n" +
                "\"rna\":[\"ATGCBA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n" +
                "}";
    }
    public static String bodyBadRequestInvalidedSize(){
        return "{\n" +
                "\"dna\":[\"ATGCBA\",\"CAGTGC\",\"TTTGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n" +
                "}";
    }

}
