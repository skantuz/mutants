package com.skantuz.mutants.apirest.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.skantuz.mutants.model.config.MutantsException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * Validate JsonNode with JsonSchema file
 */
@RequiredArgsConstructor
public class JsonSchemaValidator {
    private final JsonSchema jsonSchema;

    /**
     * Processing validation
     * @param body -- JsonNote to validate
     * @param errors -- object to load error ValidateErrors
     */
    private void validate(JsonNode body, ValidationErrors errors) {
        try {

            ProcessingReport report = jsonSchema.validate(body);
            if (!report.isSuccess()) {
                report.iterator().forEachRemaining(m -> errors.addMessage(m.asJson(),m.getMessage()));
            }
        } catch (ProcessingException e) {
            errors.addMessage();
        }
    }

    /**
     * validate JsonNode with JsonSchema
     * @param jsonNode
     */
    public void validateWithJsonSchema(JsonNode jsonNode) {
        var errors = new ValidationErrors();
        validate(jsonNode, errors);
        if (errors.isInError()) {
            Optional<String> message = errors.getMessages().stream().reduce((s, s2) -> s2);
            Optional<String> log = errors.getLogs().stream().reduce((s, s2) -> s + ", " + s2);
            if (message.isPresent()&&log.isPresent()) {
                throw new MutantsException(errors.getErrorCode(), log.get(),message.get());
            }
        }

    }
}
