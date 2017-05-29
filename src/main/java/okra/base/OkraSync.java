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

package okra.base;

import okra.exception.OkraItemNotFoundException;

import java.util.Map;
import java.util.Optional;

public interface OkraSync<T extends OkraItem> extends Okra<T> {

    /**
     * Retrieves a scheduled item from the backend, deleting it if successful.
     * <p>
     * Warning: The item processing is not guaranteed.
     * If you want to delete the item after the processing is finished in order to
     * do not delete any items before the processing is done, use method {@link OkraSync#peek()}
     *
     * @return An optional containing the item if found, otherwise an empty optional
     */
    Optional<T> poll();

    /**
     * Retrieves a scheduled item from the backend.
     * <p>
     * This method doesn't deletes the item if any is found.
     * Your should call {@link OkraSync#delete(OkraItem)} after the processing is finished.
     *
     * @return An optional containing the item if found, otherwise an empty optional
     */
    Optional<T> peek();

    /**
     * Retrieves, but does not remove, the head of queue. This method
     * differs from {@link OkraSync#peek peek} only in that it throws an exception
     * if there is no elements available.
     *
     * @return An optional containing the item if found, otherwise an empty optional
     */
    T retrieve() throws OkraItemNotFoundException;

    /**
     * Reschedule an item that was previously retrieved from the scheduled items pool
     *
     * @param item The item that will be rescheduled
     * @return The rescheduled item if success, otherwise an empty optional
     */
    Optional<T> reschedule(T item);

    /**
     * Heartbeat an item to prevent that other scheduled item consumers acquire this same item
     *
     * @param item The item to heartbeat
     * @return The updated item if success, otherwise an empty optional
     */
    Optional<T> heartbeat(T item);

    /**
     * Heartbeat an item to prevent that other scheduled item consumers acquire this same item
     * This operation also updates custom attributes
     *
     * @param item The item to heartbeat
     * @return The updated item if success, otherwise an empty optional
     */
    Optional<T> heartbeatAndUpdateCustomAttrs(T item, Map<String, Object> attrs);

    /**
     * Delete a scheduled item
     *
     * @param item The item to be deleted
     */
    void delete(T item);

    /**
     * Schedule an item
     *
     * @param item The item to schedule
     */
    void schedule(T item);

    /**
     * Count items by status.
     * <p>
     * e.g. how many items are pending? Or How many items are currently processing?
     *
     * @param status
     * @return The count
     */
    long countByStatus(OkraStatus status);

}
