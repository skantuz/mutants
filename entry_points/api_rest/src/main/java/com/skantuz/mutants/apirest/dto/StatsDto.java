package com.skantuz.mutants.apirest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skantuz.mutants.model.stats.Stats;
import lombok.Getter;

@Getter
public class StatsDto {

    @JsonProperty("count_mutant_dna")
    private Integer countMutantDna;
    @JsonProperty("count_human_dna")
    private Integer countHumanDna;
    @JsonFormat(pattern = "^[0-9]*.[0-9]{1}")
    private Double ratio;

    public StatsDto(Stats stats) {
        this.countHumanDna = stats.getCountHumanDna().intValue();
        this.countMutantDna = stats.getCountMutantDna().intValue();
        this.ratio = stats.getRatio();
    }

}
