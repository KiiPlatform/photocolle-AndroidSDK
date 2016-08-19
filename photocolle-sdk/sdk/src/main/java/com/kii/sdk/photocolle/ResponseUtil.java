package com.kii.sdk.photocolle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kii.sdk.photocolle.BaseApplicationLayerException.ErrorCode;
import com.kii.sdk.photocolle.UploadException.ErrorItem;

import android.util.Base64;

class ResponseUtil {

    public static String getString(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        try {
            return json.getString(key);
        } catch (JSONException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static long getLong(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        try {
            return json.getLong(key);
        } catch (JSONException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static int getInt(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        try {
            return json.getInt(key);
        } catch (JSONException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static Date getDate(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        try {
            return MiscUtils.parseAsRFC3339(json.getString(key));
        } catch (JSONException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static JSONArray getJSONArray(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        try {
            return json.getJSONArray(key);
        } catch (JSONException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static JSONObject getJSONObjectFromArray(
            JSONArray array,
            int index)
        throws ResponseBodyParseException
    {
        try {
            return array.getJSONObject(index);
        } catch (JSONException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static String getStringFromArray(
            JSONArray array,
            int index)
        throws ResponseBodyParseException
    {
        try {
            return array.getString(index);
        } catch (JSONException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static FileType getFileType(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        int fileType = ResponseUtil.getInt(json, key);
        try {
            return FileType.fromInt(fileType);
        } catch (Exception e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static MimeType getMimeType(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        String mimeType = getString(json, key);
        try {
            return MimeType.fromString(mimeType);
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static JSONObject newJSONObject(String content)
        throws ResponseBodyParseException
    {
        try {
            return new JSONObject(content);
        } catch (JSONException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static ApplicationLayerException newApplicationLayerException(
            JSONObject json)
        throws ResponseBodyParseException
    {
        return new ApplicationLayerException(getInt(json, "err_cd"),
                json.optString("param_name"),
                json.optString("param_value"));
    }

    public static int optIntInRangeMin(
            JSONObject json,
            String key,
            int min,
            int defaultValue)
        throws ResponseBodyParseException
    {
        if (!json.has(key)) {
            return defaultValue;
        }
        int retval = ResponseUtil.getInt(json, key);
        if (retval < min) {
            throw new ParameterException(
                ParameterException.Reason.OUT_OF_RANGE,
                String.format("%s must equal or be greater than %d but %d ",
                        key, min, retval));
        }
        return retval;
    }

    public static Score optScore(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        try {
            if (json.has(key)) {
                return Score.fromInt(getInt(json, key));
            } else {
                return null;
            }
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static Orientation optOrientation(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        try {
            if (json.has(key)) {
                return Orientation.fromInt(getInt(json, key));
            } else {
                return null;
            }
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static ContentGUID getContentGUID(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        try {
            return new ContentGUID(getString(json, key));
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static ContentGUID getContentGUIDWithLengthRange(
            JSONObject json,
            String key,
            int min,
            int max)
        throws ResponseBodyParseException
    {
        try {
            return newContentGUIDWithLengthRange(getString(json, key), min,
                    max);
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static ContentGUID newContentGUIDWithLengthRange(
            String guid,
            int min,
            int max)
        throws ResponseBodyParseException
    {
        try {
            if (guid == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "guid must not be null.");
            } else if (guid.length() < min || guid.length() > max) {
                throw new ParameterException(
                    ParameterException.Reason.OUT_OF_RANGE,
                    String.format("guid must be bewteen %d and %d but %d.",
                            min, max, guid.length()));
            }
            return new ContentGUID(guid);
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static byte[] getThumbnail(JSONObject json, String key)
            throws ResponseBodyParseException
    {
        try {
            return Base64.decode(getString(json, key), Base64.DEFAULT);
        } catch (IllegalArgumentException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static UploadException newUploadException(JSONObject json)
        throws ResponseBodyParseException
    {
        try {
            return new UploadException(toErrorItems(json));
        } catch (JSONException e) {
            throw new ResponseBodyParseException(e);
        } catch (NumberFormatException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static String fromHttpEntityToString(
            HttpEntity entity,
            String encoding)
        throws ResponseBodyParseException
    {
        try {
            return EntityUtils.toString(entity, encoding);
        } catch (UnsupportedOperationException e) {
            throw new ResponseBodyParseException(e);
        } catch (ParseException e) {
            throw new ResponseBodyParseException(e);
        } catch (IOException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static void throwHttpStatusRelatedException(
            HttpResponse response)
        throws HttpException, InvalidTokenException, ResponseBodyParseException
    {
        if (isTokenExpired(response)) {
            throw new InvalidTokenException();
        }
        throw new HttpException(response.getStatusLine().getStatusCode(),
                response.getStatusLine().getReasonPhrase(),
                fromHttpEntityToString(response.getEntity(), "UTF-8"));
    }

    private static List<ErrorItem> toErrorItems(
            JSONObject json)
        throws JSONException
    {
        JSONArray array = json.getJSONArray("err_list");
        List<ErrorItem> retval = new ArrayList<ErrorItem>();
        for (int i = 0, len = array.length(); i < len; ++i) {
            retval.add(toErrorItem(array.getJSONObject(i)));
        }
        return retval;
    }

    private static ErrorItem toErrorItem(JSONObject json) throws JSONException {
        String item = json.getString("err_item");
        String errorCode = json.getString("err_cd");
        return new ErrorItem(item,
                ErrorCode.toErrorCode(Integer.parseInt(errorCode)));
    }

    private static boolean isTokenExpired(HttpResponse response) {
        if (response.getStatusLine().getStatusCode() != 401) {
            return false;
        }
        Header[] headers = response.getHeaders("WWW-Authenticate");
        if (headers != null) {
            for (Header header : headers) {
                String value = header.getValue();
                if (value == null) {
                    continue;
                }
                for (String str : value.split(" ")) {
                    if (str.equals("error=invalid_token")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int getIntInRangeMinMax(
            JSONObject json,
            String key,
            int min,
            int max)
        throws ResponseBodyParseException
    {
        try {
            int retval = ResponseUtil.getInt(json, key);
            if (retval < min || retval > max) {
                throw new ParameterException(
                    ParameterException.Reason.OUT_OF_RANGE,
                    String.format("%s must be between %d and %d but %d",
                            key, min, max, retval));
            }
            return retval;
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static int getIntInRangeMin(
            JSONObject json,
            String key,
            int min)
        throws ResponseBodyParseException
    {
        try {
            int retval = getInt(json, key);
            if (retval < min) {
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        String.format("%s must equal or be greater than %d but %d ",
                                key, min, retval));
            }
            return retval;
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static String getStringInRangeMinMax(
            JSONObject json,
            String key,
            int min,
            int max)
        throws ResponseBodyParseException
    {
        try {
            String retval = getString(json, key);
            if (retval != null && (retval.length() < min ||
                    retval.length() > max)) {
                throw new ParameterException(
                    ParameterException.Reason.OUT_OF_RANGE,
                    String.format("length of %s must be between %d and %d but %d",
                            key, min, max, retval.length()));
            }
            return retval;
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public static JSONArray getArrayInRangeMinMax(
            JSONObject json,
            String key,
            int min,
            int max)
        throws ResponseBodyParseException
    {
        JSONArray retval = getJSONArray(json, key);
        if (retval != null && (retval.length() < min ||
                retval.length() > max)) {
            throw new ParameterException(
                ParameterException.Reason.OUT_OF_RANGE,
                String.format("length of %s must equal or be greater than %d but %d ",
                        key, min, retval.length()));
        }
        return retval;
    }

    public static JSONArray optArrayInRangeMin(
            JSONObject json,
            String key,
            int min)
        throws ResponseBodyParseException
    {
        if (!json.has(key)) {
            return null;
        }
        JSONArray retval = getJSONArray(json, key);
        if (retval.length() < min) {
            throw new ParameterException(
                ParameterException.Reason.OUT_OF_RANGE,
                String.format("length of %s must equal or be greater than %d but %d ",
                        key, min, retval.length()));
        }
        return retval;
    }

    public static JSONArray optArrayInRangeMinMax(
            JSONObject json,
            String key,
            int min,
            int max)
        throws ResponseBodyParseException
    {
        if (!json.has(key)) {
            return null;
        }
        JSONArray retval = getJSONArray(json, key);
        if (retval.length() < min || retval.length() > max) {
            throw new ParameterException(
                    ParameterException.Reason.OUT_OF_RANGE,
                    String.format("length of %s must be between %d and %d but %d",
                            key, min, max, retval.length()));
        }
        return retval;
    }

    public static String optString(JSONObject json, String key)
        throws ResponseBodyParseException
    {
        try {
            if (json.has(key)) {
                return json.getString(key);
            } else {
                return null;
            }
        } catch (JSONException e) {
            throw new ResponseBodyParseException(e);
        }
    }

}
