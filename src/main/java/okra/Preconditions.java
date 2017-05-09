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