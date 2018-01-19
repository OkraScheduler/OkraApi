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

import okra.util.DateUtil;
import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class OutputMapperTest {

    private OutputMapper mapper;

    @Before
    public void setUp() {
        mapper = new OutputMapper();
    }

    @Test
    public void shouldParseAModelWithStringFieldValueTest() {
        final ToSerialize.WithStringField model = new ToSerialize.WithStringField();
        model.setField("Test");

        final Optional<Document> document = mapper.toDocument(model);

        Assertions.assertThat(document.isPresent()).isTrue();
        Assertions.assertThat(document.get().getString("field")).isEqualTo("Test");
    }

    @Test
    public void shouldParseAModelWithIntegerFieldValueTest() {
        final ToSerialize.WithIntegerField model = new ToSerialize.WithIntegerField();
        model.setField(2);

        final Optional<Document> document = mapper.toDocument(model);

        Assertions.assertThat(document.isPresent()).isTrue();
        Assertions.assertThat(document.get().getInteger("field")).isEqualTo(2);
    }

    @Test
    public void shouldParseAModelWithDoubleFieldValueTest() {
        final ToSerialize.WithDoubleField model = new ToSerialize.WithDoubleField();
        model.setField(2D);

        final Optional<Document> document = mapper.toDocument(model);

        Assertions.assertThat(document.isPresent()).isTrue();
        Assertions.assertThat(document.get().getDouble("field")).isEqualTo(2D);
    }

    @Test
    public void shouldParseAModelWithBooleanFieldValueTest() {
        final ToSerialize.WithBooleanField model = new ToSerialize.WithBooleanField();
        model.setField(true);

        final Optional<Document> document = mapper.toDocument(model);

        Assertions.assertThat(document.isPresent()).isTrue();
        Assertions.assertThat(document.get().getBoolean("field")).isTrue();
    }

    @Test
    public void shouldParseAModelWithLocalDateTimeFieldValueTest() {
        final LocalDateTime now = LocalDateTime.now();

        final ToSerialize.WithLocalDateTimeField model = new ToSerialize.WithLocalDateTimeField();
        model.setField(now);

        final Optional<Document> document = mapper.toDocument(model);

        Assertions.assertThat(document.isPresent()).isTrue();
        Assertions.assertThat(document.get().getDate("field")).isEqualTo(DateUtil.toDate(now));
    }

    @Test
    public void shouldParseAModelWithDateFieldValueTest() {
        final Date now = new Date();

        final ToSerialize.WithDateField model = new ToSerialize.WithDateField();
        model.setField(now);

        final Optional<Document> document = mapper.toDocument(model);

        Assertions.assertThat(document.isPresent()).isTrue();
        Assertions.assertThat(document.get().getDate("field")).isEqualTo(now);
    }

    @Test
    public void shouldParseADocumentWithEnumerationFieldValueTest() {
        final ToSerialize.WithEnumerationField model = new ToSerialize.WithEnumerationField();
        model.setField(ToSerialize.WithEnumerationField.Whatever.Test1);

        final Optional<Document> document = mapper.toDocument(model);

        Assertions.assertThat(document.isPresent()).isTrue();
        Assertions.assertThat(document.get().getString("field"))
                .isEqualTo(ToSerialize.WithEnumerationField.Whatever.Test1.name());
    }

    @Test
    public void shouldParseAModelWithMoreThanOneFieldsValueTest() {
        final ToSerialize.WithTwoFields model = new ToSerialize.WithTwoFields();
        model.setFieldString("Test");
        model.setFieldInteger(2);

        final Optional<Document> document = mapper.toDocument(model);

        Assertions.assertThat(document.isPresent()).isTrue();
        Assertions.assertThat(document.get().getString("fieldString")).isEqualTo("Test");
        Assertions.assertThat(document.get().getInteger("fieldInteger")).isEqualTo(2);
    }
}