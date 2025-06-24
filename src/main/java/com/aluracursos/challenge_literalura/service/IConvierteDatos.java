package com.aluracursos.challenge_literalura.service;

public interface IConvierteDatos {

    // Método generico que debe definir cada clase que implemente esta interfaz
    public <T> T convierteDatos(String json, Class<T> clase);
}
