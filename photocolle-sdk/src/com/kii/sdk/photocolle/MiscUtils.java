package com.kii.sdk.photocolle;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Base64;
import android.util.Log;

class MiscUtils {

    public static final String ENCODING = "UTF-8";

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private static final Pattern PATTERN_OAUTH_PROBLEM =
            Pattern.compile("oauth_problem=([^ ]+)");

    static boolean isLoggable = false;

    public static Date parseAsRFC3339(String str) {
        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339(str);
        return new Date(time.normalize(false));
    }

    public static String formatAsUTC(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(date);
    }

    public static String calculateRFC2104HMAC(String data, String key) {
        // get an hmac_sha1 key from the raw key bytes
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(),
                HMAC_SHA1_ALGORITHM);

        // get an hmac_sha1 Mac instance and initialize with the signing key
        Mac mac = null;
        try {
            mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        // compute the hmac on input data bytes
        byte[] rawHmac = mac.doFinal(data.getBytes());

        // base64-encode the hmac
        return Base64.encodeToString(rawHmac, Base64.NO_WRAP);
    }

    public static URL getAuthorityBaseURL(Context context) {
        String str = getPhotoColleSDKSetting(context).optString(
                "authorityUrl", null);
        if (str == null) {
            return null;
        }

        URL retval = null;
        try {
            retval = new URL(str);
        } catch (MalformedURLException e) {
            Log.e("MiscUtils", "getAuthorityBaseURL failed: " + str, e);
        }
        return retval;
    }

    public static URL getTokenBaseURL(Context context) {
        String str = getPhotoColleSDKSetting(context).optString(
                "tokenUrl", null);
        if (str == null) {
            return null;
        }

        URL retval = null;
        try {
            retval = new URL(str);
        } catch (MalformedURLException e) {
            Log.e("MiscUtils", "getTokenBaseURL failed: " + str, e);
        }
        return retval;
    }

    public static URL getPhotoColleBaseURL(Context context) {
        String str = getPhotoColleSDKSetting(context).optString(
                "photocolleUrl", null);
        if (str == null) {
            return null;
        }

        URL retval = null;
        try {
            retval = new URL(str);
        } catch (MalformedURLException e) {
            Log.e("MiscUtils", "getPhotoColleBaseURL failed: " + str, e);
        }
        return retval;
    }

    public static void setupLogProperty(Context context) {
        isLoggable = getPhotoColleSDKSetting(context).optBoolean("logging",
                false);
    }

    private static JSONObject getPhotoColleSDKSetting(Context context) {
        JSONObject retval = null;
        BufferedReader reader = null;
        try {
            StringBuilder builder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(
                    context.getResources().getAssets().open(
                            "photocolle_sdk_setting.json")));
            for (String str = reader.readLine(); str != null;
                    str = reader.readLine()) {
                builder.append(str);
            }
            retval = new JSONObject(builder.toString());
        } catch (FileNotFoundException fe) {
            // no log. setting file is optional.
            retval = new JSONObject();
        } catch (Exception e) {
            Log.e("MiscUtils", "getPhotoColleSDKSetting failed.", e);
            retval = new JSONObject();
        } finally {
            closeQuietly(reader);
        }
        return retval;
    }

    public static void closeQuietly(Closeable closeable)
    {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e("MiscUtils", "closeQuietly failed.", e);
            }
        }
    }

    public static String parseByPattern(String s, Pattern p) {
        Matcher m = p.matcher(s);
        if (m.find() && m.groupCount() >= 1) {
            try {
                return URLDecoder.decode(m.group(1), ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static String getOauthProblem(String str) {
        return parseByPattern(str, PATTERN_OAUTH_PROBLEM);
    }

    public static URL toUrl(String src) {
        try {
            return new URL(src);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isEmptyString(String string) {
        return "".equals(string);
    }

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(String[] array) {
        return array == null || array.length == 0;
    }

    public static boolean containsNullOrEmpty(Collection<String> collection) {
        if (collection == null) {
            return false;
        }
        for (String str : collection) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsNullOrEmpty(String[] array) {
        if (array == null) {
            return false;
        }
        for (String str : array) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

}
