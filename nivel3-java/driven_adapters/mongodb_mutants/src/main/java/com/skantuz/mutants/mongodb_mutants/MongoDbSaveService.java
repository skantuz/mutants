package com.skantuz.mutants.mongodb_mutants;

import com.skantuz.mutants.model.dna.Dna;
import com.skantuz.mutants.mongodb_mutants.mapper.DnaMongoDtoMapper;
import com.skantuz.mutants.mongodb_mutants.repository.DnaMongoRepository;
import com.skantuz.mutants.repository.SaveDnaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class MongoDbSaveService implements SaveDnaRepository {

    private final DnaMongoRepository dnaMongoRepository;

    /**
     * Save In Mutants collection of MongoDb
     * @param dna
     * @return dna save
     */
    @Override
    public Mono<Dna> saveDna(Dna dna) {
        return dnaMongoRepository.save(DnaMongoDtoMapper.getDnaMongoDto(dna))
                .map(DnaMongoDtoMapper::getDna);
    }
}
