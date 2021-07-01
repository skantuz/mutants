package com.skantuz.mutants.apirest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.skantuz.mutants.apirest.provider.BodyProvider;
import com.skantuz.mutants.model.config.MutantsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonSchemaValidatorTest {
    private ObjectMapper mapper;
    private JsonSchemaValidator jsonSchemaValidator;

    @BeforeEach
    void setUp() throws IOException, ProcessingException {
        mapper = new ObjectMapper();
        var factory = JsonSchemaFactory.byDefault();
        var json = JsonLoader.fromResource("/schemas/mutant_schema.json");
        jsonSchemaValidator = new JsonSchemaValidator(factory.getJsonSchema(json));
    }

    @Test
    void validateWithJsonSchemaOK() throws JsonProcessingException {
        JsonNode node = mapper.readTree(BodyProvider.bodyOk());
        Mono<JsonNode> mono = Mono.just(node).doOnNext(jsonSchemaValidator::validateWithJsonSchema);
        StepVerifier.create(mono)
                .expectSubscription()
                .assertNext(jsonNode -> assertEquals(node, jsonNode))
                .verifyComplete();
    }

    @Test
    void validateWithJsonSchemaBadRequestWithInvalidChart() throws JsonProcessingException {
        JsonNode node = mapper.readTree(BodyProvider.bodyBadRequestInvalidedChart());
        Mono<JsonNode> mono = Mono.just(node).doOnNext(jsonSchemaValidator::validateWithJsonSchema);
        StepVerifier.create(mono)
                .expectSubscription()
                .expectErrorMatches(throwable -> throwable instanceof MutantsException &&
                        ("ECMA 262 regex \"^[ATCG]{1,}$\" does not match input string " +
                                "\"ATGCBA\"").equals(((MutantsException) throwable).getLog())&&
                        "Error en la data enviada".equals(((MutantsException) throwable).getTitle())&&
                        "Error en la data Valide la firma".equals(throwable.getMessage())&&
                        "B000".equals(((MutantsException) throwable).getCode())&&
                        ((MutantsException) throwable).getStatus()==400)
                .verify();
    }

    @Test
    void validateWithJsonSchemaBadRequestWithAdditional() throws JsonProcessingException {
        JsonNode node = mapper.readTree(BodyProvider.bodyBadRequestAdditional());
        Mono<JsonNode> mono = Mono.just(node).doOnNext(jsonSchemaValidator::validateWithJsonSchema);
        StepVerifier.create(mono)
                .expectSubscription()
                .expectErrorMatches(throwable -> throwable instanceof MutantsException &&
                        ("object instance has properties which are not allowed by the schema: [\"rna\"]")
                                .equals(((MutantsException) throwable).getLog())&&
                                "Error en la data enviada".equals(((MutantsException) throwable).getTitle())&&
                        "Error en la data Valide la firma".equals(throwable.getMessage())&&
                        "B000".equals(((MutantsException) throwable).getCode())&&
                        ((MutantsException) throwable).getStatus()==400)

                .verify();
    }
}