package com.skantuz.mutants.mongodb_mutants.repository;

import com.skantuz.mutants.mongodb_mutants.dto.DnaMongoDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface DnaMongoRepository extends ReactiveMongoRepository<DnaMongoDto, String> {

    Mono<Integer> countDnaMongoDtoByMutantTrue();
}
