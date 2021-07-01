package com.skantuz.mutants.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log4j2
@Order(-2)
@Configuration
public class ApiRestErrorHandler implements ErrorWebExceptionHandler {
    private ObjectMapper mapper;
    DataBuffer dataBuffer = null;

    @SneakyThrows
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        ServerHttpRequest request = exchange.getRequest();
        var messageId = UUID.randomUUID().toString();
        try {
            throw ex;
        } catch (MutantsException e) {
            DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
            exchange.getResponse().setStatusCode(HttpStatus.valueOf(e.getStatus()));
            var str = String.format("{\"status\": %d,\n\"code\": \"%s\",\n" +
                    "\"title\" :\"%s\",\n\"message\" :\"%s\",\n\"messageId\":\"%s\"}",
                    e.getStatus(),e.getCode(),e.getTitle(),e.getMessage(),messageId);
            dataBuffer = bufferFactory.wrap(str.getBytes());
            log.info( messageId+ ": " + e.getLog());
        }

        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }
}
