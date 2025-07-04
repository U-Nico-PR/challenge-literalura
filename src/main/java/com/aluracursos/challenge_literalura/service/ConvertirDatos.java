package com.aluracursos.challenge_literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertirDatos implements IConvierteDatos{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convierteDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
