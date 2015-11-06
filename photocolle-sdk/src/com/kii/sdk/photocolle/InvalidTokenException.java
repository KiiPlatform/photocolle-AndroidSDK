package com.kii.sdk.photocolle;

/**
 * Thrown when token is invalid. User need to authenticate again.
 */
public class InvalidTokenException extends PhotocolleException {

    private static final long serialVersionUID = 1L;

    InvalidTokenException() {
        super((Throwable)null);
    }

}
