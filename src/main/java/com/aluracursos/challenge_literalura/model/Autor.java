package com.aluracursos.challenge_literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}

    public Autor(DatosAutor datos){
        this.nombre = datos.nombre();
        try{
            this.fechaDeNacimiento = Integer.parseInt(datos.fechaDeNacimiento());
            this.fechaDeFallecimiento = Integer.parseInt(datos.fechaDeFallecimiento());
        } catch (NumberFormatException e){
            this.fechaDeNacimiento = 0;
            this.fechaDeFallecimiento = 0;
        }
    }

    public String getNombre(){
        return nombre;
    }

    public void agregarLibro(Libro libro){
        libro.setAutor(this);
        this.libros.add(libro);
    }

    @Override
    public String toString() {
        List<String> tituloDeLibros = libros.stream()
                .map(Libro::getTitulo)
                .toList();
        return "---------------------\n" +
                "Nombre: " + nombre +
                "\nFecha de Nacimiento: " + fechaDeNacimiento +
                "\nFecha de Fallecimiento: " + fechaDeFallecimiento +
                "\nLibros: " + tituloDeLibros +
                "\n---------------------------------";
    }
}
