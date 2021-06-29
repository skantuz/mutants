package com.skantuz.mutants.usecase;

import com.skantuz.mutants.repository.SaveDnaRepository;
import com.skantuz.mutants.repository.ValidateMutantsRepository;
import com.skantuz.mutants.usecase.provider.DnaProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DnaUseCaseTest {

    @Mock
    private SaveDnaRepository saveDnaRepository;

    @Mock
    private ValidateMutantsRepository validateMutantsRepository;

    private DnaUseCase dnaUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
        dnaUseCase = new DnaUseCase(saveDnaRepository, validateMutantsRepository);
    }

    @Test
    void validateMutant() {
        when(validateMutantsRepository.validateMutant(any())).thenReturn(true);
        when(saveDnaRepository.saveDna(any())).thenReturn(Mono.just(DnaProvider.getDna(true)));

        StepVerifier.create(dnaUseCase.validateMutant(DnaProvider.getDna()))
                .expectSubscription()
                .assertNext(dna -> Assertions.assertTrue(dna.getMutant()))
                .verifyComplete();
    }
}