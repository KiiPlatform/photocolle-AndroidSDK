package com.kii.sdk.photocolle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

/**
 * Execute authentication.
 */
public class Authority {

    private static final String TAG = "Authority";

    private static final URL DEFAULT_AUTH_URL = MiscUtils.toUrl(
            "https://api.smt.docomo.ne.jp/cgi11d/authorization");
    private static final URL DEFAULT_TOKEN_URL = MiscUtils.toUrl(
            "https://api.smt.docomo.ne.jp/cgi12/token");

    private final static HttpClientFactory httpClientFactory
        = new HttpClientFactory();

    /**
     * Invoke authentication process to get AuthenticationContext object.
     *
     * <p>
     * Access token is saved with specified storeKey. If valid access token
     * is stored with specified storedKey, this method skips authentication
     * and uses stored access token. Otherwise this method authenticate with
     * PhotoColle network by OAuth2.0. After success of authentication,
     * PhotoColleSDK saves retrieved access token with specified storeKey.
     * </p>
     *
     * <p>
     * PhotoColleSDK refreshes access token if the stored token is going to
     * be expired. Calling this method or method in {@link PhotoColle}
     * requires authentication triggers refresh token. After the refresh
     * token succeeded, stored token will be overwritten with the storeKey
     * given to this method.
     * </p>
     *
     * <p>
     * If refresh token fails in this method, this method start to
     * authenticate with PhotoColle network.
     * </p>
     *
     * <p>
     * If refresh token fails in the method of {@link PhotoColle},
     * PhotoColleSDK removes stored access token bound to specified storeKey.
     * </p>
     *
     * <p>
     * Applications must need to request permissions with scope. Applications
     * must send at least one scope to Docomo authentication server.
     * </p>
     *
     * @param context Android context (Activity or so). must not be null.
     * @param clientId The client id string which is issued for your
     * service. must not be null or empty.
     * @param clientSecret The client secret string which is issued for
     * your service. must not be null or empty.
     * @param redirectUri The redirect URI which you registered. must not be
     * null or empty.
     * @param predefinedScopes Applications request permissions to Docomo
     * authentication server by {@link Scope Scopes}. One or more {@link
     * Scope Scopes} can be set. The {@link Scope Scopes} is defined in <A
     * HREF="https://dev.smt.docomo.ne.jp/pdf/API_common_reference_v2.0.0.pdf">
     * docomo Developer support API common reference version 2.0.0</A>. If
     * extendedScopes is null or empty, then this parameter must not be null
     * or empty.
     * @param extendedScopes Applications request permissions to Docomo
     * authentication server by scopes. If applications need to request
     * scopes defined after <A
     * HREF="https://dev.smt.docomo.ne.jp/pdf/API_common_reference_v2.0.0.pdf">
     * docomo Developer support API common reference version 2.0.0</A>,
     * applications can request these scopes with String values defined in a
     * newest specification.  If predefinedScopes is null or empty, then this
     * parameter must not be null or empty. Elements of this array must not
     * be null or empty string. If same scope strings in this array, one of
     * the string is ignored.
     * @param storeKey A key to save and load {@link AuthenticationContext}
     * automatically. If null or empty, automatic saving and loading does not
     * occur.
     * @param callback The callback for receiving a result of authentication
     * process. must not be null.
     * @throws ParameterException One or more arguments are invalid.
     */
    public static void authenticate(
            final Context context,
            final String clientId,
            final String clientSecret,
            final String redirectUri,
            final EnumSet<Scope> predefinedScopes,
            final String[] extendedScopes,
            final String storeKey,
            final AuthenticateCallback callback)
        throws ParameterException
    {
         if (context == null) {
             throw ParameterException.nullAssigned("context must no be null.");
         } else if (clientId == null) {
             throw ParameterException.nullAssigned("clientId must no be null.");
         } else if (MiscUtils.isEmptyString(clientId)) {
             throw ParameterException.outOfRange("clientId must no be empty.");
         } else if (clientSecret == null) {
             throw ParameterException.nullAssigned(
                 "clientSecret must no be null.");
         } else if (MiscUtils.isEmptyString(clientSecret)) {
             throw ParameterException.outOfRange(
                 "clientSecret must no be empty.");
         } else if (redirectUri == null) {
             throw ParameterException.nullAssigned(
                 "redirectUri must no be null.");
         } else if (MiscUtils.isEmptyString(redirectUri)) {
             throw ParameterException.outOfRange(
                 "redirectUri must no be empty.");
         } else if (MiscUtils.isNullOrEmpty(predefinedScopes) &&
                 MiscUtils.isNullOrEmpty(extendedScopes)) {
             throw ParameterException.outOfRange(
                 "At least, predefinedScopes and/or extendedScopes must not be null or empty.");
         } else if (MiscUtils.containsNullOrEmpty(extendedScopes)) {
             throw ParameterException.outOfRange(
                 "extendedScopes must not contain null or empty.");
         }  else if (callback == null) {
             throw ParameterException.nullAssigned("callback must no be null.");
         }

        final Set<String> extendedScopesSet = new HashSet<String>();
        if (extendedScopes != null) {
            for (String scope : extendedScopes) {
                if (!extendedScopesSet.contains(scope)) {
                    extendedScopesSet.add(scope);
                }
            }
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                authenticateInner(context, clientId, clientSecret,
                        redirectUri, predefinedScopes, extendedScopesSet,
                        storeKey, callback);
            }
        });
        thread.start();
    }

    /**
     * Invoke refresh token process to update AuthenticationContext object.
     *
     * This method refreshes access token with PhotoColle network by
     * OAuth2.0. If refresh token process succeed and automatic saving is
     * enabled on authenticationContext, then updated authenticationContext
     * is saved automatically. AuthenticationContext satisfying one or more
     * following conditions is automatic saving enabled.
     *
     * <UL>
     *  <LI>AuthenticationContext created by {@link
     *  Authority#authenticate(Context, String, String, String, EnumSet,
     *  String[], String, AuthenticateCallback)} with valid
     *  storeKey.</LI>
     *  <LI>AuthenticationContext loaded by {@link
     *  AuthenticationContext#loadFrom(Context, String, String, String)}</LI>
     *  <LI>AuthenticationContext saved by {@link
     *  AuthenticationContext#saveTo(Context, String)}</LI>
     * </UL>
     *
     * You can check remaining time of access token by
     * {@link AuthenticationContext#getRemainingTime(TimeUnit)}.
     *
     * @param authenticationContext the authentication context to be
     * updated. must not be null. authenticationContext loaded by {@link
     * AuthenticationContext#loadFrom(Context, String)} can not be refreshed.
     * @param callback The callback for receiving a result refresh token
     * process. must not be null.
     * @throws ParameterException One or more arguments are invalid.
     */
    public static void refreshToken(
            final AuthenticationContext authenticationContext,
            final AuthenticateCallback callback)
        throws ParameterException
    {
        if (authenticationContext == null) {
            throw ParameterException.nullAssigned(
                    "authenticationContext must no be null.");
        } else if (TextUtils.isEmpty(authenticationContext.getClientId()) ||
                TextUtils.isEmpty(authenticationContext.getClientSecret())) {
            throw ParameterException.outOfRange(
                    "AuthenticateContext which loaded by deprecated loadBy method can't refresh.");
        } else if (callback == null) {
            throw ParameterException.nullAssigned("callback must no be null.");
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Context context = authenticationContext.getAppContext();
                try {
                    refreshTokenInner(authenticationContext);
                    postToMainLooper(context, callback, authenticationContext,
                            null);
                } catch (NoRefreshTokenException e) {
                    postToMainLooper(context, callback, null, e);
                } catch (HttpException e) {
                    postToMainLooper(context, callback, null, e);
                } catch (InvalidTokenException e) {
                    postToMainLooper(context, callback, null, e);
                } catch (ResponseBodyParseException e) {
                    postToMainLooper(context, callback, null, e);
                } catch (ConnectionException e) {
                    postToMainLooper(context, callback, null, e);
                }
            }
        });
        thread.start();
    }

    private static void refreshTokenInner(AuthenticationContext context)
        throws NoRefreshTokenException, ResponseBodyParseException,
            InvalidTokenException, HttpException, ConnectionException
    {
        if (context.canRefreshToken()) {
            try {
                refreshToken(context);
            } catch (UnsupportedEncodingException e) {
                throw new ResponseBodyParseException(e);
            } catch (IOException e) {
                throw new ConnectionException(e);
            }
            return;
        } else {
            throw new NoRefreshTokenException(
                    "AuthenticationContext can't refresh.");
        }
    }

    private static void postToMainLooper(
            Context context,
            final AuthenticateCallback callback,
            final AuthenticationContext authContext,
            final Exception exception)
    {
        new Handler(context.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onAuthenticated(authContext, exception);
            }
        });
    }

    private static void authenticateInner(
            final Context context,
            final String clientId,
            final String clientSecret,
            final String redirectUri,
            EnumSet<Scope> predefinedScopes,
            Set<String> extendedScopes,
            final String storeKey,
            final AuthenticateCallback callback)
    {
        if (tryToUseStoredAuthenticationContext(context, clientId,
                clientSecret, storeKey, callback)) {
            return;
        }

        // pack data sent to server.
        Bundle authData = generateAuthData(context, clientId, clientSecret,
                redirectUri, predefinedScopes, extendedScopes);

        // add data used in PhotoColleSDK only.
        final String nonce = generateNonce(context);
        authData.putString(AuthHiddenActivity.KEY_NONCE, nonce);

        IntentFilter filter = new IntentFilter(
                AuthHiddenActivity.ACTION_AUTH_RESULT);
        context.registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if (!isMyIntent(intent)) {
                    return;
                }

                context.unregisterReceiver(this);

                // Notify exception to an application.
                Exception exception = (Exception)intent.getSerializableExtra(
                        AuthHiddenActivity.KEY_EXCEPTION);
                if (exception != null) {
                    callback.onAuthenticated(null, exception);
                    return;
                }

                // Notify AuthenticationException to an application.
                String errorCode = intent.getStringExtra(
                        AuthHiddenActivity.KEY_ERROR_CODE);
                if (errorCode != null) {
                    callback.onAuthenticated(null, toAuthenticationException(
                                errorCode));
                    return;
                }

                // Notify AuthenticationContext to an application.
                AuthorizationData data = intent.getParcelableExtra(
                        AuthHiddenActivity.KEY_AUTHORIZATION_DATA);
                if (data != null) {
                    AuthenticationContext authContext =
                            new AuthenticationContext(context,
                                    data.accessToken, data.expiredDate,
                                    data.refreshToken, storeKey, clientId,
                                    clientSecret);
                    try {
                        authContext.save();
                    } catch (AuthenticationContextAccessException e) {
                        Log.v(TAG, "save is failed.", e);
                    }
                    callback.onAuthenticated(authContext, null);
                    return;
                }

                // If process reached here, this is programming error.
                throw new RuntimeException("Unexpected state.");
            }

            private boolean isMyIntent(Intent intent) {
                if (!AuthHiddenActivity.ACTION_AUTH_RESULT.equals(
                            intent.getAction())) {
                    return false;
                } else if (!nonce.equals(intent.getStringExtra(
                                    AuthHiddenActivity.KEY_NONCE))) {
                    return false;
                }
                return true;
            }
        }, filter);

        // start authentication activity.
        Intent intent = new Intent(context, AuthHiddenActivity.class);
        intent.putExtras(authData);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private static boolean tryToUseStoredAuthenticationContext(
            Context context,
            String clientId,
            String clientSecret,
            String storeKey,
            final AuthenticateCallback callback)
    {
        boolean retval = false;
        if (!TextUtils.isEmpty(storeKey)) {
            try {
                if (AuthenticationContext.hasSaved(context, storeKey)) {
                    final AuthenticationContext authContext =
                            AuthenticationContext.loadFrom(
                                    context, storeKey, clientId, clientSecret);
                    RefreshTokenResult result = tryToRefreshToken(authContext);
                    switch (result) {
                        case ISNOT_EXPIRED:
                        case REFRESHED:
                            new Handler(context.getMainLooper()).post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        callback.onAuthenticated(authContext,
                                                null);
                                    }
                                });
                            retval = true;
                            break;
                        case EXPIRED_BUT_CANNOT_REFRESH:
                            retval = false;
                            break;
                    }
                }
            } catch (IOException e) {
                Log.e(TAG, "refreshToken failed:", e);
                // continue authenticate.
            } catch (HttpException e) {
                Log.e(TAG, "refreshToken failed:", e);
                // continue authenticate.
            } catch (AuthenticationContextAccessException e) {
                // no throw this exception.
                // continue authenticate.
            } catch (AuthenticationContextNotFoundException e) {
                // continue authenticate.
            } catch (InvalidTokenException e) {
                // continue authenticate.
            }
        }
        return retval;
    }

    private static Bundle generateAuthData(
            Context context,
            String clientId,
            String clientSecret,
            String redirectUri,
            EnumSet<Scope> predefinedScopes,
            Set<String> extendedScopes)
    {
        if (context == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "context must no be null.");
        } else if (clientId == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "clientId must no be null.");
        } else if (clientSecret == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "clientSecret must no be null.");
        } else if (redirectUri == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "redirectUri must no be null.");
        }  else if (MiscUtils.isNullOrEmpty(predefinedScopes) &&
                 MiscUtils.isNullOrEmpty(extendedScopes)) {
             throw ParameterException.outOfRange(
                 "At least, predefinedScopes and/or extendedScopes must not be null or empty.");
         } else if (MiscUtils.containsNullOrEmpty(extendedScopes)) {
             throw ParameterException.outOfRange(
                 "extendedScopes must not contain null or empty.");
         }

        // Server redirect scheme://host/?code=... whenever path is empty.
        // We need to add '/' to the end.
        String path = Uri.parse(redirectUri).getPath();
        if (path != null && path.length() == 0) {
            redirectUri = redirectUri + "/";
        }

        HashSet<String> scopes = new HashSet<String>();
        if (predefinedScopes != null) {
            for (Scope s : predefinedScopes) {
                scopes.add(s.getLabel());
            }
        }
        if (extendedScopes != null) {
            for (String s : extendedScopes) {
                if (!TextUtils.isEmpty(s) && !scopes.contains(s)) {
                    scopes.add(s);
                }
            }
        }

        Bundle retval = new Bundle();
        retval.putSerializable(AuthHiddenActivity.KEY_AUTH_URL,
                getAuthURL(context));
        retval.putSerializable(AuthHiddenActivity.KEY_TOKEN_URL,
                getTokenURL(context));
        retval.putString(AuthHiddenActivity.KEY_CLIENT_ID, clientId);
        retval.putString(AuthHiddenActivity.KEY_CLIENT_SECRET, clientSecret);
        retval.putString(AuthHiddenActivity.KEY_REDIRECT_URI, redirectUri);
        retval.putStringArray(AuthHiddenActivity.KEY_SCOPES,
                scopes.toArray(new String[0]));
        return retval;
    }

    static RefreshTokenResult tryToRefreshToken(
            AuthenticationContext context)
        throws ClientProtocolException, UnsupportedEncodingException,
                IOException, HttpException, InvalidTokenException
    {
        if (!context.isTokenExpired()) {
            return RefreshTokenResult.ISNOT_EXPIRED;
        } else if (!context.canRefreshToken()) {
            return RefreshTokenResult.EXPIRED_BUT_CANNOT_REFRESH;
        }
        refreshToken(context);
        return RefreshTokenResult.REFRESHED;
    }

    static void refreshToken(final AuthenticationContext context)
        throws ClientProtocolException, UnsupportedEncodingException,
                IOException, HttpException, InvalidTokenException
    {
        HttpClient client = httpClientFactory.createClient();
        try {
            final ArrayList<Exception> exceptions = new ArrayList<Exception>(1);
            AuthorizationData data = client.execute(
                    createRefreshTokenRequest(context),
                    new ResponseHandler<AuthorizationData>() {

                        @Override
                        public AuthorizationData handleResponse(HttpResponse response)
                        {
                            AuthorizationData retval = null;
                            try {
                                retval = parseRefreshTokenResponse(response);
                            } catch (Exception e) {
                                exceptions.add(e);
                            }
                            return retval;
                        }
                    });

            if (!exceptions.isEmpty()) {
                Exception exception = exceptions.get(0);
                if (exception instanceof HttpException) {
                    throw (HttpException)exception;
                } else if (exception instanceof InvalidTokenException) {
                    throw (InvalidTokenException)exception;
                } else if (exception instanceof IOException) {
                    throw (IOException)exception;
                } else {
                    Log.e(TAG, "unsupported exception:", exception);
                    throw new RuntimeException(exception);
                }
            } else {
                context.update(data);
                context.save();
            }
        } catch (AuthenticationContextAccessException e) {
            // failed to save AuthenticationContext. Ignore this exception.
            Log.v(TAG, "save is failed.", e);
        }
    }

    private static HttpUriRequest createRefreshTokenRequest(
            AuthenticationContext context)
        throws UnsupportedEncodingException
    {
        HttpPost retval = new HttpPost(Authority.getTokenURL(
                context.getAppContext()).toString());
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Collections.addAll(params,
                new BasicNameValuePair("grant_type", "refresh_token"),
                new BasicNameValuePair("refresh_token",
                        context.getRefreshToken()));
        retval.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        retval.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        retval.setHeader("Authorization",
                AuthHiddenActivity.createAuthorization(
                        context.getClientId(), context.getClientSecret()));
        return retval;
    }

    private static AuthorizationData parseRefreshTokenResponse(
            HttpResponse response)
        throws IOException, HttpException, InvalidTokenException
    {
        StatusLine statusLine = response.getStatusLine();
        String body = null;
        try {
            body = EntityUtils.toString(response.getEntity(), "UTF-8");;
            switch (statusLine.getStatusCode()) {
                case 200:
                    JSONObject json = new JSONObject(body);
                    AuthorizationData data = AuthorizationData.create(json);
                    if (data != null) {
                        return data;
                    } else {
                        throw new HttpException(statusLine.getStatusCode(),
                                statusLine.getReasonPhrase(), body);
                    }
                case 400:
                    String error = (new JSONObject(body)).optString("error", null);
                    if ("invalid_grant".equals(error)) {
                        throw new InvalidTokenException();
                    }
                case 401:
                case 500:
                case 503:
                default:
                    throw new HttpException(statusLine.getStatusCode(),
                            statusLine.getReasonPhrase(), body);
            }
        } catch (ParseException e) {
            throw new HttpException(statusLine.getStatusCode(),
                    statusLine.getReasonPhrase(), body);
        } catch (JSONException e) {
            throw new HttpException(statusLine.getStatusCode(),
                    statusLine.getReasonPhrase(), body);
        }
    }

    private static String generateNonce(Context context) {
        return context.getApplicationContext().getPackageName() + "."
                + Long.toString(System.currentTimeMillis());
    }

    private static URL getTokenURL(Context context) {
        return getURL(MiscUtils.getTokenBaseURL(context), DEFAULT_TOKEN_URL);
    }

    private static URL getAuthURL(Context context) {
        return getURL(MiscUtils.getAuthorityBaseURL(context), DEFAULT_AUTH_URL);
    }

    private static URL getURL(URL baseURL, URL defaultURL) {
        try {
            if (baseURL == null) {
                return defaultURL;
            }
            return new URL(baseURL, defaultURL.getFile());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PhotocolleException toAuthenticationException(
            String errorCode)
    {
        if (errorCode.equals(AuthenticationCanceledException.ACCESS_DENIED)) {
            return new AuthenticationCanceledException();
        }
        // TODO: set details.
        return new AuthenticationException(
            AuthenticationException.Reason.toReason(errorCode), null);
    }

}
