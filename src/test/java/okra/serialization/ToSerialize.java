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

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

public class ToSerialize {

    @Data
    public static class WithStringField {

        private String field;
    }

    @Data
    public static class WithIntegerField {

        private Integer field;
    }

    @Data
    public static class WithDoubleField {

        private Double field;
    }

    @Data
    public static class WithBooleanField {

        private Boolean field;
    }

    @Data
    public static class WithLocalDateTimeField {

        private LocalDateTime field;
    }

    @Data
    public static class WithDateField {

        private Date field;
    }

    @Data
    public static class WithEnumerationField {

        public enum Whatever {Test1, Test2}

        private Whatever field;
    }

    @Data
    public static class WithFloatField {

        private Float field;
    }

    @Data
    public static class WithTwoFields {

        private String fieldString;

        private Integer fieldInteger;
    }
}