package okra.serialization;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class AbstractMapper {

    protected <T> List<Field> getFields(final Class<T> clazz) {
        final List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;

        while (current != null) {
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }

        return fields;
    }
}