package com.skantuz.mutants.apirest.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.skantuz.mutants.model.config.MutantsErrorCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidationErrors {
    private final List<String> messages;

    private final List<String> logs;

    private MutantsErrorCode errorCode;

    public ValidationErrors() {
        messages = new ArrayList<>();
        logs = new ArrayList<>();
    }

    public MutantsErrorCode getErrorCode() {
        return errorCode;
    }


    public List<String> getMessages() {
        return messages;
    }

    /**
     * @return List Logs in intance
     */
    public List<String> getLogs() {
        return logs;
    }

    /**
     * add message to error and logs
     * @param json validate type error
     * @param message to add in logs
     */
    public void addMessage(JsonNode json, String message) {
        if (Objects.isNull(errorCode)) {
            errorCode = MutantsErrorCode.B000;
            messages.add("Error en la data Valide la firma");
            if ("".equals(json.at("/schema/pointer").asText())) {
                logs.add(message);
            } else {
                errorCode = MutantsErrorCode.B000;
                logs.add(message);
            }
        }
    }

    /**
     * error in generate ValidateJsonSchema
     */
    public void addMessage() {
        messages.add("Error Interno Comunicate con soporte");
        logs.add("Error en JsonSchema");
        errorCode = MutantsErrorCode.E000;

    }

    /**
     * @return if exits errors
     */
    public boolean isInError() {
        return !messages.isEmpty();
    }
}
