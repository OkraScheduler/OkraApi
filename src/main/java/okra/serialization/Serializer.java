package okra.serialization;

import okra.util.DateUtil;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

public class Serializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(Serializer.class);

    private enum FieldType {

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

    public <T> Optional<T> fromDocument(final Class<T> clazz, final Document document) {
        try {
            final T model = clazz.newInstance();
            final Field[] fields = clazz.getDeclaredFields();

            for (final Field field : fields) {
                parseSingleField(model, field, document);
            }

            return Optional.of(model);
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("Error serializing object with type [{}} from document [{}}", clazz, document);
        }

        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    private <T> void parseSingleField(final T model, final Field field, final Document document)
            throws IllegalAccessException {
        FieldType type = FieldType.get(field.getType());

        field.setAccessible(true);

        switch (type) {
            case DOUBLE:
                field.set(model, document.getDouble(field.getName()));
                break;
            case INTEGER:
                field.set(model, document.getInteger(field.getName()));
                break;
            case STRING:
                final String value = document.getString(field.getName());
                if (field.getType().isEnum()) {
                    field.set(model, Enum.valueOf((Class<Enum>) field.getType(), value));
                } else {
                    field.set(model, value);
                }
                break;
            case BOOLEAN:
                field.set(model, document.getBoolean(field.getName()));
                break;
            case DATETIME:
                field.set(model, DateUtil.toLocalDateTime(document.getDate(field.getName())));
                break;
            case DATE:
                field.set(model, document.getDate(field.getName()));
                break;
            default:
                // Object type: Not supported yet
        }
    }
}