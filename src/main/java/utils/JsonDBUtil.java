package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonDBUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String DB_FILE_PATH = "resources/database.json";

    public static <T> List<T> readFromJson(Class<T> clazz) {
        try {
            return objectMapper.readValue(new File(DB_FILE_PATH), new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void writeToJson(List<T> objects) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(DB_FILE_PATH), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void addObjectToJson(T newObject, Class<T> clazz) {
        try {
            List<T> objects = readFromJson(clazz);
            objects.add(newObject);
            writeToJson(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T findObjectInJson(Class<T> clazz, String key, String value) {
        try {
            List<T> objects = readFromJson(clazz);
            for (T object : objects) {
                String fieldValue = objectMapper.convertValue(object, Map.class).get(key).toString();
                if (fieldValue.equals(value)) {
                    return object;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> void removeObjectFromJson(Class<T> clazz, String key, String value) {
        try {
            List<T> objects = readFromJson(clazz);
            objects.removeIf(object -> objectMapper.convertValue(object, Map.class).get(key).toString().equals(value));
            writeToJson(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void updateObjectInJson(Class<T> clazz, String key, String value, T updatedObject) {
        try {
            List<T> objects = readFromJson(clazz);
            for (int i = 0; i < objects.size(); i++) {
                String fieldValue = objectMapper.convertValue(objects.get(i), Map.class).get(key).toString();
                if (fieldValue.equals(value)) {
                    objects.set(i, updatedObject);
                    break;
                }
            }
            writeToJson(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}