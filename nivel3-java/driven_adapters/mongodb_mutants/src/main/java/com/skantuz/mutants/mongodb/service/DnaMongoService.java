package com.skantuz.mutants.mongodb.service;

import com.skantuz.mutants.mongodb.dto.DnaMongoDto;
import com.skantuz.mutants.mongodb.repository.DnaMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DnaMongoService {


    private final DnaMongoRepository dnaMongoRepository;


    public Mono<DnaMongoDto> save(DnaMongoDto dnaMongoDto) {
        return dnaMongoRepository.save(dnaMongoDto);
    }

    public Mono<Long> count() {
        return dnaMongoRepository.count();
    }

    public Mono<Integer> countMutant() {
        return dnaMongoRepository.countDnaMongoDtoByMutantTrue();
    }
}
