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

import okra.index.IndexDefinition;
import okra.index.Ordering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractOkra<T extends OkraItem> implements Okra<T> {

    private List<IndexDefinition> indexDefinitions;

    private List<IndexDefinition> generateDefaultIndexDefinitions() {
        List<String> statusRunDate = new ArrayList<>();
        statusRunDate.add("status");
        statusRunDate.add("runDate");

        IndexDefinition statusRunDateDef = new IndexDefinition();
        statusRunDateDef.setAttrs(statusRunDate);
        statusRunDateDef.setOrdering(Ordering.ASC);


        List<String> statusHeartbeat = new ArrayList<>();
        statusHeartbeat.add("status");
        statusHeartbeat.add("heartbeat");

        IndexDefinition statusHeartbeatDef = new IndexDefinition();
        statusHeartbeatDef.setAttrs(statusHeartbeat);
        statusHeartbeatDef.setOrdering(Ordering.ASC);

        List<String> idStatusHeartbeat = new ArrayList<>();
        idStatusHeartbeat.add("_id");
        idStatusHeartbeat.add("status");
        idStatusHeartbeat.add("heartbeat");

        IndexDefinition idStatusHeartbeatDef = new IndexDefinition();
        idStatusHeartbeatDef.setAttrs(idStatusHeartbeat);
        idStatusHeartbeatDef.setOrdering(Ordering.ASC);

        return Arrays.asList(
                statusRunDateDef,
                statusHeartbeatDef,
                idStatusHeartbeatDef);
    }

    @Override
    public List<IndexDefinition> indexDefinitions() {
        if (indexDefinitions == null) {
            return generateDefaultIndexDefinitions();
        }
        return indexDefinitions;
    }

    @Override
    public void setIndexDefinitions(List<IndexDefinition> indexDefinitions) {
        this.indexDefinitions = indexDefinitions;
    }

    @Override
    public void setup() {
    }

}
