package com.kii.sdk.photocolle;

import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Context of an authentication.
 */
public class AuthenticationContext {

    private static final String PHOTOCOLLE_PREF = "photocollePref";
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String EXPIRED_DATE = "expiredDate";
    private static final String REFRESH_TOKEN = "refreshToken";

    private final Context appContext;
    private String accessToken;
    private long expiredDate;
    private String refreshToken;
    private String storeKey;
    private final String clientId;
    private final String clientSecret;

    AuthenticationContext(
            Context context,
            String accessToken,
            long expiredDate,
            String refreshToken,
            String storeKey,
            String clientId,
            String clientSecret)
    {
        if (context == null) {
            throw ParameterException.nullAssigned("context must not be null.");
        } else if (accessToken == null) {
            throw ParameterException.nullAssigned(
                "accessToken must not be null.");
        } else if (MiscUtils.isEmptyString(accessToken)) {
            throw ParameterException.outOfRange(
                "accessToken must not be empty.");
        } else if (expiredDate <= 0L) {
            throw ParameterException.outOfRange(
                "expiredDate must be positive.");
        } else if (clientId == null) {
            throw ParameterException.nullAssigned("clientId must not be null.");
        } else if (MiscUtils.isEmptyString(clientId)) {
            throw ParameterException.outOfRange("clientId must not be empty.");
        } else if (clientSecret == null) {
            throw ParameterException.nullAssigned(
                "clientSecret must not be null.");
        } else if (MiscUtils.isEmptyString(clientSecret)) {
            throw ParameterException.outOfRange(
                "clientSecret must not be empty.");
        }

        this.appContext = context.getApplicationContext();
        this.accessToken = accessToken;
        this.expiredDate = expiredDate;
        this.refreshToken = refreshToken;
        this.storeKey = storeKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    // This constructor is only for loadFrom(Context, String).
    // This constructor allows to set null to clientId and clientSecret
    // however, after SDK version 1.0.0, that is not allowed.
    // This constructor is only for backward compatibility.
    private AuthenticationContext(
            Context context,
            String accessToken,
            long expiredDate,
            String refreshToken,
            String storeKey)
    {
        if (context == null) {
            throw ParameterException.nullAssigned("context must not be null");
        } else if (accessToken == null) {
            throw ParameterException.nullAssigned(
                "accessToken must not be null");
        } else if (MiscUtils.isEmptyString(accessToken)) {
            throw ParameterException.outOfRange(
                "accessToken must not be empty.");
        } else if (expiredDate <= 0L) {
            throw ParameterException.outOfRange(
                "expiredDate must be positive.");
        }

        this.appContext = context.getApplicationContext();
        this.accessToken = accessToken;
        this.expiredDate = expiredDate;
        this.refreshToken = refreshToken;
        this.storeKey = storeKey;
        this.clientId = null;
        this.clientSecret = null;
    }

    /**
     * Check whether {@link AuthenticationContext AuthenticationContext} has
     * stored or not.
     *
     * <P>
     * This method uses SharedPreferences. If you access this method from
     * main thread, you will see the disk read violation on StrictMode.
     * </P>
     *
     * @param context context of an application. must not be null.
     * @param key a key to specify an {@link AuthenticationContext
     * AuthenticationContext} object to check. must not be null or empty.
     * @return {@link AuthenticationContext AuthenticationContext} object
     * exists or not.
     * @throws AuthenticationContextAccessException fail to access stored
     * {@link AuthenticationContext AuthenticationContext}.
     * @throws ParameterException One or more arguments are invalid.
     */
    public static boolean hasSaved(
            Context context,
            String key)
        throws AuthenticationContextAccessException
    {
        if (context == null) {
            throw ParameterException.nullAssigned("context must not be null.");
        } else if (key == null) {
            throw ParameterException.nullAssigned("key must not be null.");
        } else if (MiscUtils.isEmptyString(key)) {
            throw ParameterException.outOfRange("key must not be empty.");
        }
        String accessToken = getSharedPreferences(context).getString(
                getKey(key, ACCESS_TOKEN), null);
        long expiredDate = getSharedPreferences(context).getLong(
                getKey(key, EXPIRED_DATE), 0L);
        String refreshToken = getSharedPreferences(context).getString(
                getKey(key, REFRESH_TOKEN), null);
        if (accessToken == null || expiredDate == 0L) {
            return false;
        }
        return true;
    }

    /**
     * Load {@link AuthenticationContext AuthenticationContext} object.
     *
     * <P>
     * This method uses SharedPreferences. If you access this method from
     * main thread, you will see the disk read violation on StrictMode.
     * </P>
     *
     * @param context context of an application. must not be null.
     * @param key a key to specify an {@link AuthenticationContext
     * AuthenticationContext} object to load. must not be null or empty.
     * @return loaded {@link AuthenticationContext AuthenticationContext}
     * object.
     * @throws AuthenticationContextAccessException fail to access stored
     * AuthenticationContext
     * @throws AuthenticationContextNotFoundException target data does not
     * exist.
     * @throws ParameterException One or more arguments are invalid.
     * @deprecated {@link Authority#authenticate(Context, String, String,
     * String, DisplayType, String, AuthenticateCallback) authenticate()}
     * loads {@link AuthenticationContext AuthenticationContext}
     * automatically if saved. Applications should use {@link
     * Authority#authenticate(Context, String, String, String, DisplayType,
     * String, AuthenticateCallback) authenticate()}. If applications need to
     * load {@link AuthenticationContext} by itself, Applications should use
     * {@link AuthenticationContext#loadFrom(Context, String, String, String)
     * loadFrom(Context, String, String, String)} instead of this method..
     */
    @Deprecated
    public static AuthenticationContext loadFrom(
            Context context,
            String key)
        throws AuthenticationContextAccessException,
            AuthenticationContextNotFoundException
    {
        if (context == null) {
            throw ParameterException.nullAssigned("context must not be null.");
        } else if (key == null) {
            throw ParameterException.nullAssigned("key must not be null.");
        } else if (MiscUtils.isEmptyString(key)) {
            throw ParameterException.outOfRange("key must not be empty.");
        }
        String accessToken = getSharedPreferences(context).getString(
                getKey(key, ACCESS_TOKEN), null);
        long expiredDate = getSharedPreferences(context).getLong(
                getKey(key, EXPIRED_DATE), 0L);
        String refreshToken = getSharedPreferences(context).getString(
                getKey(key, REFRESH_TOKEN), null);
        if (accessToken == null || expiredDate == 0L) {
            throw new AuthenticationContextNotFoundException();
        }
        return new AuthenticationContext(context, accessToken, expiredDate,
                refreshToken, key);
    }

    private static AuthenticationContext load(
            Context context,
            String key,
            String clientId,
            String clientSecret)
        throws AuthenticationContextAccessException,
            AuthenticationContextNotFoundException
    {
        if (context == null) {
            throw ParameterException.nullAssigned("context must not be null.");
        } else if (key == null) {
            throw ParameterException.nullAssigned("key must not be null.");
        } else if (MiscUtils.isEmptyString(key)) {
            throw ParameterException.outOfRange("key must not be empty.");
        }
        String accessToken = getSharedPreferences(context).getString(
                getKey(key, ACCESS_TOKEN), null);
        long expiredDate = getSharedPreferences(context).getLong(
                getKey(key, EXPIRED_DATE), 0L);
        String refreshToken = getSharedPreferences(context).getString(
                getKey(key, REFRESH_TOKEN), null);
        if (accessToken == null || expiredDate == 0L) {
            throw new AuthenticationContextNotFoundException();
        }
        return new AuthenticationContext(context, accessToken, expiredDate,
                refreshToken, key, clientId, clientSecret);
    }

    /**
     * Load {@link AuthenticationContext AuthenticationContext} object.
     *
     * <P>
     * This method uses SharedPreferences. If you access this method from
     * main thread, you will see the disk read violation on StrictMode.
     * </P>
     *
     * @param context context of an application. must not be null.
     * @param key a key to specify an {@link AuthenticationContext
     * AuthenticationContext} object to load. must not be null or empty
     * @param clientId A client ID issued by docomo Developer support. must
     * not be null or empty.
     * @param clientSecret A client secret issued by docomo Developer
     * support. must not be null or empty.
     * @return loaded {@link AuthenticationContext AuthenticationContext}
     * object.
     * @throws AuthenticationContextAccessException fail to access stored
     * {@link AuthenticationContext AuthenticationContext}.
     * @throws AuthenticationContextNotFoundException target data does not
     * exist.
     * @throws ParameterException One or more arguments are invalid.
     */
    public static AuthenticationContext loadFrom(
            Context context,
            String key,
            String clientId,
            String clientSecret)
        throws AuthenticationContextAccessException,
            AuthenticationContextNotFoundException
    {
        if (clientId == null) {
            throw ParameterException.nullAssigned("clientId must not be null.");
        } else if (MiscUtils.isEmptyString(clientId)) {
            throw ParameterException.outOfRange(
                "clientId must not be empty.");
        } else if (clientSecret == null) {
            throw ParameterException.nullAssigned(
                "clientSecret must not be null.");
        } else if (MiscUtils.isEmptyString(clientSecret)) {
            throw ParameterException.outOfRange(
                "clientSecret must not be empty.");
        }
        return load(context, key, clientId, clientSecret);
    }

    /**
     * Remove an {@link AuthenticationContext AuthenticationContext} object.
     *
     * The {@link AuthenticationContext AuthenticationContext} object which
     * is removed by this method can not load by the {@link
     * #loadFrom(Context, String) loadFrom()}.
     *
     * <P>
     * This method uses SharedPreferences. If you access this method from
     * main thread, you will see the disk read violation or disk write
     * violation on StrictMode.
     * </P>
     *
     * @param context context of an application. must not be null.
     * @param key a key to specify an {@link AuthenticationContext
     * AuthenticationContext} object to remove. must not be null or empty.
     * @throws AuthenticationContextAccessException fail to access stored
     * {@link AuthenticationContext AuthenticationContext}.
     * @throws ParameterException One or more arguments are invalid.
     */
    public static void removeFrom(
            Context context,
            String key)
        throws AuthenticationContextAccessException
    {
        if (context == null) {
            throw ParameterException.nullAssigned("context must not be null.");
        } else if (key == null) {
            throw ParameterException.nullAssigned("key must not be null.");
        } else if (MiscUtils.isEmptyString(key)) {
            throw ParameterException.outOfRange("key must not be empty.");
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(getKey(key, ACCESS_TOKEN));
        editor.remove(getKey(key, EXPIRED_DATE));
        editor.remove(getKey(key, REFRESH_TOKEN));
        if (!editor.commit()) {
            throw new AuthenticationContextAccessException(
                AuthenticationContextAccessException.Reason.ACCESS_FAILED);
        }
    }

    /**
     * Remove all stored {@link AuthenticationContext} objects.
     *
     * <P>
     * This method uses SharedPreferences. If you access this method from
     * main thread, you will see the disk read violation or disk write
     * violation on StrictMode.
     * </P>
     *
     * @param context context of an application. must not be null or empty.
     * @throws AuthenticationContextAccessException fail to access stored
     * @{link AuthenticationContext};
     * @throws ParameterException One or more arguments are invalid.
     */
    public static void removeAll(Context context)
        throws AuthenticationContextAccessException
    {
        if (context == null) {
            throw ParameterException.nullAssigned("context must not be null.");
        }
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        if (!editor.commit()) {
            throw new AuthenticationContextAccessException(
                AuthenticationContextAccessException.Reason.ACCESS_FAILED);
        }
    }

    /**
     * Save {@link AuthenticationContext AuthenticationContext} object.
     *
     * <P>
     * This method uses SharedPreferences. If you access this method from
     * main thread, you will see the disk read violation or disk write
     * violation on StrictMode.
     * </P>
     *
     * <p>
     * After calling this method, PhotoColleSDK automatically saves {@link
     * AuthenticationContext AuthenticationContext} with the storeKey
     * provided to this method. The timing to save {@link
     * AuthenticationContext AuthenticationContext} is when new access toekn
     * is received by refresh token.
     * </p>
     *
     * <p>
     * If this method is called several times with different storeKey, the
     * storeKey which is provided at last call is used to automatically save.
     * </p>
     *
     * @param context context of an application. must not be null.
     * @param key a key to save an {@link AuthenticationContext
     * AuthenticationContext} object. must not be null or empty.
     * @throws AuthenticationContextAccessException fail to store
     * {@link AuthenticationContext AuthenticationContext}.
     * @throws ParameterException One or more arguments are invalid.
     */
    public void saveTo(
            Context context,
            String key)
        throws AuthenticationContextAccessException
    {
        if (context == null) {
            throw ParameterException.nullAssigned("context must not be null.");
        } else if (key == null) {
            throw ParameterException.nullAssigned("key must not be null.");
        } else if (MiscUtils.isEmptyString(key)) {
            throw ParameterException.outOfRange("key must not be empty.");
        }
        if (!TextUtils.isEmpty(this.storeKey) && !this.storeKey.equals(key)) {
            if (hasSaved(context, this.storeKey)) {
                removeFrom(context, this.storeKey);
            }
        }
        this.storeKey = key;
        saveToSharedPreferences(context, key);
    }

    /**
     * Remaining time of this AuthenticationContext.
     *
     * Returns remaining time of this AuthenticationContext in TimeUnit. If
     * remaining time is more than 0, access token of this
     * AuthenticationContext is valid at the time. If remaining time is
     * equals or less than 0, applications should refresh access token.
     *
     * @param timeUnit time unit of returning value. must not be null.
     * @throws ParameterException an argument is invalid.
     */
    public long getRemainingTime(TimeUnit timeUnit) throws ParameterException {
        if (timeUnit == null) {
            throw ParameterException.nullAssigned(
                    "timeUnit must not be null.");
        }

        return timeUnit.convert(this.expiredDate - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS);
    }

    void save() throws AuthenticationContextAccessException {
        if (!TextUtils.isEmpty(this.storeKey)) {
            saveToSharedPreferences(this.appContext, this.storeKey);
        }
    }

    private void saveToSharedPreferences(
            Context context,
            String key)
        throws AuthenticationContextAccessException
    {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        // FIXME: encrypt data beflow.
        editor.putString(getKey(key, ACCESS_TOKEN), this.accessToken);
        editor.putLong(getKey(key, EXPIRED_DATE), this.expiredDate);
        if (TextUtils.isEmpty(this.refreshToken)) {
            editor.remove(getKey(key, REFRESH_TOKEN));
        } else {
            editor.putString(getKey(key, REFRESH_TOKEN), this.refreshToken);
        }
        if (!editor.commit()) {
            throw new AuthenticationContextAccessException(
                AuthenticationContextAccessException.Reason.ACCESS_FAILED);
        }
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        Context appContext = context.getApplicationContext();
        StringBuilder builder = new StringBuilder();
        builder.append(appContext.getPackageName()).append('.').
                append(PHOTOCOLLE_PREF);
        return appContext.getSharedPreferences(builder.toString(),
                Context.MODE_PRIVATE);
    }

    private static String getKey(String keyBase, String keySpecific) {
        return (new StringBuilder()).append(keyBase).append('.').append(
            keySpecific).toString();
    }

    boolean isTokenExpired() {
        return (this.expiredDate - 10000) < System.currentTimeMillis();
    }

    void update(AuthorizationData data) {
        this.accessToken = data.accessToken;
        this.expiredDate = data.expiredDate;
        this.refreshToken = data.refreshToken;
    }

    /**
     * Get access token.
     *
     * <p>
     * Applications should get access token by this method every time
     * applications use access token because access token is expired after
     * a period of time.
     * </p>
     *
     * <p>
     * PhotoColleSDK get new access token by using {@link PhotoColle} methods
     * accessing to PhotoColle network if access token is expired.
     * <p>
     *
     * @return the accessToken
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    long getExpiredDate() {
        return this.expiredDate;
    }

    String getRefreshToken() {
        return this.refreshToken;
    }

    Context getAppContext() {
        return this.appContext;
    }

    String getClientId() {
        return this.clientId;
    }

    String getClientSecret() {
        return this.clientSecret;
    }

    boolean canRefreshToken() {
        if (TextUtils.isEmpty(this.clientId) ||
                TextUtils.isEmpty(this.clientSecret)) {
            // clientId and clientSecret are null if the
            // AuthenticationContext object was generated by
            // AuthenticationContext#loadFrom(Context, String).
            // If clientId and/or clientSecret are null, then PhotoColleSDK
            // can not refresh Token.
            return false;
        } else if (TextUtils.isEmpty(this.refreshToken)) {
            // In specification
            // https://dev.smt.docomo.ne.jp/pdf/API_common_reference_v2.0.0.pdf,
            // Server may not send refresh token.
            return false;
        }
        return true;
    }

}
