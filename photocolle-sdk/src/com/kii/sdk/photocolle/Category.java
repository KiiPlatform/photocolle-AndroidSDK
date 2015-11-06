package com.kii.sdk.photocolle;

/**
 * The type of category to select tags.
 */
public enum Category
{
    /** category type to select all tags. */
    ALL(0),
    /** category type to select person tags. */
    PERSON(1),
    /** category type to select event tags. */
    EVENT(2),
    /** category type to select favorite tags. */
    FAVORITE(3),
    /** category type to select place tags. */
    PLACEMENT(4),
    /** category type to select year month tags. */
    YEAR_MONTH(5);

    private final int number;

    private Category(int number) {
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
