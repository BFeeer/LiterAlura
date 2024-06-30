package LiterAlura.servicio;

import LiterAlura.model.Autor;
import LiterAlura.model.Libro;

import java.sql.*;

public class BD {
    private static String name = "literalura";
    private static String host = "jdbc:mysql://localhost/"+name;
    private static String user = "tu_usuario";
    private static String password = "tu_contraseña";
    private static Connection connection;

    private static void conectar(){
        try{
            connection = DriverManager.getConnection(host,user,password);
        } catch (SQLException e) {
            System.out.println("No se ha podido realizar la conexión");
            System.out.println("Error: "+e.getMessage());
        }
    }

    private static void desconectar(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("No se ha podido cerrar la conexión");
            System.out.println("Error: "+e.getMessage());
        }
    }

    public static void agregarLibro(Libro libro){
        conectar();
        try {
            PreparedStatement pStatement  = connection.prepareStatement("INSERT INTO libros(titulo, autor, idioma, derechos, descargas) VALUES(?,?,?,?,?)");
            pStatement.setString(1,libro.getTitulo());
            pStatement.setString(2,libro.getAutor().getNombre());
            pStatement.setString(3, libro.getIdioma());
            pStatement.setBoolean(4,libro.getDerechosDeAutor());
            pStatement.setInt(5, libro.getDescargas());
            // ejecutar sentencia SQL y almacenar la cantidad de filas afectadas
            int filasAfectadas = pStatement.executeUpdate();
            System.out.println("Datos insertados\nFilas afectadas: "+filasAfectadas);
            pStatement.close();
            desconectar();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error: "+e.getMessage());
        }
    }

    public static void verLibros(){
        conectar();
        try {
            Statement statement  = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM libros");
            while (resultSet.next()){
                System.out.println(" - "+resultSet.getString("titulo")+" ("+resultSet.getString("autor")+")");
            }
            resultSet.close();
            statement.close();
            desconectar();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error: "+e.getMessage());
        }
    }

    public static void verLibrosPorIdioma(String idioma){
        conectar();
        try {
            PreparedStatement pStatement  = connection.prepareStatement("SELECT * FROM libros WHERE idioma = ?");
            pStatement.setString(1, idioma);
            // ejecutar sentencia SQL
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(" - "+resultSet.getString("titulo")+" ("+resultSet.getString("autor")+") ("+resultSet.getString("idioma")+")");
            }
            resultSet.close();
            pStatement.close();
            desconectar();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error: "+e.getMessage());
        }
    }

    public static void agregarAutor(Autor autor){
        conectar();
        try {
            PreparedStatement pStatement  = connection.prepareStatement("INSERT INTO autores(nombre, anioNacimiento, anioFallecimiento) VALUES(?,?,?)");
            pStatement.setString(1,autor.getNombre());
            pStatement.setInt(2,autor.getAnioNacimiento());
            pStatement.setInt(3,autor.getAnioFallecimiento());
            // ejecutar sentencia SQL y almacenar la cantidad de filas afectadas
            int filasAfectadas = pStatement.executeUpdate();
            System.out.println("Datos insertados\nFilas afectadas: "+filasAfectadas);
            pStatement.close();
            desconectar();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error: "+e.getMessage());
        }
    }

    public static void verAutores(){
        conectar();
        try {
            Statement statement  = connection.createStatement();
            // ejecutar sentencia SQL
            ResultSet resultSet = statement.executeQuery("SELECT * FROM autores");
            while (resultSet.next()){
                System.out.println(" - "+resultSet.getString("nombre"));
            }
            resultSet.close();
            statement.close();
            desconectar();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error: "+e.getMessage());
        }
    }

    public static void verAutoresVivosPorAnio(int anio){
        conectar();
        try {
            PreparedStatement pStatement  = connection.prepareStatement("SELECT * FROM autores WHERE anioNacimiento <= ? AND (anioFallecimiento >= ? OR anioFallecimiento IS NULL)");
            pStatement.setInt(1,anio);
            pStatement.setInt(2,anio);
            // ejecutar sentencia SQL
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(" - "+resultSet.getString("nombre"));
            }
            resultSet.close();
            pStatement.close();
            desconectar();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error: "+e.getMessage());
        }
    }
}
