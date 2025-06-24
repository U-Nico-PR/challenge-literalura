package com.aluracursos.challenge_literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
Esta clase recive la URL para consumir la API con solo un método statico
 */
public class ConsumoAPI {

    public static String obtenerDatos(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest reques = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try{
            response = client
                    .send(reques, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e){
            throw new RuntimeException();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }
}
