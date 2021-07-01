package com.skantuz.mutants.mongodb;

import com.skantuz.mutants.mongodb.repository.DnaMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MongoDbStatsServiceTest {

    @Mock
    private DnaMongoRepository dnaMongoRepository;

    private MongoDbStatsService mongoDbStatsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mongoDbStatsService = new MongoDbStatsService(dnaMongoRepository);
    }

    @Test
    void getStats() {
        Mockito.when(dnaMongoRepository.count()).thenReturn(Mono.just(100L));
        Mockito.when(dnaMongoRepository.countDnaMongoDtoByMutantTrue()).thenReturn(Mono.just(40L));

        StepVerifier.create(mongoDbStatsService.getStats())
                .expectSubscription()
                .assertNext(stats -> assertEquals(0.4, stats.getRatio()))
                .verifyComplete();
    }

    @Test
    void getStatsCache() {
        Mockito.when(dnaMongoRepository.count()).thenReturn(Mono.just(100L).delayElement(Duration.ofMillis(100)));
        Mockito.when(dnaMongoRepository.countDnaMongoDtoByMutantTrue()).thenReturn(Mono.just(40L)
                .delayElement(Duration.ofMillis(500)));
        assertTrue(500 < getDuration().toMillis());
        assertTrue(10 > getDuration().toMillis());
        assertTrue(10 > getDuration().toMillis());
        assertTrue(10 > getDuration().toMillis());
    }

    private Duration getDuration() {
        Duration duration = StepVerifier.create(mongoDbStatsService.getStats())
                .expectSubscription()
                .assertNext(stats -> assertEquals(0.4, stats.getRatio()))
                .verifyComplete();
        System.out.println(duration.toMillis());
        return duration;
    }
}