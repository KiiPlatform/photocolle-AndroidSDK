package com.kii.sdk.photocolle;

/**
 * The type of sort.
 */
public enum SortType
{
    /** Sort of descending order by creation date time */
    CREATION_DATETIME_DESC(1),
    /** Sort of ascending order by creation date time */
    CREATION_DATETIME_ASC(2),
    /** Sort of descending order by modification date time */
    MODIFICATION_DATETIME_DESC(3),
    /** Sort of ascending order by modification date time */
    MODIFICATION_DATETIME_ASC(4),
    /** Sort of ascending order by upload date time */
    UPLOAD_DATETIME_ASC(5),
    /** Sort of descending order by upload date time */
    UPLOAD_DATETIME_DESC(6),
    /** Sort of descending order by score. */
    SCORE_DESC(7);

    private final int number;

    private SortType(int number) {
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

