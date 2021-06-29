package com.skantuz.mutants.mongodb_mutants;

import com.skantuz.mutants.mongodb_mutants.provider.DnaMongoDtoProvider;
import com.skantuz.mutants.mongodb_mutants.provider.DnaProvider;
import com.skantuz.mutants.mongodb_mutants.repository.DnaMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MongoDbSaveServiceTest {

    @Mock
    private DnaMongoRepository dnaMongoRepository;

    private MongoDbSaveService mongoDbSaveService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mongoDbSaveService=new MongoDbSaveService(dnaMongoRepository);
    }

    @Test
    void saveDna() {
        when(dnaMongoRepository.save(Mockito.any())).thenReturn(Mono.just(DnaMongoDtoProvider.getDnaMongoDto()));
        StepVerifier.create(mongoDbSaveService.saveDna(DnaProvider.getDna()))
                .expectSubscription()
                .assertNext(dna -> assertEquals("abc",dna.getId()))
                .verifyComplete();
    }
}