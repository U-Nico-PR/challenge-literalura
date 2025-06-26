package com.aluracursos.challenge_literalura.principal;

import com.aluracursos.challenge_literalura.model.*;
import com.aluracursos.challenge_literalura.service.ConsumoAPI;
import com.aluracursos.challenge_literalura.service.ConvertirDatos;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

/*
Esta clase tiene las funcionalidades objetivo que se presenta al usuario por terminal.
Igualmente se usa para testeo (esto no es recomendable).
 */
public class Principal {
    private static final String BASE_URL = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private ConvertirDatos conversor = new ConvertirDatos();

    // Método que solo muestra las opciones del menú.
    private int menu() {
        int opcion = -1;
        String menu = """
                1- Registrar libro por titulo.
                2- Listar libros registrados.
                3- Listar autores registrados.
                4- Listar auteres vivos en determinado año.
                5- Listar libros por idioma.
                
                0- Salir""";
        System.out.println("\nElige una opción:\n" + menu);
        try {
            opcion = teclado.nextInt();
            teclado.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("\nRevisa la opción ingresada.");
            teclado.nextLine();
        }
        return opcion;
    }

    // Método que hace el loop para la interacción con el usuario.
    public void mostrarMenu() {
        int op = -1;
        while (op != 0) {
            op = menu();
            switch (op) {
                case 1:
                    registarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresPorYear();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no valida.\n");
            }
        }
    }

    private void registarLibro() {
        System.out.println("Ingresa el titulo del libro:");
        var titulo = teclado.nextLine();
    }

    private void listarLibros() {
    }

    private void listarAutores() {
    }

    private void listarAutoresPorYear() {
    }

    private void listarLibrosPorIdioma() {
    }

    private Optional<DatosLibro> obtenerLibro(String titulo){
        var json = ConsumoAPI.obtenerDatos(BASE_URL + "?search=" + titulo.replace(" ", "+"));
        Datos datos = conversor.convierteDatos(json, Datos.class);
        // Regreso el primer libro encontrado
        return datos.resultados().stream().findFirst();
    }
}
