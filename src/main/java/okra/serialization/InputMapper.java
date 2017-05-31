package okra.serialization;

import okra.Preconditions;
import okra.util.DateUtil;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

class InputMapper {

    private final static Logger LOGGER = LoggerFactory.getLogger(InputMapper.class);

    public <T> Optional<T> fromDocument(final Class<T> clazz, final Document document) {
        Preconditions.checkNotNull(clazz, "clazz");
        Preconditions.checkNotNull(document, "document");

        try {
            final T model = clazz.newInstance();
            final Field[] fields = clazz.getDeclaredFields();

            parseIdField(model, document);

            for (final Field field : fields) {
                parseSingleField(model, field, document);
            }

            return Optional.of(model);
        } catch (final InstantiationException | IllegalAccessException e) {
            LOGGER.error("Error serializing object with type [{}} from document [{}}", clazz, document, e);
        }

        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    private <T> void parseSingleField(final T model, final Field field, final Document document)
            throws IllegalAccessException {
        final FieldType type = FieldType.get(field.getType());

        if (field.getName().equals("id")) return;

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
                // TODO: Object type - Not supported yet
        }
    }

    private <T> void parseIdField(final T model, final Document document) {
        try {
            final ObjectId objectId = document.getObjectId("_id");
            final String id = objectId == null ? null : objectId.toString();
            final Method method = model.getClass().getMethod("setId");

            method.invoke(model, id);
        } catch (final NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            // Just ignore it
        }
    }
}