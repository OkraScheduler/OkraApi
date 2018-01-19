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