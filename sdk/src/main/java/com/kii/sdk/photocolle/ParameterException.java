package com.kii.sdk.photocolle;

/**
 * Thrown when parameter passed to method of PhotoColle was invalid.
 */
public class ParameterException extends IllegalArgumentException {

    /**
     * Reason of invalid parameter.
     */
    public static enum Reason {
        /** null is assigned to the parameter which can not accept null. */
        NULL_ASSIGNED,

        /** value of parameter is out of range. */
        OUT_OF_RANGE
    }

    private static final long serialVersionUID = 1L;
    private Reason reason;

    ParameterException(Reason reason, String message) {
        super(message);
        this.reason = reason;
    }

    /**
     * @return the reason
     */
    public Reason getReason() {
        return this.reason;
    }

    static ParameterException nullAssigned(String message) {
        return new ParameterException(Reason.NULL_ASSIGNED, message);
    }

    static ParameterException outOfRange(String message) {
        return new ParameterException(Reason.OUT_OF_RANGE, message);
    }

}
