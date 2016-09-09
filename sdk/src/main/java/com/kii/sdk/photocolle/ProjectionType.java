package com.kii.sdk.photocolle;

/**
 * The type of a projection.
 */
public enum ProjectionType
{
    /** the count of file. */
    FILE_COUNT(1),
    /** the detail information with all items. */
    ALL_DETAILS(2),
    /** the detail information without tag. */
    DETAILS_WITHOUT_TAGS(3);

    private final int number;

    private ProjectionType(int number) {
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
