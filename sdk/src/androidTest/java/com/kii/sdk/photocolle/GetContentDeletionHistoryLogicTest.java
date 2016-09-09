package com.kii.sdk.photocolle;

import static org.mockito.Mockito.*;

import java.net.URL;
import java.util.Date;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONObject;

import com.kii.example.photocolle.annotation.TestInformation;

import android.annotation.SuppressLint;
import android.text.format.Time;

@SuppressLint("NewApi")
public class GetContentDeletionHistoryLogicTest extends TestCaseBase {

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0001"
    )
    public void test1() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                null,
                null,
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0002"
    )
    public void test2() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                null,
                null,
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 2 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0003"
    )
    public void test3() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.SLIDE_MOVIE,
                null,
                null,
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 3 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0004"
    )
    public void test4() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
             (new GetContentDeletionHistoryLogic()).createRequest(
                new URL("http://example.com"),
                new GetContentDeletionHistoryLogic.Args(
                        authenticateContext,
                        null,
                        null,
                        null,
                        null));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0005"
    )
    public void test5() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time t = new Time(Time.TIMEZONE_UTC);
        t.parse3339("2013-01-01T00:00:00Z");
        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                new Date(t.toMillis(false)),
                null,
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1," +
                    "\"min_date_deleted\" : \"2013-01-01T00:00:00Z\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0006"
    )
    public void test6() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time t = new Time(Time.TIMEZONE_UTC);
        t.parse3339("1998-06-15T13:30:25Z");
        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                new Date(t.toMillis(false)),
                null,
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1," +
                    "\"min_date_deleted\" : \"1998-06-15T13:30:25Z\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0007"
    )
    public void test7() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                null,
                null,
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0008"
    )
    public void test8() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                null,
                Integer.valueOf(50),
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1," +
                    "\"max_results\" : 50 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0009"
    )
    public void test9() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                null,
                Integer.valueOf(1),
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1," +
                    "\"max_results\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0010"
    )
    public void test10() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                null,
                Integer.valueOf(100),
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1," +
                    "\"max_results\" : 100 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0011"
    )
    public void test11() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
             (new GetContentDeletionHistoryLogic()).createRequest(
                new URL("http://example.com"),
                new GetContentDeletionHistoryLogic.Args(
                        authenticateContext,
                        FileType.IMAGE,
                        null,
                        Integer.valueOf(0),
                        null));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0012"
    )
    public void test12() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
             (new GetContentDeletionHistoryLogic()).createRequest(
                new URL("http://example.com"),
                new GetContentDeletionHistoryLogic.Args(
                        authenticateContext,
                        FileType.IMAGE,
                        null,
                        Integer.valueOf(101),
                        null));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0013"
    )
    public void test13() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
             (new GetContentDeletionHistoryLogic()).createRequest(
                new URL("http://example.com"),
                new GetContentDeletionHistoryLogic.Args(
                        authenticateContext,
                        FileType.IMAGE,
                        null,
                        Integer.valueOf(-100),
                        null));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0014"
    )
    public void test14() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
             (new GetContentDeletionHistoryLogic()).createRequest(
                new URL("http://example.com"),
                new GetContentDeletionHistoryLogic.Args(
                        authenticateContext,
                        FileType.IMAGE,
                        null,
                        Integer.valueOf(200),
                        null));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0015"
    )
    public void test15() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                null,
                null,
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0016"
    )
    public void test16() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                null,
                null,
                Integer.valueOf(5));
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1," +
                    "\"start\" : 5 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0017"
    )
    public void test17() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                null,
                null,
                Integer.valueOf(1));
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1," +
                    "\"start\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0018"
    )
    public void test18() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
             (new GetContentDeletionHistoryLogic()).createRequest(
                new URL("http://example.com"),
                new GetContentDeletionHistoryLogic.Args(
                        authenticateContext,
                        FileType.IMAGE,
                        null,
                        null,
                        Integer.valueOf(0)));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0019"
    )
    public void test19() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentDeletionHistoryLogic.Args arg =
            new GetContentDeletionHistoryLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                null,
                null,
                null);
        GetContentDeletionHistoryLogic logic =
                new GetContentDeletionHistoryLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        ByteArrayEntity entity = (ByteArrayEntity)request.getEntity();
        assertNotNull(entity);
        assertEquals("application/json", entity.getContentType().getValue());

        // check json.
        JSONObject actual = TestUtils.getJSONObject(entity);

        JSONObject expect = new JSONObject("{ \"file_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=6",
            id = "GCDHLCR0020"
    )
    public void test20() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
             (new GetContentDeletionHistoryLogic()).createRequest(
                new URL("http://example.com"),
                new GetContentDeletionHistoryLogic.Args(
                        authenticateContext,
                        FileType.ALL,
                        null,
                        null,
                        null));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

}

