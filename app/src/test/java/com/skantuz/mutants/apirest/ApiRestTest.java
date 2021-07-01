package com.skantuz.mutants.apirest;

import com.skantuz.mutants.apirest.provider.DnaMongoDtoProvider;
import com.skantuz.mutants.apirest.util.JsonSchemaValidator;
import com.skantuz.mutants.app.config.ApiRestErrorHandler;
import com.skantuz.mutants.app.config.DrivenAdaptersConfig;
import com.skantuz.mutants.app.config.JsonSchemaConfig;
import com.skantuz.mutants.app.config.MongoReactiveConfig;
import com.skantuz.mutants.app.config.Router;
import com.skantuz.mutants.apirest.provider.BodyProvider;
import com.skantuz.mutants.apirest.provider.DnaProvider;
import com.skantuz.mutants.apirest.provider.StatsProvider;
import com.skantuz.mutants.app.config.UseCaseConfig;
import com.skantuz.mutants.model.config.MutantsErrorCode;
import com.skantuz.mutants.model.config.MutantsException;
import com.skantuz.mutants.mongodb.repository.DnaMongoRepository;
import com.skantuz.mutants.usecase.DnaUseCase;
import com.skantuz.mutants.usecase.StatsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
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
@ContextConfiguration(classes = {Router.class, ApiRestErrorHandler.class, DrivenAdaptersConfig.class,
        UseCaseConfig.class})
@ActiveProfiles("test")
class ApiRestTest {

    @MockBean
    private DnaMongoRepository dnaMongoRepository;

    @MockBean
    private JsonSchemaValidator jsonSchemaValidator;

    @Autowired
    private WebTestClient testClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateMutantTrue() {
        when(dnaMongoRepository.save(Mockito.any())).thenReturn(Mono.just(DnaMongoDtoProvider.getDnaMongoDto()));
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
        when(dnaMongoRepository.save(Mockito.any())).thenReturn(Mono.just(DnaMongoDtoProvider.getDnaMongoDtoFalse()));
        doNothing().when(jsonSchemaValidator).validateWithJsonSchema(any());

        testClient.post()
                .uri("/mutant/")
                .bodyValue(BodyProvider.bodyHuman())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isForbidden()
                .expectBody().isEmpty();
    }

    @Test
    void validateMutantBadRequest() {
        when(dnaMongoRepository.save(Mockito.any())).thenReturn(Mono.just(DnaMongoDtoProvider.getDnaMongoDto()));
        Mockito.doThrow(new MutantsException(MutantsErrorCode.B000,"pruba","prueba")).when(jsonSchemaValidator)
                .validateWithJsonSchema(any());
        testClient.post()
                .uri("/mutant/")
                .bodyValue(BodyProvider.bodyBadRequestInvalidedChart())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().jsonPath("code").exists();
    }

    @Test
    void stats() {
        Mockito.when(dnaMongoRepository.count()).thenReturn(Mono.just(100L));
        Mockito.when(dnaMongoRepository.countDnaMongoDtoByMutantTrue()).thenReturn(Mono.just(40L));

        testClient.get()
                .uri("/stats")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"ratio\":0.4,\"count_mutant_dna\":40,\"count_human_dna\":100}");
    }

}