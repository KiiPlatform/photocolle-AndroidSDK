package com.kii.sdk.photocolle;

/**
 * The type of a file.
 */
public enum FileType
{
    /** file type for all. */
    ALL(0, "All"),
    /** file type for image. */
    IMAGE(1, "Image"),
    /** file type for video. */
    VIDEO(2, "Video"),
    /** file type for slide movie. */
    SLIDE_MOVIE(3, "SlideMovie");

    private final int number;
    private final String label;

    private FileType(int number, String label) {
        this.number = number;
        this.label = label;
    }

    /**
     * Get number of type.
     *
     * @return number of type.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Get label of type.
     *
     * @return label of type.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Get FileType from int value.
     *
     * @param number source of file type.
     * @return the enum constant with the specified number.
     * @throws ParameterException the number does not match any enum value.
     */
    public static FileType fromInt(int number) {
        switch (number) {
            case 0:
                return ALL;
            case 1:
                return IMAGE;
            case 2:
                return VIDEO;
            case 3:
                return SLIDE_MOVIE;
            default:
                throw new ParameterException(
                    ParameterException.Reason.OUT_OF_RANGE,
                    String.format("unexptected number %d", number));
        }
    }
}
