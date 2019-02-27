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

package okra.serialization;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class AbstractMapper {

    protected List<Field> getFields(final Class<?> clazz) {
        final List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;

        while (current != null) {
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }

        return fields;
    }

    protected Method getMethod(final Class<?> clazz, final String methodName)
            throws NoSuchMethodException {
        Class<?> current = clazz;

        while (current != null) {
            final Method[] methods = current.getDeclaredMethods();

            if (methods != null && methods.length > 0) {
                for (final Method declaredMethod : methods) {
                    if (methodName.equals(declaredMethod.getName())) {
                        return declaredMethod;
                    }
                }
            }

            current = current.getSuperclass();
        }

        return null;
    }
}