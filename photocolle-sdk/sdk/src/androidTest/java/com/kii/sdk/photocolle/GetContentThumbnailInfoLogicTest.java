package com.kii.sdk.photocolle;

import static org.mockito.Mockito.*;

import java.net.URL;
import java.util.ArrayList;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import com.kii.example.photocolle.annotation.TestInformation;

public class GetContentThumbnailInfoLogicTest extends TestCaseBase {

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=8",
        id = "GCTILCR0001"
    )
    public void test1() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("dummyGUID");
        GetContentThumbnailInfoLogic.Args arg =
            new GetContentThumbnailInfoLogic.Args(
                authenticateContext,
                guid);
        GetContentThumbnailInfoLogic logic =
                new GetContentThumbnailInfoLogic();
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

        JSONObject expect = new JSONObject("{ \"content_info_list\" : [ \"dummyGUID\" ] }");
        assertEquals(expect.toString(), actual.toString());
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=8",
        id = "GCTILCR0002"
    )
    public void test2() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ArrayList<ContentGUID> guids = createGuids(100);
        GetContentThumbnailInfoLogic.Args arg =
            new GetContentThumbnailInfoLogic.Args(
                authenticateContext,
                guids.toArray(new ContentGUID[] { }));
        GetContentThumbnailInfoLogic logic =
                new GetContentThumbnailInfoLogic();
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

        JSONArray list = new JSONArray();
        for (int i = 0; i < guids.size(); ++i) {
            list.put(guids.get(i).getString());
        }
        JSONObject expect = new JSONObject();
        expect.put("content_info_list", list);

        assertEquals(expect.toString(), actual.toString());
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=8",
        id = "GCTILCR0003"
    )
    public void test3() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
            (new GetContentThumbnailInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentThumbnailInfoLogic.Args(
                            authenticateContext,
                            createGuids(101).toArray(new ContentGUID[] { })));
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
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=8",
        id = "GCTILCR0004"
    )
    public void test4() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
            (new GetContentThumbnailInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentThumbnailInfoLogic.Args(
                            authenticateContext,
                            new ContentGUID[] { }));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=8",
            id = "GCTILCR0005"
        )
    public void test5() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        ContentGUID guid = new ContentGUID("0");
        GetContentThumbnailInfoLogic.Args arg =
            new GetContentThumbnailInfoLogic.Args(
                authenticateContext,
                guid);
        GetContentThumbnailInfoLogic logic =
                new GetContentThumbnailInfoLogic();
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

        JSONObject expect = new JSONObject("{ \"content_info_list\" : [ \"0\" ] }");
        assertEquals(expect.toString(), actual.toString());
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=8",
            id = "GCTILCR0006"
        )
    public void test6() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guidStr = "01234567890123456789012345678901234567890123456789";
        ContentGUID guid = new ContentGUID(guidStr);
        GetContentThumbnailInfoLogic.Args arg =
            new GetContentThumbnailInfoLogic.Args(
                authenticateContext,
                guid);
        GetContentThumbnailInfoLogic logic =
                new GetContentThumbnailInfoLogic();
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

        JSONObject expect = new JSONObject("{ \"content_info_list\" : [ \"" + guidStr + "\" ] }");
        assertEquals(expect.toString(), actual.toString());
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=8",
            id = "GCTILCR0007"
        )
    public void test7() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        Exception exception = null;
        try {
            (new GetContentThumbnailInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentThumbnailInfoLogic.Args(
                            authenticateContext,
                            new ContentGUID("")));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=8",
            id = "GCTILCR0008"
        )
    public void test8() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        String guidStr = "012345678901234567890123456789012345678901234567890";
        Exception exception = null;
        try {
            (new GetContentThumbnailInfoLogic()).createRequest(
                    new URL("http://example.com"),
                    new GetContentThumbnailInfoLogic.Args(
                            authenticateContext,
                            new ContentGUID(guidStr)));
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

    private ArrayList<ContentGUID> createGuids(int num) throws Exception {
        ArrayList<ContentGUID> retval = new ArrayList<ContentGUID>();
        for (int i = 0; i < num; ++i) {
            retval.add(new ContentGUID("dummyGUID_" + i));
        }
        return retval;
    }
}

