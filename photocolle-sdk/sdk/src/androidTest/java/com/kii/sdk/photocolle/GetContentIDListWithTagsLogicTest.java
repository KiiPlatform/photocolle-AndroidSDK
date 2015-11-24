package com.kii.sdk.photocolle;

import static org.mockito.Mockito.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONObject;

import com.kii.example.photocolle.annotation.TestInformation;

import android.annotation.SuppressLint;
import android.test.AndroidTestCase;
import android.text.format.Time;

/**
 * https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2
 *
 * Details of each test methods are described in the spread sheet above.
 * You can find the detail of a test method by searching with test method name.
 *
 * This is test class for GetContentIDListWithTagsLogic#createRequest().
 * In this test case, All arguments GetContentIDListWithTagsLogic#createRequest()
 * were checked, but does not check combination of these arguments.
 */
@SuppressLint("NewApi")
public class GetContentIDListWithTagsLogicTest extends AndroidTestCase {

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0001"
    )
    public void test1() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0002"
    )
    public void test2() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.VIDEO,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0003"
    )
    public void test3() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.SLIDE_MOVIE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0004"
    )
    public void test4() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            null,
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0005"
    )
    public void test5() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
             url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
             id = "GCILWTLCR0006"
    )
    public void test6() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_ASC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0007"
    )
    public void test7() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.MODIFICATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0008"
    )
    public void test8() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.MODIFICATION_DATETIME_ASC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0009"
    )
    public void test9() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.UPLOAD_DATETIME_ASC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0010"
    )
    public void test10() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.UPLOAD_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0011"
    )
    public void test11() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            null,
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0012"
    )
    public void test12() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0013"
    )
    public void test13() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                true,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0014"
    )
    public void test14() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("2013-01-01T00:00:00Z");
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                true,
                new UploadDateFilter(new Date(time.normalize(false))),
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0015"
    )
    public void test15() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("1998-06-15T13:30:25Z");
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                true,
                new UploadDateFilter(new Date(time.normalize(false))),
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0016"
    )
    public void test16() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("2013-01-01T00:00:00Z");
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                true,
                new ModifiedDateFilter(new Date(time.normalize(false))),
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0017"
    )
    public void test17() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("1998-06-15T13:30:25Z");
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                true,
                new ModifiedDateFilter(new Date(time.normalize(false))),
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0018"
    )
    public void test18() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("1998-06-15T13:30:25Z");
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                true,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0019"
    )
    public void test19() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                Integer.valueOf(50),
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0020"
    )
    public void test20() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                Integer.valueOf(1),
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0021"
    )
    public void test21() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                Integer.valueOf(1000),
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"max_results\" : 1000," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0022"
    )
    public void test22() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            null,
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0023"
    )
    public void test23() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            null,
                            false,
                            null,
                            Integer.valueOf(1001),
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0024"
    )
    public void test24() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            null,
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0025"
    )
    public void test25() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            null,
                            false,
                            null,
                            Integer.valueOf(2000),
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0026"
    )
    public void test26() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0027"
    )
    public void test27() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                Integer.valueOf(50),
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0028"
    )
    public void test28() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                Integer.valueOf(1),
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0029"
    )
    public void test29() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            null,
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0030"
    )
    public void test30() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            null,
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0031"
    )
    public void test31() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0032"
    )
    public void test32() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.FILE_COUNT,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                null);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"projection\" : \"1\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0033"
    )
    public void test33() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.ALL_DETAILS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"projection\" : \"2\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0034"
    )
    public void test34() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0035"
    )
    public void test35() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            null,
                            FileType.IMAGE,
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0036"
    )
    public void test36() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0037"
    )
    public void test37() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(new PersonTag(new ContentGUID("dummyGUID"), "dummy",
                0, new Date()));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_1_tag_type\" : 1," +
                "\"criteria_1_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0038"
    )
    public void test38() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(new EventTag(new ContentGUID("dummyGUID"), "dummy",
                0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_1_tag_type\" : 2," +
                "\"criteria_1_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0039"
    )
    public void test39() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(new FavoriteTag(new ContentGUID("dummyGUID"), "dummy",
                0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_1_tag_type\" : 3," +
                "\"criteria_1_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0040"
    )
    public void test40() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(new PlacementTag(new ContentGUID("dummyGUID"),
                "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_1_tag_type\" : 4," +
                "\"criteria_1_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0041"
    )
    public void test41() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(new YearMonthTag(new ContentGUID("dummyGUID"),
                "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_1_tag_type\" : 5," +
                "\"criteria_1_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0042"
    )
    public void test42() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0043"
    )
    public void test43() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(new EventTag(new ContentGUID("0"), "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_1_tag_type\" : 2," +
                "\"criteria_1_guid\" : \"0\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0044"
    )
    public void test44() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guid = "01234567890123456789012345678901234567890123456";
        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(new EventTag(new ContentGUID(guid), "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_1_tag_type\" : 2," +
                "\"criteria_1_guid\" : \"01234567890123456789012345678901234567890123456\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0045"
    )
    public void test45() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(new EventTag(new ContentGUID(""), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0046"
    )
    public void test46() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guid = "012345678901234567890123456789012345678901234567";
        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(new EventTag(new ContentGUID(guid), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0047"
    )
    public void test47() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(new PersonTag(new ContentGUID("dummyGUID"), "dummy",
                0, new Date()));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_2_tag_type\" : 1," +
                "\"criteria_2_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0048"
    )
    public void test48() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID("dummyGUID"), "dummy",
                0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_2_tag_type\" : 2," +
                "\"criteria_2_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0049"
    )
    public void test49() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(new FavoriteTag(new ContentGUID("dummyGUID"), "dummy",
                0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_2_tag_type\" : 3," +
                "\"criteria_2_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0050"
    )
    public void test50() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(new PlacementTag(new ContentGUID("dummyGUID"),
                "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_2_tag_type\" : 4," +
                "\"criteria_2_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0051"
    )
    public void test51() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(new YearMonthTag(new ContentGUID("dummyGUID"),
                "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_2_tag_type\" : 5," +
                "\"criteria_2_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0052"
    )
    public void test52() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0053"
    )
    public void test53() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID("0"), "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_2_tag_type\" : 2," +
                "\"criteria_2_guid\" : \"0\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0054"
    )
    public void test54() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guid = "01234567890123456789012345678901234567890123456";
        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(guid), "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_2_tag_type\" : 2," +
                "\"criteria_2_guid\" : \"01234567890123456789012345678901234567890123456\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0055"
    )
    public void test55() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(""), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0056"
    )
    public void test56() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guid = "012345678901234567890123456789012345678901234567";
        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(guid), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0057"
    )
    public void test57() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new PersonTag(new ContentGUID("dummyGUID"), "dummy",
                0, new Date()));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_3_tag_type\" : 1," +
                "\"criteria_3_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0058"
    )
    public void test58() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID("dummyGUID"), "dummy",
                0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_3_tag_type\" : 2," +
                "\"criteria_3_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0059"
    )
    public void test59() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new FavoriteTag(new ContentGUID("dummyGUID"), "dummy",
                0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_3_tag_type\" : 3," +
                "\"criteria_3_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0060"
    )
    public void test60() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new PlacementTag(new ContentGUID("dummyGUID"),
                "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_3_tag_type\" : 4," +
                "\"criteria_3_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0061"
    )
    public void test61() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new YearMonthTag(new ContentGUID("dummyGUID"),
                "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_3_tag_type\" : 5," +
                "\"criteria_3_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0062"
    )
    public void test62() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0063"
    )
    public void test63() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID("0"), "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_3_tag_type\" : 2," +
                "\"criteria_3_guid\" : \"0\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0064"
    )
    public void test64() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guid = "01234567890123456789012345678901234567890123456";
        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(guid), "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_3_tag_type\" : 2," +
                "\"criteria_3_guid\" : \"01234567890123456789012345678901234567890123456\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0065"
    )
    public void test65() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(""), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0066"
    )
    public void test66() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guid = "012345678901234567890123456789012345678901234567";
        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(guid), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0067"
    )
    public void test67() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new PersonTag(new ContentGUID("dummyGUID"), "dummy",
                0, new Date()));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_4_tag_type\" : 1," +
                "\"criteria_4_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0068"
    )
    public void test68() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID("dummyGUID"), "dummy",
                0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_4_tag_type\" : 2," +
                "\"criteria_4_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0069"
    )
    public void test69() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new FavoriteTag(new ContentGUID("dummyGUID"), "dummy",
                0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_4_tag_type\" : 3," +
                "\"criteria_4_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0070"
    )
    public void test70() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new PlacementTag(new ContentGUID("dummyGUID"),
                "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_4_tag_type\" : 4," +
                "\"criteria_4_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0071"
    )
    public void test71() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new YearMonthTag(new ContentGUID("dummyGUID"),
                "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_4_tag_type\" : 5," +
                "\"criteria_4_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0072"
    )
    public void test72() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0073"
    )
    public void test73() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID("0"), "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_4_tag_type\" : 2," +
                "\"criteria_4_guid\" : \"0\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0074"
    )
    public void test74() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guid = "01234567890123456789012345678901234567890123456";
        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(guid), "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_4_tag_type\" : 2," +
                "\"criteria_4_guid\" : \"01234567890123456789012345678901234567890123456\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0075"
    )
    public void test75() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(""), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0076"
    )
    public void test76() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guid = "012345678901234567890123456789012345678901234567";
        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(guid), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0077"
    )
    public void test77() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new PersonTag(new ContentGUID("dummyGUID"), "dummy",
                0, new Date()));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_5_tag_type\" : 1," +
                "\"criteria_5_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0078"
    )
    public void test78() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID("dummyGUID"), "dummy",
                0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_5_tag_type\" : 2," +
                "\"criteria_5_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0079"
    )
    public void test79() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new FavoriteTag(new ContentGUID("dummyGUID"), "dummy",
                0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_5_tag_type\" : 3," +
                "\"criteria_5_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0080"
    )
    public void test80() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new PlacementTag(new ContentGUID("dummyGUID"),
                "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_5_tag_type\" : 4," +
                "\"criteria_5_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0081"
    )
    public void test81() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new YearMonthTag(new ContentGUID("dummyGUID"),
                "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_5_tag_type\" : 5," +
                "\"criteria_5_guid\" : \"dummyGUID\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0082"
    )
    public void test82() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0083"
    )
    public void test83() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID("0"), "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_5_tag_type\" : 2," +
                "\"criteria_5_guid\" : \"0\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0084"
    )
    public void test84() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guid = "01234567890123456789012345678901234567890123456";
        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(guid), "dummy", 0));
        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                criteriaList,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"criteria_5_tag_type\" : 2," +
                "\"criteria_5_guid\" : \"01234567890123456789012345678901234567890123456\"," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0085"
    )
    public void test85() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(""), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0086"
    )
    public void test86() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guid = "012345678901234567890123456789012345678901234567";
        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID(guid), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0087"
    )
    public void test87() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        List<TagHead> criteriaList = new ArrayList<TagHead>();
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(null);
        criteriaList.add(new EventTag(new ContentGUID("dummyGUID"), "dummy", 0));
        Exception actualException = null;
        try {
            (new GetContentIDListWithTagsLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentIDListWithTagsLogic.Args(
                            authenticateContext,
                            ProjectionType.DETAILS_WITHOUT_TAGS,
                            FileType.IMAGE,
                            criteriaList,
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0088"
    )
    public void test88() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.SCORE_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"sort_type\" : 7 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0089"
    )
    public void test89() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.FILE_COUNT,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                null);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"projection\" : \"1\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0090"
    )
    public void test90() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.FILE_COUNT,
                FileType.VIDEO,
                null,
                false,
                null,
                null,
                null,
                null);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"projection\" : \"1\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0091"
    )
    public void test91() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.FILE_COUNT,
                FileType.SLIDE_MOVIE,
                null,
                false,
                null,
                null,
                null,
                null);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"projection\" : \"1\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0092"
    )
    public void test92() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
                new GetContentIDListWithTagsLogic.Args(
                    authenticateContext,
                    ProjectionType.FILE_COUNT,
                    FileType.IMAGE,
                    null,
                    false,
                    null,
                    null,
                    null,
                    SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
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
                "\"projection\" : \"1\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0093"
    )
    public void test93() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339("2013-01-01T00:00:00Z");
        GetContentIDListWithTagsLogic.Args arg =
                new GetContentIDListWithTagsLogic.Args(
                    authenticateContext,
                    ProjectionType.FILE_COUNT,
                    FileType.IMAGE,
                    null,
                    false,
                    new UploadDateFilter(new Date(time.normalize(false))),
                    null,
                    null,
                    null);

        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
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
                "\"projection\" : \"1\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0094"
    )
    public void test94() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
                new GetContentIDListWithTagsLogic.Args(
                    authenticateContext,
                    ProjectionType.FILE_COUNT,
                    FileType.IMAGE,
                    null,
                    false,
                    null,
                    Integer.valueOf(50),
                    null,
                    null);

        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
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
                "\"projection\" : \"1\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0095"
    )
    public void test95() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
                new GetContentIDListWithTagsLogic.Args(
                    authenticateContext,
                    ProjectionType.FILE_COUNT,
                    FileType.IMAGE,
                    null,
                    false,
                    null,
                    null,
                    Integer.valueOf(50),
                    null);
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
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
                "\"projection\" : \"1\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0096"
    )
    public void test96() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
                new GetContentIDListWithTagsLogic.Args(
                    authenticateContext,
                    ProjectionType.FILE_COUNT,
                    FileType.IMAGE,
                    new ArrayList<TagHead>(),
                    false,
                    null,
                    null,
                    null,
                    null);
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
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
                "\"projection\" : \"1\" }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=2",
            id = "GCILWTLCR0097"
    )
    public void test97() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetContentIDListWithTagsLogic.Args arg =
            new GetContentIDListWithTagsLogic.Args(
                authenticateContext,
                ProjectionType.DETAILS_WITHOUT_TAGS,
                FileType.ALL,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
        GetContentIDListWithTagsLogic logic = new GetContentIDListWithTagsLogic();
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
                "\"file_type_cd\" : 0," +
                "\"projection\" : \"3\"," +
                "\"dustbox_condition\" : 1," +
                "\"sort_type\" : 1 }");

        TestUtils.assertJSONObjectEquals(expect, actual);
    }

}
