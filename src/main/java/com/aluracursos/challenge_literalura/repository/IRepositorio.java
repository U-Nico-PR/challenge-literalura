package com.aluracursos.challenge_literalura.repository;

import com.aluracursos.challenge_literalura.model.Autor;
import com.aluracursos.challenge_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IRepositorio extends JpaRepository<Autor, Long> {

    Optional<Autor> findBynombreContainingIgnoreCase(String nombre);

    @Query("SELECT t FROM Autor a JOIN a.libros t WHERE LOWER(t.titulo) = LOWER(:titulo)")
    Optional<Libro> obtenerLibro(String titulo);

    @Query("SELECT l FROM Libro l")
    List<Libro> obtenerLibros();

}
