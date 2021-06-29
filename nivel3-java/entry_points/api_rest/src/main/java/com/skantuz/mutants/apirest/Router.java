package com.skantuz.mutants.apirest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Router {

    @Bean
    RouterFunction<ServerResponse> mutantsRouter(Handler handler) {
        return route(POST("/mutant/"), handler::validateMutant)
                .andRoute(GET("/stats"), handler::stats);
    }
}
