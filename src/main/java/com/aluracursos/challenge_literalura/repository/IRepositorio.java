package com.aluracursos.challenge_literalura.repository;

import com.aluracursos.challenge_literalura.model.Autor;
import com.aluracursos.challenge_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
/*
Aquí se sentra la conexión con la base de datos, es decir, que se traen los datos por medio de esta interfaz
con ayuda de la inyección de depencia.
 */
public interface IRepositorio extends JpaRepository<Autor, Long> {

    // Regresa el Autor buscado por nombre
    Optional<Autor> findBynombreContainingIgnoreCase(String nombre);

    // Regresa el libro buscado por titulo
    @Query("SELECT t FROM Autor a JOIN a.libros t WHERE LOWER(t.titulo) = LOWER(:titulo)")
    Optional<Libro> obtenerLibro(String titulo);

    // Regresa los libros en la base de datos
    @Query("SELECT l FROM Libro l")
    List<Libro> obtenerLibros();

    // Regresa el Autores vivos por una fecha de año
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :year AND a.fechaDeFallecimiento >= :year")
    List<Autor> buscarAutoresVivosEn(int year);

    // Regresa los libros por el idioma especifico
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> obtenerPorIdioma(String idioma);

}
