/*
 * Copyright (c) 2019 Okra Scheduler
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
 */
package okra.base.model;

import java.time.LocalDateTime;

public interface OkraItem {

    /**
     * @return
     */
    String getId();

    /**
     * @param id
     */
    void setId(String id);

    /**
     * Latest date this entry received a heartBeat
     *
     * @return The date of the latest heartbeat
     */
    LocalDateTime getHeartbeat();

    /**
     * Set latest date this entry received a heartbeat
     *
     * @param lastHeartbeatDate
     */
    void setHeartbeat(LocalDateTime lastHeartbeatDate);

    /**
     * The date this entry should run
     *
     * @return The date this entry should run
     */
    LocalDateTime getRunDate();

    /**
     * Set run date for this item
     *
     * @param runDate
     */
    void setRunDate(LocalDateTime runDate);

    /**
     * @return The current status of this scheduled item
     */
    OkraStatus getStatus();

    /**
     * Set OkraItem status
     *
     * @param status the new status
     */
    void setStatus(OkraStatus status);
}