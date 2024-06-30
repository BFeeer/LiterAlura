package LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Autor {
    @JsonAlias("name") private String nombre;
    @JsonAlias("birth_year") private Integer anioNacimiento;
    @JsonAlias("death_year") private Integer anioFallecimiento;

    // constructor
    public Autor() {
    }

    public Autor(String nombre, Integer anioNacimiento, Integer anioFallecimiento) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioFallecimiento = anioFallecimiento;
    }

    // getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    // to string
    @Override
    public String toString() {
        return nombre+" ("+anioNacimiento+" - "+anioFallecimiento+")";
    }
}
