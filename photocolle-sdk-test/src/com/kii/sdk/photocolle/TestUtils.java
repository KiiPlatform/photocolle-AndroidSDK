package com.kii.sdk.photocolle;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Iterator;

import junit.framework.Assert;

import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtils {

    public static JSONObject getJSONObject(ByteArrayEntity entity)
        throws Exception
    {
        ByteArrayOutputStream bos = null;
        JSONObject retval = new JSONObject();
        try {
            bos = new ByteArrayOutputStream();
            entity.writeTo(bos);
            retval = new JSONObject(bos.toString());
        } finally {
            MiscUtils.closeQuietly(bos);
        }
        return retval;
    }

    public static void assertJSONObjectEquals(
            JSONObject expected,
            JSONObject actual)
        throws Exception
    {
        if (expected == actual) {
            return;
        }
        Assert.assertEquals(expected.length(), actual.length());
        for (Iterator<?> i = expected.keys(); i.hasNext(); ) {
            String key = (String)i.next();
            assertJSONElementEquals(key, expected.get(key), actual.get(key));
        }
    }

    public static void assertJSONArrayEquals(
            JSONArray expected,
            JSONArray actual)
        throws Exception
    {
        if (expected == actual) {
            return;
        }
        Assert.assertEquals(expected.length(), actual.length());
        for (int i = 0, len = expected.length(); i < len; ++i) {
            assertJSONElementEquals(Integer.toString(i),
                    expected.get(i), actual.get(i));
        }
    }

    private static void assertJSONElementEquals(
            String key,
            Object expected,
            Object actual)
        throws Exception
    {
        Class<?> expectedClass = expected.getClass();
        Class<?> actualClass = actual.getClass();
        Assert.assertEquals(expectedClass, actualClass);
        if (expectedClass == JSONObject.class) {
            assertJSONObjectEquals((JSONObject)expected,
                    (JSONObject)actual);
        } else if (expectedClass == JSONArray.class) {
            assertJSONArrayEquals((JSONArray)expected,
                    (JSONArray)actual);
        } else {
            // check class is valid or not.
            if (expectedClass == String.class ||
                    expectedClass == Boolean.class ||
                    expectedClass == Integer.class ||
                    expectedClass == Long.class ||
                    expectedClass == Double.class) {
                if (!expected.equals(actual)) {
                    Assert.fail(String.format(
                                "expected: <%s:%s> but was <%s:%s>", key,
                                expected.toString(), key, actual.toString()));
                }
            } else {
                Assert.fail(String.format("key %s for %s is unexpected class",
                                key, expectedClass.getName()));
            }
        }
    }

    public static byte[] getHashFromInputStream(
            InputStream is)
        throws Exception
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        while(true) {
            int len = is.read(buf);
            if(len < 0) {
                break;
            }
            bos.write(buf, 0, len);
        }
        return MessageDigest.getInstance("MD5").digest(bos.toByteArray());
    }

    public static String toHexString(byte[] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String b = Integer.toHexString(arr[i] & 0xff);
            if (b.length() == 1) {
                builder.append("0");
            }
            builder.append(b);
        }
        return builder.toString();
    }
}
