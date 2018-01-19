/*
 * Copyright (c) 2018 Okra Scheduler
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class OutputMapper extends AbstractMapper {

    private final static Logger LOGGER = LoggerFactory.getLogger(OutputMapper.class);

    public <T> Optional<Document> toDocument(final T item) {
        return toDocument(item, new Document());
    }

    public <T> Optional<Document> toDocument(final T model, final Document document) {
        Preconditions.checkNotNull(model, "model");
        Preconditions.checkNotNull(document, "document");

        try {
            final List<Field> fields = getFields(model.getClass());

            parseIdField(model, document);

            for (final Field field : fields) {
                parseSingleField(model, field, document);
            }

            return Optional.of(document);
        } catch (final IllegalAccessException e) {
            LOGGER.error("Error serializing object with type [{}} to document [{}}", model.getClass(), document, e);
        }

        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    private <T> void parseSingleField(final T model, final Field field, final Document document)
            throws IllegalAccessException {
        final FieldType type = FieldType.get(field.getType());

        if (field.getName().equals("id")) return;

        field.setAccessible(true);

        final Object value = field.get(model);

        if (value == null) return;

        switch (type) {
            case SHORT:
            case INTEGER:
            case LONG:
            case DOUBLE:
            case BOOLEAN:
            case DATE:
                document.append(field.getName(), field.get(model));
                break;
            case STRING:
                final String stringValue = String.valueOf(value);
                if (field.getType().isEnum()) {
                    final Enum enumValue = Enum.valueOf((Class<Enum>) field.getType(), stringValue);
                    document.append(field.getName(), enumValue.name());
                } else {
                    document.append(field.getName(), stringValue);
                }
                break;
            case DATETIME:
                final LocalDateTime dateValue = LocalDateTime.class.cast(value);
                document.append(field.getName(), DateUtil.toDate(dateValue));
                break;
            default:
                // TODO: Object type - Not supported yet
        }
    }

    private <T> void parseIdField(final T model, final Document document) {
        try {
            final Method method = getMethod(model.getClass(), "getId");
            final Object value = method == null ? null : method.invoke(model);
            if (value != null) document.put("_id", new ObjectId(String.valueOf(value)));
        } catch (final NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            // Just ignore it
        }
    }
}