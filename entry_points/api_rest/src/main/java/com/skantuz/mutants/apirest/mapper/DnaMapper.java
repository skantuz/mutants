package com.skantuz.mutants.apirest.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.skantuz.mutants.model.config.MutantsErrorCode;
import com.skantuz.mutants.model.config.MutantsException;
import com.skantuz.mutants.model.dna.Dna;

import java.util.stream.StreamSupport;

public class DnaMapper {
    public static Dna JsonToDna(JsonNode node) {
        return Dna.builder()
                .id(getId(node.get("dna")))
                .dna(getDna(node.get("dna")))
                .build();
    }

    private static String[] getDna(JsonNode dna) {
        return StreamSupport.stream(dna.spliterator(),false)
                .map(JsonNode::asText)
                .toArray(String[]::new);
    }

    private static String getId(JsonNode dna) {
        var id = StreamSupport.stream(dna.spliterator(), false)
                .map(JsonNode::asText).reduce((s, s2) -> s + s2);
        if(id.isPresent()){
            return id.get();
        }else {
            throw new MutantsException(
                    MutantsErrorCode.E000,"Ocurrio un error genrardo el id","Comunicate con Soporte");
        }
    }
}
