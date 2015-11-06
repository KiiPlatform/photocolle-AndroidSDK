package com.kii.sdk.photocolle;

import static org.mockito.Mockito.*;

import java.net.URL;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONObject;

import com.kii.example.photocolle.annotation.TestInformation;

import android.test.AndroidTestCase;

public class GetContentBodyInfoLogicTest extends AndroidTestCase {

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0001"
    )
    public void test1() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                guid,
                ResizeType.ORIGINAL);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 1, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0002"
    )
    public void test2() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                guid,
                ResizeType.ORIGINAL);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 2, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0003"
    )
    public void test3() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.SLIDE_MOVIE,
                guid,
                ResizeType.ORIGINAL);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 3, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0004"
    )
    public void test4() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
            (new GetContentBodyInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentBodyInfoLogic.Args(
                            authenticateContext,
                            null,
                            new ContentGUID("dummyGUID"),
                            ResizeType.ORIGINAL));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0005"
    )
    public void test5() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                guid,
                ResizeType.ORIGINAL);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 1, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0006"
    )
    public void test6() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("d");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                guid,
                ResizeType.ORIGINAL);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 1, \"content_guid\" : \"d\"," +
                "\"resize_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0007"
    )
    public void test7() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guidStr = "01234567890123456789012345678901234567890123456789";
        ContentGUID guid = new ContentGUID(guidStr);
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                guid,
                ResizeType.ORIGINAL);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 1," +
                "\"content_guid\" : \"" + guidStr + "\"," +
                "\"resize_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0008"
    )
    public void test8() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
            (new GetContentBodyInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentBodyInfoLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            new ContentGUID(""),
                            ResizeType.ORIGINAL));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0009"
    )
    public void test9() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guidStr = "012345678901234567890123456789012345678901234567890";
        Exception exception = null;
        try {
            (new GetContentBodyInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentBodyInfoLogic.Args(
                        authenticateContext,
                        FileType.IMAGE,
                        new ContentGUID(guidStr),
                        ResizeType.ORIGINAL));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0010"
    )
    public void test10() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
            (new GetContentBodyInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentBodyInfoLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            null,
                            ResizeType.ORIGINAL));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0011"
    )
    public void test11() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                guid,
                ResizeType.ORIGINAL);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 1, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0012"
    )
    public void test12() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                guid,
                ResizeType.RESIZED_IMAGE);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 1, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 2 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0013"
    )
    public void test13() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                guid,
                ResizeType.RESIZED_VIDEO);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 2, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 3 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0014"
    )
    public void test14() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");

        Exception exception = null;
        try {
            (new GetContentBodyInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentBodyInfoLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            guid,
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0015"
    )
    public void test15() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                guid,
                ResizeType.ORIGINAL);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 1, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0016"
    )
    public void test16() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                guid,
                ResizeType.RESIZED_IMAGE);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 1, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 2 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0017"
    )
    public void test17() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
            (new GetContentBodyInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentBodyInfoLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            new ContentGUID("dummyGUID"),
                            ResizeType.RESIZED_VIDEO));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0018"
    )
    public void test18() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                guid,
                ResizeType.ORIGINAL);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 2, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0019"
    )
    public void test19() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                guid,
                ResizeType.RESIZED_IMAGE);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 2, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 2 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0020"
    )
    public void test20() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                guid,
                ResizeType.RESIZED_VIDEO);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 2, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 3 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0021"
    )
    public void test21() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.SLIDE_MOVIE,
                guid,
                ResizeType.ORIGINAL);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 3, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0022"
    )
    public void test22() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentBodyInfoLogic.Args arg =
            new GetContentBodyInfoLogic.Args(
                authenticateContext,
                FileType.SLIDE_MOVIE,
                guid,
                ResizeType.RESIZED_IMAGE);
        GetContentBodyInfoLogic logic = new GetContentBodyInfoLogic();
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

        JSONObject expect = new JSONObject(
                "{ \"file_type_cd\" : 3, \"content_guid\" : \"dummyGUID\"," +
                "\"resize_type_cd\" : 2 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0023"
    )
    public void test23() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
            (new GetContentBodyInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentBodyInfoLogic.Args(
                            authenticateContext,
                            FileType.SLIDE_MOVIE,
                            new ContentGUID("dummyGUID"),
                            ResizeType.RESIZED_VIDEO));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=7",
            id = "GCBILCR0024"
    )
    public void test24() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
            (new GetContentBodyInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentBodyInfoLogic.Args(
                            authenticateContext,
                            FileType.ALL,
                            new ContentGUID("dummyGUID"),
                            ResizeType.ORIGINAL));
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

