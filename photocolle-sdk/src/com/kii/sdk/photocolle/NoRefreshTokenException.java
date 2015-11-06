package com.kii.sdk.photocolle;

/**
 * Thrown when refreshing access token is failed because of {@link
 * AuthenticationContext} object.
 *
 * This exception passed to {@link
 * AuthenticateCallback#onAuthenticated(AuthenticationContext, Exception)} when
 * an argument of authenticationContext on {@link
 * Authority#refreshToken(AuthenticationContext, AuthenticateCallback)} lacks
 * refresh token.
 */
public class NoRefreshTokenException extends PhotocolleException {

    private static final long serialVersionUID = 5207114051931088242L;

    NoRefreshTokenException(String msg) {
        super(msg);
    }

}
