package com.kii.sdk.photocolle;

/**
 * Thrown when failed to access stored AuthenticationContext.
 */
public class AuthenticationContextAccessException extends PhotocolleException {

    /**
     * The reason for accessing stored AuthenticationContext failed.
     */
    public static enum Reason {
        /** Fail to access AuthenticationContext. */
        ACCESS_FAILED
    }

    private static final long serialVersionUID = 1L;
    private Reason reason;

    AuthenticationContextAccessException(Reason reason) {
        super((Throwable)null);
        this.reason = reason;
    }

    /**
     * @return the reason.
     */
    public Reason getReason() {
        return this.reason;
    }

}
