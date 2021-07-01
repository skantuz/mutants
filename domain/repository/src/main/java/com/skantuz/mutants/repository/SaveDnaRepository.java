package com.skantuz.mutants.repository;

import com.skantuz.mutants.model.dna.Dna;
import reactor.core.publisher.Mono;

public interface SaveDnaRepository {
    Mono<Dna> saveDna(Dna dna);
}
