package com.aluracursos.challenge_literalura.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDePartida;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosAutor autor){
        this.nombre = autor.nombre();
        try{
            this.fechaDeNacimiento = LocalDate.parse(autor.fechaDeNacimiento());
            this.fechaDePartida = LocalDate.parse(autor.fechaDePartida());
        } catch (DateTimeParseException e){
            this.fechaDeNacimiento = null;
            this.fechaDePartida = null;
        }
    }



}
