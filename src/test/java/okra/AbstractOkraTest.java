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
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractOkraTest {

    @Test
    public void test() {
        AbstractOkra okra = new AbstractOkra("db", "collection") {
            @Override
            protected void initDbIfNeeded() {
            }

            @Override
            public Optional poll() {
                return null;
            }

            @Override
            public Optional reschedule(OkraItem item) {
                return null;
            }

            @Override
            public Optional heartbeat(OkraItem item) {
                return null;
            }

            @Override
            public void delete(OkraItem item) {

            }

            @Override
            public void schedule(OkraItem item) {

            }

            @Override
            public Optional heartbeatAndUpdateCustomAttrs(OkraItem item, Map attrs) {
                return null;
            }
        };

        assertThat(okra.getCollection()).isEqualTo("collection");
        assertThat(okra.getDatabase()).isEqualTo("db");
    }

}
