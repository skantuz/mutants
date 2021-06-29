package com.skantuz.mutants.usecase;

import com.skantuz.mutants.model.stats.Stats;
import com.skantuz.mutants.repository.StatsRepository;
import com.skantuz.mutants.usecase.provider.StatsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StatsUseCaseTest {

    @Mock
    private StatsRepository statsRepository;

    private StatsUseCase statsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        statsUseCase = new StatsUseCase(statsRepository);

    }

    @Test
    void getStats() {
        Stats statsTest = StatsProvider.getStats(40, 100, 0.4);
        when(statsRepository.getStats()).thenReturn(Mono.just(statsTest));
        StepVerifier.create(statsUseCase.getStats())
                .expectSubscription()
                .assertNext(stats -> assertEquals(statsTest, stats))
                .verifyComplete();
    }
}