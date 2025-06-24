package com.aluracursos.challenge_literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    @ManyToOne
    private Autor autor;

    public Libro(){}

    public Libro(DatosLibro libro){
        this.titulo = libro.titulo();
        this.idioma = libro.idioma().get(0);
    }

}
