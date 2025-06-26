package com.aluracursos.challenge_literalura.model;

import jakarta.persistence.*;

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

    public Libro(){}

    public Libro(DatosLibro datos){
        this.titulo = datos.titulo();
        this.idioma = datos.idioma().get(0);
        this.numeroDeDescargas = datos.numeroDeDescargas();
    }

    public String getTitulo(){
        return titulo;
    }

    public void setAutor(Autor autor){;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "\n--------------------\n" +
                "Titulo: " + titulo +
                "\nAutor: " + autor.getNombre() +
                "\nIdioma: " + idioma +
                "\nDescargas: " + numeroDeDescargas +
                "\n--------------------";
    }
}
