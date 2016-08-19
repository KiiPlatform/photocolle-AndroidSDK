package com.kii.sdk.photocolle;

/**
 * The orientation of content.
 */
public enum Orientation
{
    /** 0 rotation */
    ROTATE_0(0),
    /** 90 rotation */
    ROTATE_90(90),
    /** 180 rotation */
    ROTATE_180(180),
    /** 270 rotation */
    ROTATE_270(270);

    private final int number;

    private Orientation(int number) {
        this.number = number;
    }

    /**
     * Get number.
     *
     * @return the number of rotation.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Get Orientation from int value.
     *
     * @param number the number of rotation.
     * @return the enum constant with the specified number.
     * @throws ParameterException if this enum type has no constant with
     * the specified number.
     */
    public static Orientation fromInt(int number) throws ParameterException {
        switch (number) {
            case 0:
                return ROTATE_0;
            case 90:
                return ROTATE_90;
            case 180:
                return ROTATE_180;
            case 270:
                return ROTATE_270;
            default:
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        String.format("unexptected number %d", number));
        }
    }
}
