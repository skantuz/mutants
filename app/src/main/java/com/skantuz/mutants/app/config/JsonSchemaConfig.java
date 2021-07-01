package com.skantuz.mutants.app.config;


import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.skantuz.mutants.apirest.util.JsonSchemaValidator;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class JsonSchemaConfig {
    /**
     * Instanced bean that validate body request
     * @param schema -- Configure in application properties at spring, route json.schemas.file
     * @return JsonSchemaValidator -- Validate Body in JsonNode
     */
    @Bean
    public JsonSchemaValidator jsonSchemaSubscription(@Value("${json.schemas.dir}") String dir,
                                                      @Value("${json.schemas.file}") String schema) {
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        try {
            JsonNode jsonSchemaNode = JsonLoader.fromFile(new File(dir+schema));
            return new JsonSchemaValidator(factory.getJsonSchema(jsonSchemaNode));
        } catch (IOException | ProcessingException e1) {
            throw new BeanCreationException("Ensure that the json schema resource is in: " + schema,e1.getMessage());
        }
    }

}
