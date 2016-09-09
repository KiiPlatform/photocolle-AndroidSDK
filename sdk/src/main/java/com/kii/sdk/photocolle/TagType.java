package com.kii.sdk.photocolle;

/**
 * The type of a tag.
 */
public enum TagType
{
    /** tag type for person. */
    PERSON(1),
    /** tag type for event. */
    EVENT(2),
    /** tag type for favorite. */
    FAVORITE(3),
    /** tag type for placement. */
    PLACEMENT(4),
    /** tag type for years. */
    YEAR_MONTH(5);

    private final int number;

    private TagType(int number) {
        this.number = number;
    }

    /**
     * Get number of type.
     *
     * @return number of type.
     */
    public int getNumber() {
        return this.number;
    }
}
