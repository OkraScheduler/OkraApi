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
package okra.base.async;

import okra.base.Okra;
import okra.base.async.callback.*;
import okra.base.model.OkraItem;
import okra.base.model.OkraStatus;
import okra.base.sync.OkraSync;

public interface OkraAsync<T extends OkraItem> extends Okra<T> {

    /**
     * (Retrieves a scheduled item from the backend.
     * <p>
     * This method doesn't deletes the item if any is found.
     * Your should call {@link OkraSync#delete(OkraItem)} after the processing is finished.
     *
     * @param callback
     */
    void peek(OkraItemCallback<T> callback);

    /**
     * Retrieves a scheduled item from the backend, deleting it if successful.
     * <p>
     * Warning: The item processing is not guaranteed.
     * If you want to delete the item after the processing is finished in order to
     * do not delete any items before the processing is done, use method {@link OkraSync#peek()}
     *
     * @param callback
     */
    void poll(OkraItemCallback<T> callback);


    /**
     * Delete a scheduled item
     *
     * @param item
     * @param callback
     */
    void delete(T item, OkraItemDeleteCallback callback);

    /**
     * Reschedule an item that was previously retrieved from the scheduled items pool
     *
     * @param item
     * @param callback
     */
    void reschedule(T item, OkraItemOperationCallback<T> callback);

    /**
     * Heartbeat an item to prevent that other scheduled item consumers acquire this same item
     *
     * @param item
     * @param callback
     */
    void heartbeat(T item, OkraItemOperationCallback<T> callback);

    /**
     * Schedules an item
     *
     * @param item
     * @param callback
     */
    void schedule(T item, OkraItemScheduleCallback callback);

    /**
     * Count items by status.
     * <p>
     * e.g. how many items are pending? Or How many items are currently processing?
     *
     * @param status
     * @param callback
     */
    void countByStatus(OkraStatus status, OkraCountCallback callback);

    /**
     * Counts the number of delayed items that are waiting to be processed.
     *
     * @param callback
     */
    void countDelayed(OkraCountCallback callback);
}