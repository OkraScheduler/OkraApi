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

import okra.exception.InvalidOkraConfigurationException;

public class Preconditions {

    private Preconditions() {
    }

    public static <T> T checkConfigurationNotNull(final T reference, final String field) {
        if (reference == null) {
            final String message = String.format("Field \"%s\" shout not be null", field);
            throw new InvalidOkraConfigurationException(message);
        }

        return reference;
    }

    public static String checkConfigurationNotEmpty(final String reference, final String field) {
        if (reference == null || reference.isEmpty()) {
            final String message = String.format("Field \"%s\" shout not be empty", field);
            throw new InvalidOkraConfigurationException(message);
        }

        return reference;
    }
}