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

import okra.base.model.OkraItem;
import okra.base.model.OkraStatus;
import okra.base.sync.AbstractOkraSync;
import okra.exception.OkraItemNotFoundException;

import java.util.Map;
import java.util.Optional;

public class OkraTestUtil {
    private OkraTestUtil() {
    }

    public static <T extends OkraItem> AbstractOkraSync<T> create(final String database, final String collection) {
        return new AbstractOkraSync<T>("db", "collection") {
            @Override
            public Optional<T> poll() {
                return null;
            }

            @Override
            public Optional<T> peek() {
                return null;
            }

            @Override
            public T retrieve() throws OkraItemNotFoundException {
                return null;
            }

            @Override
            public Optional<T> reschedule(T item) {
                return null;
            }

            @Override
            public Optional<T> heartbeat(T item) {
                return null;
            }

            @Override
            public Optional<T> heartbeatAndUpdateCustomAttrs(T item, Map<String, Object> attrs) {
                return null;
            }

            @Override
            public void delete(T item) {

            }

            @Override
            public void schedule(T item) {

            }

            @Override
            public long countByStatus(OkraStatus status) {
                return 0;
            }

            @Override
            public long countDelayed() {
                return 0;
            }
        };
    }
}