package com.skantuz.mutants.mongodb_mutants.provider;

import com.skantuz.mutants.mongodb_mutants.dto.DnaMongoDto;

public class DnaMongoDtoProvider {
    public static DnaMongoDto getDnaMongoDto(){
        return new DnaMongoDto("abc",new String[]{"abcd","abdc","abcd","abdc"},true);
    }
}
