package com.skantuz.mutants.repository;

import com.skantuz.mutants.model.dna.Dna;

public interface ValidateMutantsRepository {

    Boolean validateMutant(Dna dna);
}
