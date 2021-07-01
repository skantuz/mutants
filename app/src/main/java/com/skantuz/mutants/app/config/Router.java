package com.skantuz.mutants.app.config;

import com.skantuz.mutants.apirest.Handler;
import com.skantuz.mutants.apirest.util.JsonSchemaValidator;
import com.skantuz.mutants.usecase.DnaUseCase;
import com.skantuz.mutants.usecase.StatsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class Router {

    @Bean
    public Handler handler(DnaUseCase dnaUseCase, StatsUseCase statsUseCase,
                           JsonSchemaValidator jsonSchemaValidator) {
        return new Handler(dnaUseCase, statsUseCase, jsonSchemaValidator);
    }

    @Bean
    RouterFunction<ServerResponse> mutantsRouter(Handler handler) {
        return RouterFunctions.route(POST("/mutant/"), handler::validateMutant)
                .andRoute(GET("/stats"), handler::stats);
    }
}
