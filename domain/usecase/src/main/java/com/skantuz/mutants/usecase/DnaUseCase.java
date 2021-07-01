package com.skantuz.mutants.usecase;

import com.skantuz.mutants.model.dna.Dna;
import com.skantuz.mutants.repository.SaveDnaRepository;
import com.skantuz.mutants.repository.ValidateMutantsRepository;
import reactor.core.publisher.Mono;

public class DnaUseCase {

    private final SaveDnaRepository saveDnaRepository;
    private final ValidateMutantsRepository validateMutantsRepository;

    public DnaUseCase(SaveDnaRepository saveDnaRepository, ValidateMutantsRepository validateMutantsRepository) {
        this.saveDnaRepository = saveDnaRepository;
        this.validateMutantsRepository = validateMutantsRepository;
    }

    public Mono<Dna> validateMutant(Dna dna){

        dna.setMutant(validateMutantsRepository.validateMutant(dna));
        return saveDnaRepository.saveDna(dna);
    }

}
