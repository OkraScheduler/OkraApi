/*
 * Copyright (c) 2017 Okra Scheduler
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