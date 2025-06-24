package com.aluracursos.challenge_literalura.principal;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
Esta clase tiene las funcionalidades objetivo que se presenta al usuario por terminal.
Igualmente se usa para testeo (esto no es recomendable).
 */
public class Principal {
    private String url = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);

    // Método que solo muestra las opciones del menú.
    private int menu(){
        int opcion = -1;
        String menu = """
                1- Registrar libro por titulo.
                2- Listar libros registrados.
                3- Listar autores registrados.
                4- Listar auteres vivos en determinado año.
                5- Listar libros por idioma.
                
                0- Salir""";
        System.out.println("\nElige una opción:\n" + menu);
        try{
            opcion = teclado.nextInt();
        } catch(InputMismatchException e){
            System.out.println("\nRevisa la opción ingresada.");
            teclado.nextLine();
        }
        return opcion;
    }

    // Método que hace el loop para la interacción con el usuario.
    public void mostrarMenu(){
        int op = -1;
        while(op != 0){
            op = menu();
            switch(op){
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
    }

    private void listarLibros() {
    }

    private void listarAutores() {
    }

    private void listarAutoresPorYear() {
    }

    private void listarLibrosPorIdioma() {
    }
}
