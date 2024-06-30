package LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {
    private Integer id;

    private String titulo;

    private Autor autor;

    private String idioma;

    private boolean derechosDeAutor;

    private Integer descargas;

    // constructor
    public Libro() {
    }

    public Libro(Integer id, String titulo, Autor autor, String idioma, boolean derechosDeAutor, Integer descargas) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.derechosDeAutor = derechosDeAutor;
        this.descargas = descargas;
    }

    public Libro(LibroGuten libroGuten) {
        this.id = libroGuten.id();
        this.titulo = libroGuten.title();
        if (!libroGuten.authors().isEmpty()){
            this.autor = libroGuten.authors().get(0);
        }
        if (!libroGuten.languages().isEmpty()){
            this.idioma = libroGuten.languages().get(0);
        }
        this.derechosDeAutor = libroGuten.copyright();
        this.descargas = libroGuten.download_count();
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean getDerechosDeAutor() {
        return derechosDeAutor;
    }

    public void setDerechosDeAutor(boolean derechosDeAutor) {
        this.derechosDeAutor = derechosDeAutor;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    // To string
    @Override
    public String toString() {
        return """
                ---------- LIBRO ----------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Descargas: %d
                %s
                ---------------------------
                """.formatted(titulo,autor, idioma, descargas, derechosDeAutor?"Copyright":"No Copyright");
    }
}
