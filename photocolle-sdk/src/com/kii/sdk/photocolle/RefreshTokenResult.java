package com.kii.sdk.photocolle;

/**
 * Return value for Authority#tryToRefreshToken() method.
 */
enum RefreshTokenResult {
    /**
     * This value means that tryToRefreshToken() execute refresh token and
     * the refresh token is succeed.
     */
    REFRESHED,

    /**
     * This value means that access token is not expired, so
     * tryToRefreshToken() does nothing and the AuthenticationContext can be
     * used.
     */
    ISNOT_EXPIRED,

    /**
     * This value means that access token is expired but tryToRefreshToken()
     * can not refresh token. i.e, there is no refresh token string or
     * clientId and/or clientSecret are null.
     */
    EXPIRED_BUT_CANNOT_REFRESH;

}
