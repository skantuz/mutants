package com.skantuz.mutants.apirest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skantuz.mutants.model.config.MutantsException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Log4j2
@Order(-2)
@Configuration
@RequiredArgsConstructor
public class ApiRestErrorHandler implements ErrorWebExceptionHandler {
    private ObjectMapper mapper;
    DataBuffer dataBuffer = null;

    @SneakyThrows
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        ServerHttpRequest request = exchange.getRequest();
        var messageId = UUID.randomUUID();
        Map<String, Object> response = Map.of("_clientRequest", request.getId(), "_requestDate", LocalDateTime.now(),
                "_messageId", messageId, "_version", "1.0");
        try {
            throw ex;
        } catch (MutantsException e) {
            DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
            exchange.getResponse().setStatusCode(HttpStatus.valueOf(e.getStatus()));
            Map<String, Object> error = Map.of("href", request.getURI().toString(), "status", e.getStatus(),
                    "code", e.getCode(), "title", e.getTitle(), "message", e.getMessage());
            response.put("error", error);
            dataBuffer = bufferFactory.wrap(mapper.writeValueAsBytes(response));
            log.info( messageId+ ": " + e.getLog());
        }

        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }
}
