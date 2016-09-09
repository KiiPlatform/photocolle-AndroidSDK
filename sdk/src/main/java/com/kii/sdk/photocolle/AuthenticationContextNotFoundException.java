package com.kii.sdk.photocolle;

/**
 * Thrown when AuthenticationContext does not stored.
 */
public class AuthenticationContextNotFoundException
        extends PhotocolleException
{

    private static final long serialVersionUID = 1L;

    AuthenticationContextNotFoundException() {
        super((Throwable)null);
    }

}
