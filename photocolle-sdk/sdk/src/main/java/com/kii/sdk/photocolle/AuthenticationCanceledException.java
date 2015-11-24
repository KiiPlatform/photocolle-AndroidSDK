package com.kii.sdk.photocolle;

/**
 * Thrown when authentication is canceled by user.
 */
public class AuthenticationCanceledException extends PhotocolleException {

    private static final long serialVersionUID = 1L;
    static final String ACCESS_DENIED = "access_denied";

    AuthenticationCanceledException() {
        super((Throwable)null);
    }

}
