package com.skantuz.mutants.mongodb.repository;

import com.skantuz.mutants.mongodb.dto.DnaMongoDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface DnaMongoRepository extends ReactiveMongoRepository<DnaMongoDto, String> {

    Mono<Long> countDnaMongoDtoByMutantTrue();
}
