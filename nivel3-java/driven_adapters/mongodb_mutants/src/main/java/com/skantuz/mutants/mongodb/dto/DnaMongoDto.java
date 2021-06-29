package com.skantuz.mutants.mongodb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection=" mutants")
public class DnaMongoDto {

    @Id
    private String id;
    private String[] dna;
    private Boolean mutant;
}
