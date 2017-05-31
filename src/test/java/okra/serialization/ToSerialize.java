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