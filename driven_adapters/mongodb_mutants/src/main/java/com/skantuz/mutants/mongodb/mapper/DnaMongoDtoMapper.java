package com.skantuz.mutants.mongodb.mapper;

import com.skantuz.mutants.mongodb.dto.DnaMongoDto;
import com.skantuz.mutants.model.dna.Dna;

public class DnaMongoDtoMapper {

    /**
     * mapper DnaMongoDto in Dna Model
     * @param dnaMongoDto - DnaMongoDto
     * @return Dna
     */
    public static Dna getDna(DnaMongoDto dnaMongoDto) {
        return Dna.builder()
                .id(dnaMongoDto.getId())
                .dna(dnaMongoDto.getDna())
                .mutant(dnaMongoDto.getMutant())
                .build();
    }

    /**
     * mapper Dna in DnaMongoDto
     * @param dna
     * @return DnaMongoDto
     */
    public static DnaMongoDto getDnaMongoDto(Dna dna){
        return new DnaMongoDto(dna.getId(), dna.getDna(), dna.getMutant());
    }
}
