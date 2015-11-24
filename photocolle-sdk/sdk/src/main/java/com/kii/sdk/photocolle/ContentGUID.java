package com.kii.sdk.photocolle;

/**
 * Guid of a content.
 */
public class ContentGUID implements DTO
{
    private final String string;

    /**
     * Create a new guid of a content.
     *
     * @param string string representation of guid which are retrieved by
     * {@link ContentGUID#getString()}.
     * @throws ParameterException One or more arguments are invalid.
     */
    public ContentGUID(String string) throws ParameterException {
        if (string == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "string must not be null.");
        }
        this.string = string;
    }

    /**
     * Get guid of a content by String.
     *
     * @return string representation of guid.
     */
    public String getString() {
        return this.string;
    }
}
