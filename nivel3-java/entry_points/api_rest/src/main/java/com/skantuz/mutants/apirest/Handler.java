package com.skantuz.mutants.apirest;

import com.fasterxml.jackson.databind.JsonNode;
import com.skantuz.mutants.apirest.dto.StatsDto;
import com.skantuz.mutants.model.config.MutantsException;
import com.skantuz.mutants.usecase.StatsUseCase;
import com.skantuz.mutants.apirest.mapper.DnaMapper;
import com.skantuz.mutants.apirest.util.JsonSchemaValidator;
import com.skantuz.mutants.model.config.MutantsErrorCode;
import com.skantuz.mutants.model.dna.Dna;
import com.skantuz.mutants.usecase.DnaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class Handler {
    private final DnaUseCase dnaUseCase;
    private final StatsUseCase statsUseCase;
    private final JsonSchemaValidator jsonSchemaValidator;

    /**
     * ValidateMutant
     * * @param ServerRequest with body
     * @return Server Response whit 200 if Mutant is true and 403 if mutant is false
     */
    public <T extends ServerResponse> Mono<ServerResponse> validateMutant(ServerRequest request) {
        return request.bodyToMono(JsonNode.class)
                .doOnNext(jsonSchemaValidator::validateWithJsonSchema)
                .map(DnaMapper::JsonToDna)
                .doOnNext(this::validateArray)
                .flatMap(dnaUseCase::validateMutant)
                .flatMap(dna -> dna.getMutant() ? ServerResponse.ok().build() : ServerResponse.status(403).build());
    }

    /**
     * @return humans, mutants and percent mutants
     */
    public Mono<ServerResponse> stats(ServerRequest serverRequest) {
        return statsUseCase.getStats()
                .map(StatsDto::new)
                .flatMap(statsDto -> ServerResponse.ok().body(BodyInserters.fromValue(statsDto)));
    }

    /**
     * validate leght string in array equals to legth
     * @param dna received in request
     */
    private void validateArray(Dna dna){
        if(Arrays.stream(dna.getDna()).anyMatch(s -> s.length()!=dna.getDna().length)){
            throw new MutantsException(MutantsErrorCode.B000,"string length is not equal to legth array"
                    ,"la longitud de los string debe ser igual a los del array");
        }
    }




   
}
