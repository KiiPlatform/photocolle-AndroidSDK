package com.kii.sdk.photocolle;

/**
 * The callback interface of authenticate.
 */
public interface AuthenticateCallback {

    /**
     * Call when authenticate finished.
     *
     * <P>
     * This callback method is called on main thread.
     * </P>
     * 
     * @param context set AuthenticationContext object if authentication
     * succeed. otherwise null. If this callback is called by {@link
     * Authority#refreshToken(AuthenticationContext, AuthenticateCallback)},
     * context is same object as an argument of {@link
     * Authority#refreshToken(AuthenticationContext, AuthenticateCallback)}
     * @param exception set one of the following exceptions if authentication
     * is failed. otherwise null.
     * <ul>
     *  <li>{@link com.kii.sdk.photocolle.AuthenticationCanceledException AuthenticationCanceledException}</li>
     *  <li>{@link com.kii.sdk.photocolle.AuthenticationException AuthenticationException}</li>
     *  <li>{@link com.kii.sdk.photocolle.ConnectionException ConnectionException}</li>
     *  <li>{@link com.kii.sdk.photocolle.HttpException HttpException}</li>
     *  <li>{@link com.kii.sdk.photocolle.HttpException InvalidTokenException}</li>
     *  <li>{@link com.kii.sdk.photocolle.ResponseBodyParseException ResponseBodyParseException}</li>
     *  <li>{@link NoRefreshTokenException}</li>
     * </ul>
     * {@link AuthenticationCanceledException} is only passed when this
     * callback is called by {@link Authority#authenticate(Context, String,
     * String, String, EnumSet, String[], String,
     * AuthenticateCallback)}. {@link InvalidTokenException} and {@link
     * NoRefreshTokenException} are only passed when this callback is called
     * by {@link Authority#refreshToken(AuthenticationContext,
     * AuthenticateCallback)}. Other exceptions are passed both methods.
     */
    void onAuthenticated(
            AuthenticationContext context,
            Exception exception);
}
