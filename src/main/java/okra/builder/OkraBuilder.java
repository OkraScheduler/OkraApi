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
package okra.builder;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import okra.Preconditions;
import okra.base.Okra;
import okra.base.model.OkraItem;

import java.util.concurrent.TimeUnit;

@Setter(AccessLevel.NONE)
@Data
public abstract class OkraBuilder<T extends OkraItem> {

    private String collection;
    private String database;
    private Class<T> itemClass;
    private Long expireDuration;
    private TimeUnit expireDurationUnit;

    public OkraBuilder<T> withItemClass(final Class<T> itemClass) {
        this.itemClass = Preconditions.checkConfigurationNotNull(itemClass, "itemClass");

        if (collection == null || collection.isEmpty()) {
            collection = determineCollectionName(itemClass);
        }

        return this;
    }

    public OkraBuilder<T> withCollection(final String collectionName) {
        this.collection = Preconditions.checkConfigurationNotEmpty(collectionName, "collectionName");
        return this;
    }

    public OkraBuilder<T> withDatabase(final String database) {
        this.database = Preconditions.checkConfigurationNotEmpty(database, "databaseName");
        return this;
    }

    public OkraBuilder<T> withExpiration(final long duration, final TimeUnit unit) {
        this.expireDuration = Preconditions.checkConfigurationNotNull(duration, "duration");
        this.expireDurationUnit = Preconditions.checkConfigurationNotNull(unit, "unit");
        return this;
    }

    public abstract Okra<T> build();

    private String determineCollectionName(final Class<T> itemClass) {
        final String simpleName = itemClass.getSimpleName();
        return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
    }
}