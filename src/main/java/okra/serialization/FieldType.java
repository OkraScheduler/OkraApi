package okra.serialization;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

enum FieldType {

    DOUBLE(Double.class, Float.class, BigDecimal.class, double.class, float.class),
    INTEGER(Byte.class, Integer.class, Short.class, Long.class, int.class, byte.class, short.class, long.class),
    STRING(Character.class, String.class, char.class, Enum.class),
    BOOLEAN(Boolean.class, boolean.class),
    DATETIME(LocalDateTime.class),
    DATE(Date.class),
    OBJECT;

    private final Class<?>[] types;

    FieldType(Class<?>... classes) {
        this.types = classes;
    }

    public static FieldType get(Class<?> clazz) {
        return Arrays.stream(FieldType.values())
                .filter(typeClass -> Arrays.stream(typeClass.types).anyMatch(type -> type.isAssignableFrom(clazz)))
                .findFirst()
                .orElse(OBJECT);
    }
}