package com.kii.sdk.photocolle;

import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;
import org.mockito.internal.util.reflection.Whitebox;

import com.kii.example.photocolle.annotation.TestInformation;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthenticationContextTest extends TestCaseBase {

    private static final String PHOTOCOLLE_PREF = "photocollePref";
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String EXPIRED_DATE = "expiredDate";
    private static final String REFRESH_TOKEN = "refreshToken";

    public void tearDown() {
        try {
            AuthenticationContext.removeAll(getContext());
        } catch (AuthenticationContextAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void testHasSavedParameter()
            throws AuthenticationContextAccessException
    {
        ParameterException actual = null;
        try {
            AuthenticationContext.hasSaved(null, "storeKey");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("context must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            AuthenticationContext.hasSaved(getContext(), null);
        } catch (ParameterException e) {
            actual = e;
            assertEquals("key must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);

        try {
            AuthenticationContext.hasSaved(getContext(), "");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("key must not be empty.", actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;
    }

    @SuppressWarnings("deprecation")
    public void testLoadFromdParameter()
            throws AuthenticationContextAccessException,
                AuthenticationContextNotFoundException
    {
        ParameterException actual = null;
        try {
            AuthenticationContext.loadFrom(null, "storeKey");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("context must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            AuthenticationContext.loadFrom(getContext(), null);
        } catch (ParameterException e) {
            actual = e;
            assertEquals("key must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);

        try {
            AuthenticationContext.loadFrom(getContext(), "");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("key must not be empty.", actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;
    }

    public void test4ParamLoadFromdParameter()
            throws AuthenticationContextAccessException,
                AuthenticationContextNotFoundException
    {
        ParameterException actual = null;
        try {
            AuthenticationContext.loadFrom(null, "storeKey", "clientId",
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("context must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            AuthenticationContext.loadFrom(getContext(), null, "clientId",
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("key must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);

        try {
            AuthenticationContext.loadFrom(getContext(), "", "clientId",
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("key must not be empty.", actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            AuthenticationContext.loadFrom(getContext(), "storeKey", null,
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("clientId must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            AuthenticationContext.loadFrom(getContext(), "storeKey", "",
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("clientId must not be empty.", actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            AuthenticationContext.loadFrom(getContext(), "storeKey", "clientId",
                    null);
        } catch (ParameterException e) {
            actual = e;
            assertEquals("clientSecret must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            AuthenticationContext.loadFrom(getContext(), "storeKey", "clientId",
                    "");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("clientSecret must not be empty.",
                    actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;
    }


    public void testRemoveFromdParameter()
            throws AuthenticationContextAccessException,
                AuthenticationContextNotFoundException
    {
        ParameterException actual = null;
        try {
            AuthenticationContext.removeFrom(null, "storeKey");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("context must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            AuthenticationContext.removeFrom(getContext(), null);
        } catch (ParameterException e) {
            actual = e;
            assertEquals("key must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);

        try {
            AuthenticationContext.removeFrom(getContext(), "");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("key must not be empty.", actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;
    }

    public void testRemoveAlldParameter()
            throws AuthenticationContextAccessException,
                AuthenticationContextNotFoundException
    {
        ParameterException actual = null;
        try {
            AuthenticationContext.removeAll(null);
        } catch (ParameterException e) {
            actual = e;
            assertEquals("context must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;
    }

    public void testSaveToParamter()
            throws AuthenticationContextAccessException
    {
        ParameterException actual = null;
        AuthenticationContext authenticationContext = new AuthenticationContext(
            getContext(), "accessToken", 1, "refreshToken", "storeKey",
            "clientId", "clientSecret");
        try {
            authenticationContext.saveTo(null, "storeKey");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("context must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            authenticationContext.saveTo(getContext(), null);
        } catch (ParameterException e) {
            actual = e;
            assertEquals("key must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);

        try {
            authenticationContext.saveTo(getContext(), "");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("key must not be empty.", actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;
    }

    public void test7ParamConstructor() {
        ParameterException actual = null;
        try {
            new AuthenticationContext(
                    null,
                    "accessToken",
                    1,
                    "refreshToken",
                    "storeKey",
                    "clientId",
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("context must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            new AuthenticationContext(
                    getContext(),
                    null,
                    1,
                    "refreshToken",
                    "storeKey",
                    "clientId",
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("accessToken must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            new AuthenticationContext(
                    getContext(),
                    "",
                    1,
                    "refreshToken",
                    "storeKey",
                    "clientId",
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("accessToken must not be empty.", actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            new AuthenticationContext(
                    getContext(),
                    "accessToken",
                    1,
                    "refreshToken",
                    "storeKey",
                    null,
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("clientId must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            new AuthenticationContext(
                    getContext(),
                    "accessToken",
                    1,
                    "refreshToken",
                    "storeKey",
                    "",
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("clientId must not be empty.", actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            new AuthenticationContext(
                    getContext(),
                    "accessToken",
                    1,
                    "refreshToken",
                    "storeKey",
                    "clientId",
                    null);
        } catch (ParameterException e) {
            actual = e;
            assertEquals("clientSecret must not be null.", actual.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            new AuthenticationContext(
                    getContext(),
                    "accessToken",
                    1,
                    "refreshToken",
                    "storeKey",
                    "clientId",
                    "");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("clientSecret must not be empty.",
                    actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            new AuthenticationContext(
                    getContext(),
                    "accessToken",
                    0,
                    "refreshToken",
                    "storeKey",
                    "clientId",
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("expiredDate must be positive.",
                    actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;

        try {
            new AuthenticationContext(
                    getContext(),
                    "accessToken",
                    -100,
                    "refreshToken",
                    "storeKey",
                    "clientId",
                    "clientSecret");
        } catch (ParameterException e) {
            actual = e;
            assertEquals("expiredDate must be positive.",
                    actual.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actual.getReason());
        }
        assertNotNull(actual);
        actual = null;
    }

    public void testHasSavedSharedPreferences()
            throws AuthenticationContextAccessException
    {
        Context context = getContext();
        assertFalse(AuthenticationContext.hasSaved(context, "storeKey"));

        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                null,
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertTrue(AuthenticationContext.hasSaved(context, "storeKey"));

        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                "refreshToken",
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertTrue(AuthenticationContext.hasSaved(context, "storeKey"));
    }

    @SuppressWarnings("deprecation")
    public void testLoadFromSharedPreferences()
            throws AuthenticationContextAccessException,
                AuthenticationContextNotFoundException
    {
        Context context = getContext();
        AuthenticationContextNotFoundException actualException = null;
        try {
            AuthenticationContext.loadFrom(context, "storeKey");
        } catch (AuthenticationContextNotFoundException e) {
            actualException = e;
        }
        assertNotNull(actualException);

        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                null,
                "storeKey",
                "clientId",
                "clientSecret")).save();
        AuthenticationContext actual = AuthenticationContext.loadFrom(context,
                "storeKey");
        assertNotNull(actual);
        assertEquals("accessToken", actual.getAccessToken());
        assertEquals(Long.valueOf(1),
                Whitebox.getInternalState(actual, "expiredDate"));
        assertEquals(null, Whitebox.getInternalState(actual, "refreshToken"));
        assertEquals("storeKey",
                Whitebox.getInternalState(actual, "storeKey"));
        assertEquals("storeKey",
                Whitebox.getInternalState(actual, "storeKey"));
        assertEquals(null, Whitebox.getInternalState(actual, "clientId"));
        assertEquals(null, Whitebox.getInternalState(actual, "clientSecret"));
        actual = null;

        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                "refreshToken",
                "storeKey",
                "clientId",
                "clientSecret")).save();
        actual = AuthenticationContext.loadFrom(context, "storeKey");
        assertNotNull(actual);
        assertEquals("accessToken", actual.getAccessToken());
        assertEquals(Long.valueOf(1),
                Whitebox.getInternalState(actual, "expiredDate"));
        assertEquals("refreshToken",
                Whitebox.getInternalState(actual, "refreshToken"));
        assertEquals("storeKey",
                Whitebox.getInternalState(actual, "storeKey"));
        assertEquals("storeKey",
                Whitebox.getInternalState(actual, "storeKey"));
        assertEquals(null, Whitebox.getInternalState(actual, "clientId"));
        assertEquals(null, Whitebox.getInternalState(actual, "clientSecret"));
    }

    public void test4ParamLoadFromSharedPreferences()
            throws AuthenticationContextAccessException,
                AuthenticationContextNotFoundException
    {
        Context context = getContext();
        AuthenticationContextNotFoundException actualException = null;
        try {
            AuthenticationContext.loadFrom(context, "storeKey", "clientId",
                    "clientSecret");
        } catch (AuthenticationContextNotFoundException e) {
            actualException = e;
        }
        assertNotNull(actualException);

        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                null,
                "storeKey",
                "clientId",
                "clientSecret")).save();
        AuthenticationContext actual = AuthenticationContext.loadFrom(context,
                "storeKey", "clientId", "clientSecret");
        assertNotNull(actual);
        assertEquals("accessToken", actual.getAccessToken());
        assertEquals(Long.valueOf(1),
                Whitebox.getInternalState(actual, "expiredDate"));
        assertEquals(null, Whitebox.getInternalState(actual, "refreshToken"));
        assertEquals("storeKey",
                Whitebox.getInternalState(actual, "storeKey"));
        assertEquals("storeKey",
                Whitebox.getInternalState(actual, "storeKey"));
        assertEquals("clientId", Whitebox.getInternalState(actual, "clientId"));
        assertEquals("clientSecret",
                Whitebox.getInternalState(actual, "clientSecret"));
        actual = null;

        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                "refreshToken",
                "storeKey",
                "clientId",
                "clientSecret")).save();
        actual = AuthenticationContext.loadFrom(context, "storeKey", "clientId",
                "clientSecret");
        assertNotNull(actual);
        assertEquals("accessToken", actual.getAccessToken());
        assertEquals(Long.valueOf(1),
                Whitebox.getInternalState(actual, "expiredDate"));
        assertEquals("refreshToken",
                Whitebox.getInternalState(actual, "refreshToken"));
        assertEquals("storeKey",
                Whitebox.getInternalState(actual, "storeKey"));
        assertEquals("storeKey",
                Whitebox.getInternalState(actual, "storeKey"));
        assertEquals("clientId", Whitebox.getInternalState(actual, "clientId"));
        assertEquals("clientSecret",
                Whitebox.getInternalState(actual, "clientSecret"));
    }

    public void testRemoveFromSharedPreferences()
            throws AuthenticationContextAccessException
    {
        Context context = getContext();
        assertFalse(containsInSharedPreferences("storeKey"));
        AuthenticationContext.removeFrom(context, "storeKey");
        assertFalse(containsInSharedPreferences("storeKey"));

        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                null,
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertTrue(containsInSharedPreferencesWithoutRefreshToken("storeKey"));
        AuthenticationContext.removeFrom(context, "storeKey");
        assertFalse(containsInSharedPreferences("storeKey"));

        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                "refreshToken",
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertTrue(containsInSharedPreferences("storeKey"));
        AuthenticationContext.removeFrom(context, "storeKey");
        assertFalse(containsInSharedPreferences("storeKey"));
    }

    public void testRemoveAllSharedPreferences()
            throws AuthenticationContextAccessException
    {
        Context context = getContext();
        assertEquals(0, countRecordsInSharedPrefrecnes());
        AuthenticationContext.removeAll(context);
        assertEquals(0, countRecordsInSharedPrefrecnes());

        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                "refreshToken",
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertEquals(1, countRecordsInSharedPrefrecnes());
        AuthenticationContext.removeAll(context);
        assertEquals(0, countRecordsInSharedPrefrecnes());


        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                "refreshToken",
                "storeKey1",
                "clientId",
                "clientSecret")).save();
        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                null,
                "storeKey2",
                "clientId",
                "clientSecret")).save();
        assertEquals(2, countRecordsInSharedPrefrecnes());
        AuthenticationContext.removeAll(context);
        assertEquals(0, countRecordsInSharedPrefrecnes());
    }

    public void testSaveToSharedPreferences()
            throws AuthenticationContextAccessException, JSONException
    {
        Context context = getContext();

        // Insert AuthenticationContext without refresh token.
        assertEquals(0, countRecordsInSharedPrefrecnes());
        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                null,
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertEquals(1, countRecordsInSharedPrefrecnes());
        assertEquals("accessToken", loadString("storeKey", ACCESS_TOKEN));
        assertEquals(1, loadLong("storeKey", EXPIRED_DATE));
        assertEquals(null, loadString("storeKey", REFRESH_TOKEN));

        // Insert AuthenticationContext with refresh token.
        AuthenticationContext.removeAll(context);
        assertEquals(0, countRecordsInSharedPrefrecnes());
        (new AuthenticationContext(
                context,
                "accessToken",
                1,
                "refreshToken",
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertEquals(1, countRecordsInSharedPrefrecnes());
        assertEquals("accessToken", loadString("storeKey", ACCESS_TOKEN));
        assertEquals(1, loadLong("storeKey", EXPIRED_DATE));
        assertEquals("refreshToken", loadString("storeKey", REFRESH_TOKEN));

        // Update AuthenticationContext without refresh token to with
        // refresh token.
        AuthenticationContext.removeAll(context);
        assertEquals(0, countRecordsInSharedPrefrecnes());
        (new AuthenticationContext(
                context,
                "accessToken1",
                1,
                null,
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertEquals(1, countRecordsInSharedPrefrecnes());
        assertEquals("accessToken1", loadString("storeKey", ACCESS_TOKEN));
        assertEquals(1, loadLong("storeKey", EXPIRED_DATE));
        assertEquals(null, loadString("storeKey", REFRESH_TOKEN));
        assertEquals(1, countRecordsInSharedPrefrecnes());
        (new AuthenticationContext(
                context,
                "accessToken2",
                2,
                "refreshToken",
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertEquals(1, countRecordsInSharedPrefrecnes());
        assertEquals("accessToken2", loadString("storeKey", ACCESS_TOKEN));
        assertEquals(2, loadLong("storeKey", EXPIRED_DATE));
        assertEquals("refreshToken", loadString("storeKey", REFRESH_TOKEN));

        // Update AuthenticationContext with refresh token to without
        // refresh token.
        AuthenticationContext.removeAll(context);
        assertEquals(0, countRecordsInSharedPrefrecnes());
        (new AuthenticationContext(
                context,
                "accessToken1",
                1,
                "refreshToken",
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertEquals(1, countRecordsInSharedPrefrecnes());
        assertEquals("accessToken1", loadString("storeKey", ACCESS_TOKEN));
        assertEquals(1, loadLong("storeKey", EXPIRED_DATE));
        assertEquals("refreshToken", loadString("storeKey", REFRESH_TOKEN));
        assertEquals(1, countRecordsInSharedPrefrecnes());
        (new AuthenticationContext(
                context,
                "accessToken2",
                2,
                null,
                "storeKey",
                "clientId",
                "clientSecret")).save();
        assertEquals(1, countRecordsInSharedPrefrecnes());
        assertEquals("accessToken2", loadString("storeKey", ACCESS_TOKEN));
        assertEquals(2, loadLong("storeKey", EXPIRED_DATE));
        assertEquals(null, loadString("storeKey", REFRESH_TOKEN));

        // insert AuthenticationContext and remove old one without refresh
        // token.
        {
            AuthenticationContext.removeAll(context);
            assertEquals(0, countRecordsInSharedPrefrecnes());
            AuthenticationContext authenticationContext =
                new AuthenticationContext(
                        context,
                        "accessToken1",
                        1,
                        null,
                        "storeKey1",
                        "clientId",
                        "clientSecret");
            authenticationContext.save();
            assertEquals(1, countRecordsInSharedPrefrecnes());
            assertEquals("accessToken1", loadString("storeKey1", ACCESS_TOKEN));
            assertEquals(1, loadLong("storeKey1", EXPIRED_DATE));
            assertEquals(null, loadString("storeKey1", REFRESH_TOKEN));
            assertEquals(1, countRecordsInSharedPrefrecnes());
            AuthorizationData expected = AuthorizationData.create(
                (new JSONObject()).put("access_token", "accessToken2").
                        put("expires_in", "2").
                        put("refresh_token", "refreshToken").
                        put("scope", "dummmy"));
            authenticationContext.update(expected);
            authenticationContext.saveTo(context, "storeKey2");
            assertEquals(1, countRecordsInSharedPrefrecnes());
            assertEquals(expected.accessToken,
                    loadString("storeKey2", ACCESS_TOKEN));
            assertEquals(expected.expiredDate,
                    loadLong("storeKey2", EXPIRED_DATE));
            assertEquals(expected.refreshToken, loadString("storeKey2",
                            REFRESH_TOKEN));
        }

        // insert AuthenticationContext and remove old one with refresh
        // token.
        {
            AuthenticationContext.removeAll(context);
            assertEquals(0, countRecordsInSharedPrefrecnes());
            AuthenticationContext authenticationContext =
                new AuthenticationContext(
                        context,
                        "accessToken1",
                        1,
                        "refreshToken",
                        "storeKey1",
                        "clientId",
                        "clientSecret");
            authenticationContext.save();
            assertEquals(1, countRecordsInSharedPrefrecnes());
            assertEquals("accessToken1", loadString("storeKey1", ACCESS_TOKEN));
            assertEquals(1, loadLong("storeKey1", EXPIRED_DATE));
            assertEquals("refreshToken", loadString("storeKey1",
                            REFRESH_TOKEN));
            assertEquals(1, countRecordsInSharedPrefrecnes());
            AuthorizationData expected = AuthorizationData.create(
                    (new JSONObject()).put("access_token", "accessToken2").
                        put("expires_in", "2").
                        put("scope", "dummmy"));
            authenticationContext.update(expected);
            authenticationContext.saveTo(context, "storeKey2");
            assertEquals(1, countRecordsInSharedPrefrecnes());
            assertEquals(expected.accessToken,
                    loadString("storeKey2", ACCESS_TOKEN));
            assertEquals(expected.expiredDate,
                    loadLong("storeKey2", EXPIRED_DATE));
            assertEquals(expected.refreshToken, loadString("storeKey2",
                            REFRESH_TOKEN));
        }
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheets/d/1e0E9LL7W30qFeYHtju-xl50cCwTHXrTSypqUQdup1Fs/edit#gid=0",
            id = "ID0009,ID0010,ID0011,ID0012"
    )
    public void testGetRemainingTime() {
        // ID0009
        {
            AuthenticationContext context = new AuthenticationContext(mContext,
                    "dummy", System.currentTimeMillis() + 300000, "dummy",
                    "dummy", "dummy", "dummy");
            assertTrue("getRemainingTime must return positive number.",
                    context.getRemainingTime(TimeUnit.SECONDS) > 0);
        }

        // ID0010
        {
            AuthenticationContext context = new AuthenticationContext(mContext,
                    "dummy", System.currentTimeMillis() - 300000, "dummy",
                    "dummy", "dummy", "dummy");
            assertTrue("getRemainingTime must return negative number.",
                    context.getRemainingTime(TimeUnit.SECONDS) < 0);
        }

        // ID0011
        {
            AuthenticationContext context = new AuthenticationContext(mContext,
                    "dummy", System.currentTimeMillis() + 300000, "dummy",
                    "dummy", "dummy", "dummy");
            long sec = context.getRemainingTime(TimeUnit.SECONDS);
            assertTrue("getRemainingTime must return over 200.", sec > 200);
            assertTrue("getRemainingTime must return under 301.", sec < 301);
        }

        // ID0012
        {
            AuthenticationContext context = new AuthenticationContext(mContext,
                    "dummy", System.currentTimeMillis() + 300000, "dummy",
                    "dummy", "dummy", "dummy");
            long sec = context.getRemainingTime(TimeUnit.MILLISECONDS);
            assertTrue("getRemainingTime must return over 200000.",
                    sec > 200000);
            assertTrue("getRemainingTime must return under 300001.", sec < 300001);
        }
    }

    private boolean containsInSharedPreferences(String storeKey) {
        SharedPreferences preferences = getSharedPreferences();
        return preferences.contains(getKey(storeKey, ACCESS_TOKEN)) &&
                preferences.contains(getKey(storeKey, EXPIRED_DATE)) &&
                preferences.contains(getKey(storeKey, REFRESH_TOKEN));
    }

    private boolean containsInSharedPreferencesWithoutRefreshToken(
        String storeKey)
    {
        SharedPreferences preferences = getSharedPreferences();
        return preferences.contains(getKey(storeKey, ACCESS_TOKEN)) &&
                preferences.contains(getKey(storeKey, EXPIRED_DATE)) &&
                !preferences.contains(getKey(storeKey, REFRESH_TOKEN));
    }

    private  SharedPreferences getSharedPreferences() {
        Context appContext = getContext().getApplicationContext();
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

    private int countRecordsInSharedPrefrecnes() {
        SharedPreferences preferences = getSharedPreferences();
        int retval = 0;
        for (String key : preferences.getAll().keySet()) {
            if (key.endsWith("." + ACCESS_TOKEN)) {
                ++retval;
            }
        }
        return retval;
    }

    private String loadString(String storeKey, String postFix) {
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getString(getKey(storeKey, postFix), null);
    }

    private long loadLong(String storeKey, String postFix) {
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getLong(getKey(storeKey, postFix), 0L);
    }

}
