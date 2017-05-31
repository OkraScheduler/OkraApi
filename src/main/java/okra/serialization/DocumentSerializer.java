package okra.serialization;

import org.bson.Document;

import java.util.Optional;

public class DocumentSerializer {

    private final InputMapper inputMapper;
    private final OutputMapper outputMapper;

    public DocumentSerializer() {
        this.inputMapper = new InputMapper();
        this.outputMapper = new OutputMapper();
    }

    public <T> Optional<T> fromDocumentSafe(final Class<T> clazz, final Document document) {
        return inputMapper.fromDocument(clazz, document);
    }

    public <T> T fromDocument(final Class<T> clazz, final Document document) {
        return fromDocumentSafe(clazz, document).orElse(null);
    }

    public <T> Optional<Document> toDocumentSafe(final T item) {
        return outputMapper.toDocument(item);
    }

    public <T> Document toDocument(final T item) {
        return toDocumentSafe(item).orElse(null);
    }

    public <T> Optional<Document> toDocumentSafe(final T item, final Document document) {
        return outputMapper.toDocument(item, document);
    }

    public <T> Document toDocument(final T item, final Document document) {
        return toDocumentSafe(item, document).orElse(null);
    }
}