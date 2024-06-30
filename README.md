# LiterAlura

![Badge en Desarollo](https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green)

Es una aplicación desarrollada con Spring Boot. Realiza consultas a la API de metadatos de libros electrónicos
del proyecto Gutenberg para crear un catálogo mediante el almacenamiento de la información de los libros en una base de
datos relacional. Proporciona funcionalidades sobre el catálogo construido como:
- Visualización de libros guardados
- Visualización de autores
- Visualización de autores vivos en determinado periodo
- Visualización de libros en determinado idioma

## Características

- Interacción mediante línea de comandos
- Interfaz de usuario simple e intuitiva
- Búsqueda de libros en tiempo real
- Persistencia de libros buscados

## Requisitos
- [Java JDK 17 o superior](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 4 o superior]()
- [Spring 3.3.1](https://start.spring.io/)
- [MySql 8.0]()

## Dependencias
- [Jackson 2.17.1](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
- [MySQL Connector](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)

## Instalación

1. Clona este repositorio en tu máquina local:

    ```sh
    git clone https://github.com/BFeeer/LiterAlura.git
    cd LiterAlura
    ```
   
2. Crea la estructura de la base de datos
    ```sh
    CREATE DATABASE literalura;
   
   CREATE TABLE autores(
    id INT AUTO_INCREMENT,
    nombre VARCHAR(150) UNIQUE,
    anioNacimiento INT,
    anioFallecimiento INT,
    PRIMARY KEY(id)
    );
   
   CREATE TABLE libros(
    id INT auto_increment,
    titulo VARCHAR(150),
    autor VARCHAR(150),
    idioma VARCHAR(10),
    derechos BOOLEAN,
    descargas INT,
    PRIMARY KEY(id)
    );
    ```
3. Configura las credenciales de acceso a la base de datos en la clase BD
   ```sh
   String name = "literalura";
   String host = "jdbc:mysql://localhost/"+name;
   String user = "tu_usuario";
   String password = "tu_contraseña";
    ```   

## Uso

1. Ejecuta la aplicación.
2. Sigue las instrucciones que se muestran en consola.