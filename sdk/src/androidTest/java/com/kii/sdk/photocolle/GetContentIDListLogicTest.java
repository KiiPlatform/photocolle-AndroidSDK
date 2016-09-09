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

/**
 * https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1
 *
 * Details of each test methods are described in the spread sheet above.
 * You can find the detail of a test method by searching with test method name.
 *
 * This is test class for GetContentIDListLogic#createRequest().
 * In this test case, All arguments GetContentIDListLogic#createRequest()
 * were checked, but does not check combination of these arguments.
 */
@SuppressLint("NewApi")
public class GetContentIDListLogicTest extends TestCaseBase {

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0001"
    )
    public void test1() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0002"
    )
    public void test2() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 2," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0003"
    )
    public void test3() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.SLIDE_MOVIE,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 3," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0004"
    )
    public void test4() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListLogic.Args(
                            authenticateContext,
                            null,
                            false,
                            null,
                            null,
                            null,
                            SortType.CREATION_DATETIME_DESC));
        } catch (Exception e) {
            actualException = e;
        }

        // MAIN TARGET OF THIS TEST.
        assertNotNull(actualException);
        assertEquals(actualException.getClass(), ParameterException.class);
        assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                ((ParameterException)actualException).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0005"
    )
    public void test5() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
             url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
             id = "GCILLCR0006"
    )
    public void test6() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_ASC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 2 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0007"
    )
    public void test7() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                null,
                SortType.MODIFICATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 3 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0008"
    )
    public void test8() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                null,
                SortType.MODIFICATION_DATETIME_ASC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 4 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0009"
    )
    public void test9() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                null,
                SortType.UPLOAD_DATETIME_ASC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 5 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0010"
    )
    public void test10() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                null,
                SortType.UPLOAD_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 6 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0011"
    )
    public void test11() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            false,
                            null,
                            null,
                            null,
                            null));
        } catch (Exception e) {
            actualException = e;
        }

        // MAIN TARGET OF THIS TEST.
        assertNotNull(actualException);
        assertEquals(actualException.getClass(), ParameterException.class);
        assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                ((ParameterException)actualException).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0012"
    )
    public void test12() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0013"
    )
    public void test13() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                true,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 2," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0014"
    )
    public void test14() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("2013-01-01T00:00:00Z");
        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                true,
                new UploadDateFilter(new Date(time.normalize(false))),
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 2," +
                "\"min_date_upload\" : \"2013-01-01T00:00:00Z\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0015"
    )
    public void test15() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("1998-06-15T13:30:25Z");
        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                true,
                new UploadDateFilter(new Date(time.normalize(false))),
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 2," +
                "\"min_date_upload\" : \"1998-06-15T13:30:25Z\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0016"
    )
    public void test16() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("2013-01-01T00:00:00Z");
        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                true,
                new ModifiedDateFilter(new Date(time.normalize(false))),
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 2," +
                "\"min_date_modified\" : \"2013-01-01T00:00:00Z\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0017"
    )
    public void test17() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("1998-06-15T13:30:25Z");
        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                true,
                new ModifiedDateFilter(new Date(time.normalize(false))),
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 2," +
                "\"min_date_modified\" : \"1998-06-15T13:30:25Z\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0018"
    )
    public void test18() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("1998-06-15T13:30:25Z");
        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                true,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 2," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0019"
    )
    public void test19() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                Integer.valueOf(50),
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"max_results\" : 50," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0020"
    )
    public void test20() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                Integer.valueOf(1),
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"max_results\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0021"
    )
    public void test21() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                Integer.valueOf(100),
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"max_results\" : 100," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0022"
    )
    public void test22() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            false,
                            null,
                            Integer.valueOf(0),
                            null,
                            SortType.CREATION_DATETIME_DESC));
        } catch (Exception e) {
            actualException = e;
        }

        // MAIN TARGET OF THIS TEST.
        assertNotNull(actualException);
        assertEquals(actualException.getClass(), ParameterException.class);
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)actualException).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0023"
    )
    public void test23() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            false,
                            null,
                            Integer.valueOf(101),
                            null,
                            SortType.CREATION_DATETIME_DESC));
        } catch (Exception e) {
            actualException = e;
        }

        // MAIN TARGET OF THIS TEST.
        assertNotNull(actualException);
        assertEquals(actualException.getClass(), ParameterException.class);
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)actualException).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0024"
    )
    public void test24() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            false,
                            null,
                            Integer.valueOf(-100),
                            null,
                            SortType.CREATION_DATETIME_DESC));
        } catch (Exception e) {
            actualException = e;
        }

        // MAIN TARGET OF THIS TEST.
        assertNotNull(actualException);
        assertEquals(actualException.getClass(), ParameterException.class);
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)actualException).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0025"
    )
    public void test25() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            false,
                            null,
                            Integer.valueOf(200),
                            null,
                            SortType.CREATION_DATETIME_DESC));
        } catch (Exception e) {
            actualException = e;
        }

        // MAIN TARGET OF THIS TEST.
        assertNotNull(actualException);
        assertEquals(actualException.getClass(), ParameterException.class);
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)actualException).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0026"
    )
    public void test26() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0027"
    )
    public void test27() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                Integer.valueOf(50),
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"start\" : 50," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0028"
    )
    public void test28() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                Integer.valueOf(1),
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"start\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0029"
    )
    public void test29() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            false,
                            null,
                            null,
                            Integer.valueOf(0),
                            SortType.CREATION_DATETIME_DESC));
        } catch (Exception e) {
            actualException = e;
        }

        // MAIN TARGET OF THIS TEST.
        assertNotNull(actualException);
        assertEquals(actualException.getClass(), ParameterException.class);
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)actualException).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0030"
    )
    public void test30() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            false,
                            null,
                            null,
                            Integer.valueOf(-10),
                            SortType.CREATION_DATETIME_DESC));
        } catch (Exception e) {
            actualException = e;
        }

        // MAIN TARGET OF THIS TEST.
        assertNotNull(actualException);
        assertEquals(actualException.getClass(), ParameterException.class);
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)actualException).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0031"
    )
    public void test31() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListLogic.Args arg =
            new GetContentIDListLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListLogic logic = new GetContentIDListLogic();
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

        JSONObject expect = new JSONObject("{ " + 
                "\"file_type_cd\" : 1," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0032"
    )
    public void test32() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            false,
                            null,
                            null,
                            null,
                            SortType.SCORE_DESC));
        } catch (Exception e) {
            actualException = e;
        }

        // MAIN TARGET OF THIS TEST.
        assertNotNull(actualException);
        assertEquals(actualException.getClass(), ParameterException.class);
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)actualException).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=1",
            id = "GCILLCR0033"
    )
    public void test33() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListLogic.Args(
                            authenticateContext,
                            FileType.ALL,
                            false,
                            null,
                            null,
                            null,
                            SortType.CREATION_DATETIME_DESC));
        } catch (Exception e) {
            actualException = e;
        }

        // MAIN TARGET OF THIS TEST.
        assertNotNull(actualException);
        assertEquals(actualException.getClass(), ParameterException.class);
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)actualException).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

}
