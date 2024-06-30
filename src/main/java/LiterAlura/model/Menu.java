package LiterAlura.model;

import LiterAlura.servicio.BD;
import LiterAlura.servicio.Conversor;
import LiterAlura.servicio.GutendexAPI;

import java.sql.*;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private String URL_BASE = "https://gutendex.com/books/";
    private String BUSCAR_AUTOR_TITULO = "?search=";

    public void desplegar(){
        String opcion;
        loopPrincipal:
        while(true){
            mostrarOpciones();
            opcion = sc.nextLine();
            switch (opcion){
                case "1":
                    buscarLibro();
                    break;
                case "2":
                    mostrarLibrosRegistrados();
                    break;
                case "3":
                    mostrarAutoresRegistrados();
                    break;
                case "4":
                    mostrarAutoresVivosPorAnio();
                    break;
                case "5":
                    mostrarLibrosPorIdioma();
                    break;
                case "0":
                    System.out.println("Hasta luego");
                    break loopPrincipal;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void mostrarOpciones(){
        System.out.println("\n---------- LITERALURA ----------");
        System.out.println("""
                    1. Buscar libro
                    2. Libros registrados
                    3. Autores registrados
                    4. Autores vivos por año
                    5. Libros por idioma
                    0. Salir""");
        System.out.println("-------------------------------");
        System.out.print("Opción: ");
    }

    private void buscarLibro(){
        System.out.println("--------- BUSCAR LIBRO ---------");
        System.out.print("Autor: ");
        String autor = sc.nextLine().strip();
        System.out.print("Título: ");
        String titulo = sc.nextLine().strip();
        System.out.println("-------------------------------");
        // realizar búsqueda
        String jsonRespuesta = GutendexAPI.consultar(
                URL_BASE + BUSCAR_AUTOR_TITULO +
                        autor.replace(" ","%20") +"%20"+
                        titulo.replace(" ", "%20")
        );
        Respuesta respuesta = Conversor.fromJSON(jsonRespuesta, Respuesta.class);
        // obtenemos una lista de resultados libros
        if (!respuesta.resultados().isEmpty()){
            // se extrae el primer resultado
            Libro libro = new Libro(respuesta.resultados().get(0));
            // se inserta en la base de datos
            System.out.println(libro);
            BD.agregarLibro(libro);
            BD.agregarAutor(libro.getAutor());
        }else{
            System.out.println("No se encontraron resultados");
        }
    }

    private void mostrarLibrosRegistrados(){
        System.out.println("------- REGISTRO LIBROS -------");
        BD.verLibros();
        System.out.println("-------------------------------");
    }

    private void mostrarAutoresRegistrados(){
        System.out.println("------- REGISTRO AUTORES -------");
        BD.verAutores();
        System.out.println("-------------------------------");
    }

    private void mostrarAutoresVivosPorAnio(){
        System.out.println("------- AUTORES VIVOS EN -------");
        System.out.print("Año: ");
        int anio = sc.nextInt();
        sc.nextLine();
        System.out.println("-------------------------------");
        BD.verAutoresVivosPorAnio(anio);
    }

    private void mostrarLibrosPorIdioma(){
        System.out.println("----------- IDIOMAS -----------");
        System.out.println("""
                    (es) Español
                    (en) Inglés
                    (it) Italiano
                    (pt) Portugués
                    (fr) Francés""");
        System.out.println("-------------------------------");
        System.out.print("Opcion: ");
        String idioma = sc.nextLine().strip();
        // realizar consulta
        BD.verLibrosPorIdioma(idioma);
    }
}
