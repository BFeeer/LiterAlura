package LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroGuten(
        Integer id,
        String title,
        List<Autor> authors,
        List<String> languages,
        boolean copyright,
        Integer download_count
) {
}
