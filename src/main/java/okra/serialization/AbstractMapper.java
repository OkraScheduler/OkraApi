package okra.serialization;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class AbstractMapper {

    protected List<Field> getFields(final Class<?> clazz) {
        final List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;

        while (current != null) {
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }

        return fields;
    }

    protected Method getMethod(final Class<?> clazz, final String methodName)
            throws NoSuchMethodException {
        Class<?> current = clazz;

        while (current != null) {
            final Method[] methods = current.getDeclaredMethods();

            if (methods != null && methods.length > 0) {
                for (final Method declaredMethod : methods) {
                    if (methodName.equals(declaredMethod.getName())) {
                        return declaredMethod;
                    }
                }
            }

            current = current.getSuperclass();
        }

        return null;
    }
}