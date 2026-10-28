
package repository;

import annotation.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repository<T> {

    private HashMap<String, T> data = new HashMap<>();

    // Find the field marked with @Id using reflection
    private Field findIdField(Class<?> type) {

        Class<?> current = type;

        while (current != null) {

            Field[] fields = current.getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(Id.class)) {
                    return field;
                }
            }

            current = current.getSuperclass();
        }

        throw new IllegalArgumentException(
                "No field marked with @Id found.");
    }

    // Extract the ID from an object
    private String getId(T entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Entity cannot be null.");
        }

        try {
            Field field = findIdField(entity.getClass());

            field.setAccessible(true);

            Object value = field.get(entity);

            if (value == null) {
                throw new IllegalArgumentException(
                        "ID cannot be null.");
            }

            return value.toString();

        } catch (IllegalAccessException e) {
            throw new IllegalStateException(
                    "Unable to access ID field.", e);
        }
    }

    // Save an object
    public T save(T entity) {

        String id = getId(entity);

        data.put(id, entity);

        return entity;
    }

    // Find an object by ID
    public T findById(String id) {
        return data.get(id);
    }

    // Return all stored objects
    public ArrayList<T> findAll() {
        return new ArrayList<>(data.values());
    }

    // Delete an object by ID
    public boolean delete(String id) {
        return data.remove(id) != null;
    }

    // Save multiple objects
    @SafeVarargs
    public final void saveAll(T... items) {

        for (T item : items) {
            save(item);
        }
    }

    // Return the number of stored objects
    public int size() {
        return data.size();
    }

    // Bounded generic method
    public static <N extends Number> double average(N[] values) {

        if (values == null || values.length == 0) {
            return 0.0;
        }

        double sum = 0.0;

        for (N value : values) {
            sum += value.doubleValue();
        }

        return sum / values.length;
    }
}
