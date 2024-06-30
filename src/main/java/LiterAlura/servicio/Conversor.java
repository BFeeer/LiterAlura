package LiterAlura.servicio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Conversor {
    private static ObjectMapper JSON_mapper = new ObjectMapper();

    public static <T> T fromJSON(String json, Class<T> clase) {
        try {
            return JSON_mapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
