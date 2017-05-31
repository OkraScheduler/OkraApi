package okra.serialization;

import okra.util.DateUtil;
import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SerializerTest {

    private Serializer serializer;

    @Before
    public void setUp() {
        serializer = new Serializer();
    }

    @Test
    public void shouldParseADocumentWithStringFieldValueTest() {
        final Document document = Mockito.mock(Document.class);

        Mockito.when(document.getString("field")).thenReturn("StringValue");
        final Optional<ToSerialize.WithStringField> modelOpt = serializer
                .fromDocument(ToSerialize.WithStringField.class, document);

        Assertions.assertThat(modelOpt.isPresent()).isTrue();
        Assertions.assertThat(modelOpt.get().getField()).isEqualTo("StringValue");
    }

    @Test
    public void shouldParseADocumentWithIntegerFieldValueTest() {
        final Document document = Mockito.mock(Document.class);

        Mockito.when(document.getInteger("field")).thenReturn(10);
        final Optional<ToSerialize.WithIntegerField> modelOpt = serializer
                .fromDocument(ToSerialize.WithIntegerField.class, document);

        Assertions.assertThat(modelOpt.isPresent()).isTrue();
        Assertions.assertThat(modelOpt.get().getField()).isEqualTo(10);
    }

    @Test
    public void shouldParseADocumentWithDoubleFieldValueTest() {
        final Document document = Mockito.mock(Document.class);

        Mockito.when(document.getDouble("field")).thenReturn(2d);
        final Optional<ToSerialize.WithDoubleField> modelOpt = serializer
                .fromDocument(ToSerialize.WithDoubleField.class, document);

        Assertions.assertThat(modelOpt.isPresent()).isTrue();
        Assertions.assertThat(modelOpt.get().getField()).isEqualTo(2d);
    }

    @Test
    public void shouldParseADocumentWithBooleanFieldValueTest() {
        final Document document = Mockito.mock(Document.class);

        Mockito.when(document.getBoolean("field")).thenReturn(true);
        final Optional<ToSerialize.WithBooleanField> modelOpt = serializer
                .fromDocument(ToSerialize.WithBooleanField.class, document);

        Assertions.assertThat(modelOpt.isPresent()).isTrue();
        Assertions.assertThat(modelOpt.get().getField()).isEqualTo(true);
    }

    @Test
    public void shouldParseADocumentWithLocalDateTimeFieldValueTest() {
        final Document document = Mockito.mock(Document.class);
        final Date now = new Date();

        Mockito.when(document.getDate("field")).thenReturn(now);
        final Optional<ToSerialize.WithLocalDateTimeField> modelOpt = serializer
                .fromDocument(ToSerialize.WithLocalDateTimeField.class, document);

        Assertions.assertThat(modelOpt.isPresent()).isTrue();
        Assertions.assertThat(modelOpt.get().getField()).isEqualTo(DateUtil.toLocalDateTime(now));
    }

    @Test
    public void shouldParseADocumentWithDateFieldValueTest() {
        final Document document = Mockito.mock(Document.class);
        final Date now = new Date();

        Mockito.when(document.getDate("field")).thenReturn(now);
        final Optional<ToSerialize.WithDateField> modelOpt = serializer
                .fromDocument(ToSerialize.WithDateField.class, document);

        Assertions.assertThat(modelOpt.isPresent()).isTrue();
        Assertions.assertThat(modelOpt.get().getField()).isEqualTo(now);
    }

    @Test
    public void shouldParseADocumentWithEnumerationFieldValueTest() {
        final Document document = Mockito.mock(Document.class);

        Mockito.when(document.getString("field"))
                .thenReturn(ToSerialize.WithEnumerationField.Whatever.Test1.name());

        final Optional<ToSerialize.WithEnumerationField> modelOpt = serializer
                .fromDocument(ToSerialize.WithEnumerationField.class, document);

        Assertions.assertThat(modelOpt.isPresent()).isTrue();
        Assertions.assertThat(modelOpt.get().getField())
                .isEqualTo(ToSerialize.WithEnumerationField.Whatever.Test1);
    }

    @Test
    public void shouldParseADocumentWithMoreThanOneFieldsValueTest() {
        final Document document = Mockito.mock(Document.class);

        Mockito.when(document.getString("fieldString")).thenReturn("StringValue");
        Mockito.when(document.getInteger("fieldInteger")).thenReturn(3);

        final Optional<ToSerialize.WithTwoFields> modelOpt = serializer
                .fromDocument(ToSerialize.WithTwoFields.class, document);

        Assertions.assertThat(modelOpt.isPresent()).isTrue();
        Assertions.assertThat(modelOpt.get().getFieldString()).isEqualTo("StringValue");
        Assertions.assertThat(modelOpt.get().getFieldInteger()).isEqualTo(3);
    }
}