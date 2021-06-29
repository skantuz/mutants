package com.skantuz.mutants.apirest;

import com.skantuz.mutants.apirest.provider.BodyProvider;
import com.skantuz.mutants.apirest.provider.DnaProvider;
import com.skantuz.mutants.apirest.provider.StatsProvider;
import com.skantuz.mutants.apirest.util.JsonSchemaValidator;
import com.skantuz.mutants.usecase.DnaUseCase;
import com.skantuz.mutants.usecase.StatsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Handler.class, Router.class})
@ActiveProfiles("test")
class ApiRestTest {

    @MockBean
    private DnaUseCase dnaUseCase;

    @MockBean
    private StatsUseCase statsUseCase;

    @MockBean
    private JsonSchemaValidator jsonSchemaValidator;

    @InjectMocks
    private Handler handler;

    @Autowired
    private WebTestClient testClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void validateMutantTrue() {
        when(dnaUseCase.validateMutant(any())).thenReturn(Mono.just(DnaProvider.getDna(true)));
        doNothing().when(jsonSchemaValidator).validateWithJsonSchema(any());

        testClient.post()
                .uri("/mutant/")
                .bodyValue(BodyProvider.bodyOk())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

    @Test
    void validateMutantFalse() {
        when(dnaUseCase.validateMutant(any())).thenReturn(Mono.just(DnaProvider.getDna(false)));
        doNothing().when(jsonSchemaValidator).validateWithJsonSchema(any());

        testClient.post()
                .uri("/mutant/")
                .bodyValue(BodyProvider.bodyOk())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isForbidden()
                .expectBody().isEmpty();
    }

    @Test
    void validateMutantBadRequest() {
        when(dnaUseCase.validateMutant(any())).thenReturn(Mono.just(DnaProvider.getDna(false)));
        doNothing().when(jsonSchemaValidator).validateWithJsonSchema(any());
    }

    @Test
    void stats() {
        when(statsUseCase.getStats()).thenReturn(Mono.just(StatsProvider.getStats(40, 100, 0.4)));

        testClient.get()
                .uri("/stats")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"ratio\":0.4,\"count_mutant_dna\":40,\"count_human_dna\":100}");
    }

}