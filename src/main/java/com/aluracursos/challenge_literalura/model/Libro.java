package com.aluracursos.challenge_literalura.model;

import jakarta.persistence.*;
/*
Esta clase es un objeto libro y se hace también una entidad para la base de datos
 */
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Integer numeroDeDescargas;

    @ManyToOne
    private Autor autor;

    // Constructor predeterminado
    public Libro(){}

    public Libro(DatosLibro datos){
        this.titulo = datos.titulo();
        this.idioma = datos.idioma().get(0);
        this.numeroDeDescargas = datos.numeroDeDescargas();
    }

    // Solo se colocarón los métodos necesarios

    public String getTitulo(){
        return titulo;
    }

    public void setAutor(Autor autor){;
        this.autor = autor;
    }

    // Método para imprimir un objeto Libro

    @Override
    public String toString() {
        return "\n---------- Libro ----------\n" +
                "Titulo: " + titulo +
                "\nAutor: " + autor.getNombre() +
                "\nIdioma: " + idioma +
                "\nDescargas: " + numeroDeDescargas +
                "\n--------------------";
    }
}
