package com.kii.sdk.photocolle;

/**
 * The type of a content resized or not.
 */
public enum ResizeType
{
    /** original content */
    ORIGINAL(1),
    /** resized image. */
    RESIZED_IMAGE(2),
    /** resized video. */
    RESIZED_VIDEO(3);

    private final int number;

    private ResizeType(int number) {
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
