package okra;

import okra.base.AbstractOkra;
import okra.base.OkraItem;

import java.util.Map;
import java.util.Optional;

public class OkraTestUtil {

    private OkraTestUtil() {
    }

    public static <T extends OkraItem> AbstractOkra<T> create(final String database, final String collection) {
        return new AbstractOkra<T>("db", "collection") {

            @Override
            public Optional<T> poll() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Optional<T> peek() {
                throw new UnsupportedOperationException();
            }

            @Override
            public T retrieve() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Optional<T> reschedule(final T item) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Optional<T> heartbeat(final T item) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Optional<T> heartbeatAndUpdateCustomAttrs(final T item, final Map<String, Object> attrs) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void delete(final T item) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void schedule(final T item) {
                throw new UnsupportedOperationException();
            }
        };
    }
}