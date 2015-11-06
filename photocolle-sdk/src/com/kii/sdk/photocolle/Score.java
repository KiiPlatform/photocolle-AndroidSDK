package com.kii.sdk.photocolle;


/**
 * The type of 5 step evaluation score.
 */
public enum Score
{
    /** the lowest score */
    SCORE_1(1),
    /** the low score */
    SCORE_2(2),
    /** the middle score */
    SCORE_3(3),
    /** the high score */
    SCORE_4(4),
    /** the highest score */
    SCORE_5(5);

    private final int number;

    private Score(int number) {
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

    /**
     * Get the score from int value.
     *
     * @param number source of score.
     * @return the enum constant with the specified number.
     * @throws ParameterException if this enum type has no constant with
     * the specified number.
     */
    public static Score fromInt(int number) throws ParameterException {
        switch (number) {
            case 1:
                return SCORE_1;
            case 2:
                return SCORE_2;
            case 3:
                return SCORE_3;
            case 4:
                return SCORE_4;
            case 5:
                return SCORE_5;
            default:
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        String.format("unexptected number %d", number));
        }
    }
}
