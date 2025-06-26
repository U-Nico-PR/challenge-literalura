package com.aluracursos.challenge_literalura.principal;

import com.aluracursos.challenge_literalura.model.*;
import com.aluracursos.challenge_literalura.repository.IRepositorio;
import com.aluracursos.challenge_literalura.service.ConsumoAPI;
import com.aluracursos.challenge_literalura.service.ConvertirDatos;

import java.util.*;
/*
Esta clase tiene las funcionalidades objetivo que se presenta al usuario por terminal.
 */
public class Principal {

    // Atributos requeridos para el reto
    private static final String BASE_URL = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private ConvertirDatos conversor = new ConvertirDatos();
    private IRepositorio repositorio;
    private List<Autor> autores;
    private List<Libro> libros;

    // Constructor con la inyección de dependencia
    public Principal(IRepositorio repositorio) {
        this.repositorio = repositorio;
    }

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

    // Método para registrar un libro en la base de datos
    private void registarLibro() {
        System.out.println("Ingresa el titulo del libro:");
        var titulo = teclado.nextLine();
        if(!estaLibro(titulo)) {
            Optional<DatosLibro> datosLibro = obtenerLibro(titulo);
            if (datosLibro.isPresent()) {
                guardarLibro(datosLibro.get());
            } else {
                System.out.println("Libro no encontrado");
            }
        } else {
            System.out.println("Libro ya ingresado");
        }
    }

    // Método que lista los libros que estan registrados
    private void listarLibros() {
        libros = repositorio.obtenerLibros();
        List<Libro> listaOrdenada = libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo)).
                toList();
        listaOrdenada.forEach(System.out::println);
    }

    // Método que lista los autores que estan registrados
    private void listarAutores() {
        autores = repositorio.findAll();
        List<Autor> listaOrdenada = autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .toList();
        listaOrdenada.forEach(System.out::println);
    }

    // Método que muestra los autores vivos en un año especifico
    private void listarAutoresPorYear() {
        System.out.println("Ingresa el año vivo del autor(es) que quiere buscar:");
        try{
            int year = teclado.nextInt();
            teclado.nextLine();
            List<Autor> autores = repositorio.buscarAutoresVivosEn(year);
            if(!autores.isEmpty()){
                autores.forEach(System.out::println);
            } else {
                System.out.println("No hay autores registrados en ese año");
            }
        } catch(InputMismatchException e) {
            teclado.nextLine();
            System.out.println("Dato ingresado invalido.");
        }
    }

    // Método que lista los libros registrados por un idioma específico
    private void listarLibrosPorIdioma() {
        List<String> opciones = new ArrayList<>(List.of("es", "en","fr", "pt"));
        String menu = """
                Ingrese el idioma para buscar los libros:
                es - español
                en - inglés
                fr - frances
                pt - portugués
                """;
        System.out.println(menu);
        var op = teclado.nextLine();
        if(opciones.contains(op)){
            List<Libro> libros = repositorio.obtenerPorIdioma(op);
            if(!libros.isEmpty()){
                libros.forEach(System.out::println);
            } else {
                System.out.println("No se encontro algún libro con el idioma: " + op);
            }
        } else {
            System.out.println("Opcion no reconocida");
        }

    }

    // Método que ayuda a traer la información de la API
    private Optional<DatosLibro> obtenerLibro(String titulo) {
        var json = ConsumoAPI.obtenerDatos(BASE_URL + "?search=" + titulo.replace(" ", "+"));
        Datos datos = conversor.convierteDatos(json, Datos.class);
        // Regreso el primer libro encontrado
        return datos.resultados().stream().findFirst();
    }

    // Método que verifica si ya se encuentra un libro registrado
    private boolean estaLibro(String titulo){
        Optional<Libro> libroBuscado = repositorio.obtenerLibro(titulo);
        return libroBuscado.isPresent();
    }

    // Mpetodo que ayuda a registrarLibro() para almacenarlo
    private void guardarLibro(DatosLibro datosLibro) {
        Autor autor = new Autor(datosLibro.autor().get(0));
        Libro libro = new Libro(datosLibro);
        String nombre = autor.getNombre();
        if(buscarAutor(nombre)){
            Optional<Autor> autorBuscado = repositorio.findBynombreContainingIgnoreCase(nombre);
            Autor autorExistente = autorBuscado.get();
            autorExistente.agregarLibro(libro);
            repositorio.save(autorExistente);
        } else {
            autor.agregarLibro(libro);
            repositorio.save(autor);
            System.out.println(libro);
        }
    }

    // Método que ayuda a mapear un autor en en repositorio
    private boolean buscarAutor(String nombre){
        Optional<Autor> autorEncontrado = repositorio.findBynombreContainingIgnoreCase(nombre);
        return autorEncontrado.isPresent();
    }

}
