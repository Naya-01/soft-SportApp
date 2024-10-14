package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class JsonDBUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Lire une liste d'objets à partir d'un fichier JSON spécifique
    public static <T> List<T> readFromJson(String filePath, Class<T> clazz) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Écrire une liste d'objets dans un fichier JSON spécifique
    public static <T> void writeToJson(String filePath, List<T> objects) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ajouter un objet à un fichier JSON spécifique
    public static <T> void addObjectToJson(String filePath, T newObject, Class<T> clazz) {
        try {
            List<T> objects = readFromJson(filePath, clazz);
            objects.add(newObject);
            writeToJson(filePath, objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Supprimer un objet d'un fichier JSON spécifique selon une condition
    public static <T> void removeObjectFromJson(String filePath, String key, String value, Class<T> clazz) {
        try {
            List<T> objects = readFromJson(filePath, clazz);
            objects.removeIf(object -> {
                Map<String, Object> objectMap = objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
                return objectMap.get(key).toString().equals(value);
            });
            writeToJson(filePath, objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Trouver un objet dans un fichier JSON spécifique selon une condition
    public static <T> T findObjectInJson(String filePath, String key, String value, Class<T> clazz) {
        try {
            List<T> objects = readFromJson(filePath, clazz);
            for (T object : objects) {
                Map<String, Object> objectMap = objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
                if (objectMap.get(key).toString().equals(value)) {
                    return object;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}