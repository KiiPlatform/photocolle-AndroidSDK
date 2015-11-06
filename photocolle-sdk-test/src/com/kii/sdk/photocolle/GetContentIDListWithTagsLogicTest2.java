package com.kii.sdk.photocolle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mockito.internal.util.reflection.Whitebox;

import com.kii.example.photocolle.annotation.TestInformation;

import android.annotation.SuppressLint;
import android.test.AndroidTestCase;
import android.text.format.Time;

public class GetContentIDListWithTagsLogicTest2 extends AndroidTestCase {

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0001"
    )
    public void test1() throws Exception {
        // make mocks.
        byte[] data = "{ \"result\" : 1 }".getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.FILE_COUNT));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0002"
    )
    public void test2() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 1," +
                "\"next_page\" : 0 }").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.FILE_COUNT));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 50);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0003"
    )
    public void test3() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 0," +
                "\"start\" : 1," +
                "\"next_page\" : 0 }").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.FILE_COUNT));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 0);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0004"
    )
    public void test4() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 1000," +
                "\"start\" : 1," +
                "\"next_page\" : 0 }").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.FILE_COUNT));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1000);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0005"
    )
    public void test5() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : -1," +
                "\"start\" : 1," +
                "\"next_page\" : 0 }").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.FILE_COUNT));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    /*
    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0006"
    )
    public void test6() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 101," +
                "\"start\" : 1," +
                "\"next_page\" : 0," +
                "\"content_list\" : ["
                + "]}").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
             (new GetContentIDListWithTagsLogic()).parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }
    */

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0007"
    )
    public void test7() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"start\" : 1," +
                "\"next_page\" : 0 }").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.FILE_COUNT));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0008"
    )
    public void test8() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 5," +
                "\"next_page\" : 0," +
                "\"content_list\" : ["
                + "]}").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(5, 0, 50);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0009"
    )
    public void test9() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 1," +
                "\"next_page\" : 0," +
                "\"content_list\" : ["
                + "]}").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 50);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0010"
    )
    public void test10() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 0," +
                "\"next_page\" : 0," +
                "\"content_list\" : ["
                + "]}").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0011"
    )
    public void test11() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"next_page\" : 0 }").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.FILE_COUNT));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(-1, 0, 50);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0012"
    )
    public void test12() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 1," +
                "\"next_page\" : 5," +
                "\"content_list\" : ["
                + "]}").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 5, 50);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0013"
    )
    public void test13() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 1," +
                "\"next_page\" : 0," +
                "\"content_list\" : ["
                + "]}").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 50);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0014"
    )
    public void test14() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 1," +
                "\"next_page\" : -1," +
                "\"content_list\" : ["
                + "]}").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0015"
    )
    public void test15() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 1 }").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.FILE_COUNT));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, -1, 50);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0016"
    )
    public void test16() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 50; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("content_guid", "dummy_" + i);
            obj.put("content_name", "name_" + i);
            obj.put("file_type_cd", 1);
            obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
            obj.put("mdate", "1970-01-01T00:00:00+09:00");
            obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
            obj.put("file_data_size", 0);
            obj.put("resize_ng_flg", "1");
            obj.put("file_data_xy_rate", "0.75");
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 50);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 50);
        for (int i = 0; i < 50; ++i) {
            DetailedContentInfo info = new DetailedContentInfo(
                    new ContentGUID("dummy_" + i),
                    "name_" + i, FileType.IMAGE,
                    parse3339("1970-01-01T00:00:00+09:00"),
                    parse3339("1970-01-01T00:00:00+09:00"),
                    parse3339("1970-01-01T00:00:00+09:00"),
                    0, "0.75", null, null, false,
                    new ArrayList<NamedTagHead>(),
                    new ArrayList<NamedTagHead>(),
                    new ArrayList<NamedTagHead>(),
                    new ArrayList<NamedTagHead>(),
                    new ArrayList<NamedTagHead>());
            expectList.getList().add(info);
        }

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0017"
    )
    public void test17() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 0);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");


        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 0);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0018"
    )
    public void test18() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 1000; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("content_guid", "dummy_" + i);
            obj.put("content_name", "name_" + i);
            obj.put("file_type_cd", 1);
            obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
            obj.put("mdate", "1970-01-01T00:00:00+09:00");
            obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
            obj.put("file_data_size", 0);
            obj.put("resize_ng_flg", "1");
            obj.put("file_data_xy_rate", "0.75");
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1000);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1000);
        for (int i = 0; i < 1000; ++i) {
            DetailedContentInfo info = new DetailedContentInfo(
                    new ContentGUID("dummy_" + i),
                    "name_" + i, FileType.IMAGE,
                    parse3339("1970-01-01T00:00:00+09:00"),
                    parse3339("1970-01-01T00:00:00+09:00"),
                    parse3339("1970-01-01T00:00:00+09:00"),
                    0, "0.75", null, null, false,
                    new ArrayList<NamedTagHead>(),
                    new ArrayList<NamedTagHead>(),
                    new ArrayList<NamedTagHead>(),
                    new ArrayList<NamedTagHead>(),
                    new ArrayList<NamedTagHead>());
            expectList.getList().add(info);
        }

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0019"
    )
    public void test19() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 1001; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("content_guid", "dummy_" + i);
            obj.put("content_name", "name_" + i);
            obj.put("file_type_cd", 1);
            obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
            obj.put("mdate", "1970-01-01T00:00:00+09:00");
            obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
            obj.put("file_data_size", 0);
            obj.put("resize_ng_flg", "1");
            obj.put("file_data_xy_rate", "0.75");
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1001);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0020"
    )
    public void test20() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0021"
    )
    public void test21() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0022"
    )
    public void test22() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "01234567890123456789012345678901234567890123456789");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("01234567890123456789012345678901234567890123456789"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0023"
    )
    public void test23() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0024"
    )
    public void test24() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "012345678901234567890123456789012345678901234567890");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0025"
    )
    public void test25() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0026"
    )
    public void test26() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "012345678901234567890123456789012345678901234");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "012345678901234567890123456789012345678901234",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0027"
    )
    public void test27() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0028"
    )
    public void test28() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name",
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0029"
    )
    public void test29() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0030"
    )
    public void test30() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name",
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "0123456789012345");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0031"
    )
    public void test31() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0032"
    )
    public void test32() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0033"
    )
    public void test33() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 2);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.VIDEO,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0034"
    )
    public void test34() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 3);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.SLIDE_MOVIE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0035"
    )
    public void test35() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", -1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0036"
    )
    public void test36() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 4);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0037"
    )
    public void test37() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0038"
    )
    public void test38() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "2013-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("2013-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0039"
    )
    public void test39() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0040"
    )
    public void test40() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "2013-01-01T00:00:00+09:00");
        obj.put("mdate", "1998-06-15T13:30:25+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("2013-01-01T00:00:00+09:00"),
                parse3339("1998-06-15T13:30:25+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0041"
    )
    public void test41() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0042"
    )
    public void test42() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "2013-01-01T00:00:00+09:00");
        obj.put("mdate", "1998-06-15T13:30:25+09:00");
        obj.put("upload_datetime", "1989-01-07T12:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("2013-01-01T00:00:00+09:00"),
                parse3339("1998-06-15T13:30:25+09:00"),
                parse3339("1989-01-07T12:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0043"
    )
    public void test43() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0044"
    )
    public void test44() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 1234);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                1234L, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0045"
    )
    public void test45() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0046"
    )
    public void test46() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "0");
        obj.put("file_data_xy_rate", "1.3");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "1.3", null, null, true,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0047"
    )
    public void test47() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0048"
    )
    public void test48() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0049"
    )
    public void test49() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("prf5score", 1);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", Score.SCORE_1, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0050"
    )
    public void test50() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("prf5score", 2);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", Score.SCORE_2, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0051"
    )
    public void test51() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("prf5score", 3);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", Score.SCORE_3, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0052"
    )
    public void test52() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("prf5score", 4);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", Score.SCORE_4, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0053"
    )
    public void test53() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("prf5score", 5);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", Score.SCORE_5, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0054"
    )
    public void test54() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("prf5score", 0);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0055"
    )
    public void test55() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("prf5score", 6);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0056"
    )
    public void test56() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0057"
    )
    public void test57() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("orientation", 0);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, Orientation.ROTATE_0, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0058"
    )
    public void test58() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("orientation", 90);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, Orientation.ROTATE_90, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0059"
    )
    public void test59() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("orientation", 180);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, Orientation.ROTATE_180, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0060"
    )
    public void test60() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("orientation", 270);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, Orientation.ROTATE_270, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0061"
    )
    public void test61() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("orientation", 360);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0062"
    )
    public void test62() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0063"
    )
    public void test63() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_guid", "person_guid");
        personTag.put("person_tag_name", "Jhon Smith");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> personTagList = new ArrayList<NamedTagHead>();
        personTagList.add(new NamedTagHead(TagType.PERSON,
                new ContentGUID("person_guid"), "Jhon Smith"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                personTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0064"
    )
    public void test64() throws Exception {
        // make mocks.
        JSONArray personList = new JSONArray();
        for (int i = 0; i < 50; ++i) {
            JSONObject personTag = new JSONObject();
            personTag.put("person_tag_guid", "person_guid_" + i);
            personTag.put("person_tag_name", "Jhon Smith." + i);
            personList.put(personTag);
        }
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> personTagList = new ArrayList<NamedTagHead>();
        for (int i = 0; i < 50; ++i) {
            personTagList.add(new NamedTagHead(TagType.PERSON,
                    new ContentGUID("person_guid_" + i), "Jhon Smith." + i));
        }
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                personTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0065"
    )
    public void test65() throws Exception {
        // make mocks.
        JSONArray personList = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0066"
    )
    public void test66() throws Exception {
        // make mocks.
        JSONArray personList = new JSONArray();
        for (int i = 0; i < 51; ++i) {
            JSONObject personTag = new JSONObject();
            personTag.put("person_tag_guid", "person_guid_" + i);
            personTag.put("person_tag_name", "Jhon Smith." + i);
            personList.put(personTag);
        }
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0067"
    )
    public void test67() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", null);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> personTagList = new ArrayList<NamedTagHead>();
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                personTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0068"
    )
    public void test68() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_guid", "event_guid");
        eventTag.put("event_tag_name", "summer_fes");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> eventTagList = new ArrayList<NamedTagHead>();
        eventTagList.add(new NamedTagHead(TagType.EVENT,
                new ContentGUID("event_guid"), "summer_fes"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                eventTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0069"
    )
    public void test69() throws Exception {
        // make mocks.
        JSONArray eventList = new JSONArray();
        for (int i = 0; i < 100; ++i) {
            JSONObject eventTag = new JSONObject();
            eventTag.put("event_tag_guid", "event_guid_" + i);
            eventTag.put("event_tag_name", "summer_fes." + i);
            eventList.put(eventTag);
        }
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> eventTagList = new ArrayList<NamedTagHead>();
        for (int i = 0; i < 100; ++i) {
            eventTagList.add(new NamedTagHead(TagType.EVENT,
                    new ContentGUID("event_guid_" + i), "summer_fes." + i));
        }
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                eventTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0070"
    )
    public void test70() throws Exception {
        // make mocks.
        JSONArray eventList = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0071"
    )
    public void test71() throws Exception {
        // make mocks.
        JSONArray eventList = new JSONArray();
        for (int i = 0; i < 101; ++i) {
            JSONObject eventTag = new JSONObject();
            eventTag.put("event_tag_guid", "event_guid_" + i);
            eventTag.put("event_tag_name", "summer_fes." + i);
            eventList.put(eventTag);
        }
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0072"
    )
    public void test72() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", null);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> eventTagList = new ArrayList<NamedTagHead>();
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                eventTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0073"
    )
    public void test73() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_guid", "optional_guid");
        favoriteTag.put("optional_tag_name", "optional_name");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> favoriteTagList = new ArrayList<NamedTagHead>();
        favoriteTagList.add(new NamedTagHead(TagType.FAVORITE,
                new ContentGUID("optional_guid"), "optional_name"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                favoriteTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0074"
    )
    public void test74() throws Exception {
        // make mocks.
        JSONArray favoriteList = new JSONArray();
        for (int i = 0; i < 100; ++i) {
            JSONObject favoriteTag = new JSONObject();
            favoriteTag.put("optional_tag_guid", "optional_guid_" + i);
            favoriteTag.put("optional_tag_name", "optional_name_" + i);
            favoriteList.put(favoriteTag);
        }
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> favoriteTagList = new ArrayList<NamedTagHead>();
        for (int i = 0; i < 100; ++i) {
            favoriteTagList.add(new NamedTagHead(TagType.FAVORITE,
                    new ContentGUID("optional_guid_" + i),
                    "optional_name_" + i));
        }
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                favoriteTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0075"
    )
    public void test75() throws Exception {
        // make mocks.
        JSONArray favoriteList = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0076"
    )
    public void test76() throws Exception {
        // make mocks.
        JSONArray favoriteList = new JSONArray();
        for (int i = 0; i < 101; ++i) {
            JSONObject favoriteTag = new JSONObject();
            favoriteTag.put("optional_tag_guid", "optional_guid_" + i);
            favoriteTag.put("optional_tag_name", "optional_name_" + i);
            favoriteList.put(favoriteTag);
        }
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0077"
    )
    public void test77() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", null);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> favoriteTagList = new ArrayList<NamedTagHead>();
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                favoriteTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0078"
    )
    public void test78() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_tag_guid", "place_guid");
        placeTag.put("place_name", "place_name");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> placeTagList = new ArrayList<NamedTagHead>();
        placeTagList.add(new NamedTagHead(TagType.PLACEMENT,
                new ContentGUID("place_guid"), "place_name"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                placeTagList,
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0079"
    )
    public void test79() throws Exception {
        // make mocks.
        JSONArray placeList = new JSONArray();
        for (int i = 0; i < 50; ++i) {
            JSONObject placeTag = new JSONObject();
            placeTag.put("place_tag_guid", "place_guid_" + i);
            placeTag.put("place_name", "place_" + i);
            placeList.put(placeTag);
        }
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> placeTagList = new ArrayList<NamedTagHead>();
        for (int i = 0; i < 50; ++i) {
            placeTagList.add(new NamedTagHead(TagType.PLACEMENT,
                    new ContentGUID("place_guid_" + i), "place_" + i));
        }
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                placeTagList,
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0080"
    )
    public void test80() throws Exception {
        // make mocks.
        JSONArray placeList = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    /*
    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0081"
    )
    public void test81() throws Exception {
        // make mocks.
        JSONArray placeList = new JSONArray();
        for (int i = 0; i < 51; ++i) {
            JSONObject placeTag = new JSONObject();
            placeTag.put("place_tag_guid", "place_guid_" + i);
            placeTag.put("place_name", "place_" + i);
            placeList.put(placeTag);
        }
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
             (new GetContentIDListWithTagsLogic()).parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }
    */

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0032"
    )
    public void test82() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", null);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> placeTagList = new ArrayList<NamedTagHead>();
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                placeTagList,
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0083"
    )
    public void test83() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_guid", "month_guid");
        monthTag.put("month_tag_name", "month_name");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> yearMonthTagList = new ArrayList<NamedTagHead>();
        yearMonthTagList.add(new NamedTagHead(TagType.YEAR_MONTH,
                new ContentGUID("month_guid"), "month_name"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                yearMonthTagList));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0084"
    )
    public void test84() throws Exception {
        // make mocks.
        JSONArray monthList = new JSONArray();
        for (int i = 0; i < 50; ++i) {
            JSONObject monthTag = new JSONObject();
            monthTag.put("month_tag_guid", "month_guid_" + i);
            monthTag.put("month_tag_name", "month_name_" + i);
            monthList.put(monthTag);
        }
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> yearMonthTagList = new ArrayList<NamedTagHead>();
        for (int i = 0; i < 50; ++i) {
            yearMonthTagList.add(new NamedTagHead(TagType.YEAR_MONTH,
                    new ContentGUID("month_guid_" + i), "month_name_" + i));
        }
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                yearMonthTagList));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0085"
    )
    public void test85() throws Exception {
        // make mocks.
        JSONArray monthList = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    /*
    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0086"
    )
    public void test86() throws Exception {
        // make mocks.
        JSONArray monthList = new JSONArray();
        for (int i = 0; i < 51; ++i) {
            JSONObject monthTag = new JSONObject();
            monthTag.put("month_tag_guid", "month_guid_" + i);
            monthTag.put("month_tag_name", "month_name_" + i);
            monthList.put(monthTag);
        }
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
             (new GetContentIDListWithTagsLogic()).parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }
    */

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0087"
    )
    public void test87() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", null);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> yaerMonthTagList = new ArrayList<NamedTagHead>();
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                yaerMonthTagList));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0088"
    )
    public void test88() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_guid", "0");
        personTag.put("person_tag_name", "Jhon Smith");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> personTagList = new ArrayList<NamedTagHead>();
        personTagList.add(new NamedTagHead(TagType.PERSON,
                new ContentGUID("0"), "Jhon Smith"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                personTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0089"
    )
    public void test89() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_guid",
                "01234567890123456789012345678901234567890123456");
        personTag.put("person_tag_name", "Jhon Smith");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> personTagList = new ArrayList<NamedTagHead>();
        personTagList.add(new NamedTagHead(TagType.PERSON,
                new ContentGUID("01234567890123456789012345678901234567890123456"),
                "Jhon Smith"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                personTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0090"
    )
    public void test90() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_guid", "");
        personTag.put("person_tag_name", "Jhon Smith");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0091"
    )
    public void test91() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_guid",
                "012345678901234567890123456789012345678901234567");
        personTag.put("person_tag_name", "Jhon Smith");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0092"
    )
    public void test92() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_name", "Jhon Smith");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0093"
    )
    public void test93() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_guid", "person_guid");
        personTag.put("person_tag_name", "0");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> personTagList = new ArrayList<NamedTagHead>();
        personTagList.add(new NamedTagHead(TagType.PERSON,
                new ContentGUID("person_guid"), "0"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                personTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0094"
    )
    public void test94() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_guid", "person_guid");
        personTag.put("person_tag_name", "01234567890123456789");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> personTagList = new ArrayList<NamedTagHead>();
        personTagList.add(new NamedTagHead(TagType.PERSON,
                new ContentGUID("person_guid"), "01234567890123456789"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                personTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0095"
    )
    public void test95() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_guid", "person_guid");
        personTag.put("person_tag_name", "");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0096"
    )
    public void test96() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_guid", "person_guid");
        personTag.put("person_tag_name", "012345678901234567890");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0097"
    )
    public void test97() throws Exception {
        // make mocks.
        JSONObject personTag = new JSONObject();
        personTag.put("person_tag_guid", "person_guid");
        JSONArray personList = new JSONArray();
        personList.put(personTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("person_tag_list", personList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0098"
    )
    public void test98() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_guid", "0");
        eventTag.put("event_tag_name", "summer_fes");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> eventTagList = new ArrayList<NamedTagHead>();
        eventTagList.add(new NamedTagHead(TagType.EVENT,
                new ContentGUID("0"), "summer_fes"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                eventTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0099"
    )
    public void test99() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_guid",
                "01234567890123456789012345678901234567890123456");
        eventTag.put("event_tag_name", "summer_fes");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> eventTagList = new ArrayList<NamedTagHead>();
        eventTagList.add(new NamedTagHead(TagType.EVENT,
                new ContentGUID("01234567890123456789012345678901234567890123456"),
                "summer_fes"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                eventTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0100"
    )
    public void test100() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_guid", "");
        eventTag.put("event_tag_name", "summer_fes");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0101"
    )
    public void test101() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_guid",
                "012345678901234567890123456789012345678901234567");
        eventTag.put("event_tag_name", "summer_fes");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0102"
    )
    public void test102() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_name", "summer_fes");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0103"
    )
    public void test103() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_guid", "event_guid");
        eventTag.put("event_tag_name", "0");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> eventTagList = new ArrayList<NamedTagHead>();
        eventTagList.add(new NamedTagHead(TagType.EVENT,
                new ContentGUID("event_guid"), "0"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                eventTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0104"
    )
    public void test104() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_guid", "event_guid");
        eventTag.put("event_tag_name", "01234567890123456789");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> eventTagList = new ArrayList<NamedTagHead>();
        eventTagList.add(new NamedTagHead(TagType.EVENT,
                new ContentGUID("event_guid"), "01234567890123456789"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                eventTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0105"
    )
    public void test105() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_guid", "event_guid");
        eventTag.put("event_tag_name", "");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0106"
    )
    public void test106() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_guid", "event_guid");
        eventTag.put("event_tag_name", "012345678901234567890");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0107"
    )
    public void test107() throws Exception {
        // make mocks.
        JSONObject eventTag = new JSONObject();
        eventTag.put("event_tag_guid", "event_guid");
        JSONArray eventList = new JSONArray();
        eventList.put(eventTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("event_tag_list", eventList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0108"
    )
    public void test108() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_guid", "0");
        favoriteTag.put("optional_tag_name", "optional_name");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> favoriteTagList = new ArrayList<NamedTagHead>();
        favoriteTagList.add(new NamedTagHead(TagType.FAVORITE,
                new ContentGUID("0"), "optional_name"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                favoriteTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0109"
    )
    public void test109() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_guid",
                "01234567890123456789012345678901234567890123456");
        favoriteTag.put("optional_tag_name", "optional_name");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> favoriteTagList = new ArrayList<NamedTagHead>();
        favoriteTagList.add(new NamedTagHead(TagType.FAVORITE,
                new ContentGUID("01234567890123456789012345678901234567890123456"),
                "optional_name"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                favoriteTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0110"
    )
    public void test110() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_guid", "");
        favoriteTag.put("optional_tag_name", "optional_name");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0111"
    )
    public void test111() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_guid",
                "012345678901234567890123456789012345678901234567");
        favoriteTag.put("optional_tag_name", "optional_name");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0110"
    )
    public void test112() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_name", "optional_name");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0113"
    )
    public void test113() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_guid", "optional_guid");
        favoriteTag.put("optional_tag_name", "0");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> favoriteTagList = new ArrayList<NamedTagHead>();
        favoriteTagList.add(new NamedTagHead(TagType.FAVORITE,
                new ContentGUID("optional_guid"), "0"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                favoriteTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0114"
    )
    public void test114() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_guid", "optional_guid");
        favoriteTag.put("optional_tag_name", "01234567890123456789");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> favoriteTagList = new ArrayList<NamedTagHead>();
        favoriteTagList.add(new NamedTagHead(TagType.FAVORITE,
                new ContentGUID("optional_guid"), "01234567890123456789"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                favoriteTagList,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0115"
    )
    public void test115() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_guid", "optional_guid");
        favoriteTag.put("optional_tag_name", "");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0116"
    )
    public void test116() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_guid", "optional_guid");
        favoriteTag.put("optional_tag_name", "012345678901234567890");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0117"
    )
    public void test117() throws Exception {
        // make mocks.
        JSONObject favoriteTag = new JSONObject();
        favoriteTag.put("optional_tag_guid", "optional_guid");
        JSONArray favoriteList = new JSONArray();
        favoriteList.put(favoriteTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("optional_tag_list", favoriteList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0118"
    )
    public void test118() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_tag_guid", "0");
        placeTag.put("place_name", "place_name");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> placeTagList = new ArrayList<NamedTagHead>();
        placeTagList.add(new NamedTagHead(TagType.PLACEMENT,
                new ContentGUID("0"), "place_name"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                placeTagList,
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0119"
    )
    public void test119() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_tag_guid",
                "01234567890123456789012345678901234567890123456");
        placeTag.put("place_name", "place_name");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> placeTagList = new ArrayList<NamedTagHead>();
        placeTagList.add(new NamedTagHead(TagType.PLACEMENT,
                new ContentGUID("01234567890123456789012345678901234567890123456"),
                "place_name"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                placeTagList,
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0120"
    )
    public void test120() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_tag_guid", "");
        placeTag.put("place_name", "place_name");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0121"
    )
    public void test121() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_tag_guid",
                "012345678901234567890123456789012345678901234567");
        placeTag.put("place_name", "place_name");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0122"
    )
    public void test122() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_name", "place_name");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0123"
    )
    public void test123() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_tag_guid", "place_guid");
        placeTag.put("place_name", "0");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> placeTagList = new ArrayList<NamedTagHead>();
        placeTagList.add(new NamedTagHead(TagType.PLACEMENT,
                new ContentGUID("place_guid"), "0"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                placeTagList,
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0124"
    )
    public void test124() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_tag_guid", "place_guid");
        placeTag.put("place_name", "01234567890123456789");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> placeTagList = new ArrayList<NamedTagHead>();
        placeTagList.add(new NamedTagHead(TagType.PLACEMENT,
                new ContentGUID("place_guid"), "01234567890123456789"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                placeTagList,
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0125"
    )
    public void test125() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_tag_guid", "place_guid");
        placeTag.put("place_name", "");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0126"
    )
    public void test126() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_tag_guid", "place_guid");
        placeTag.put("place_name", "012345678901234567890");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0127"
    )
    public void test127() throws Exception {
        // make mocks.
        JSONObject placeTag = new JSONObject();
        placeTag.put("place_tag_guid", "place_guid");
        JSONArray placeList = new JSONArray();
        placeList.put(placeTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("place_info_list", placeList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0128"
    )
    public void test128() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_guid", "0");
        monthTag.put("month_tag_name", "month_name");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> yearMonthTagList = new ArrayList<NamedTagHead>();
        yearMonthTagList.add(new NamedTagHead(TagType.YEAR_MONTH,
                new ContentGUID("0"), "month_name"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                yearMonthTagList));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0129"
    )
    public void test129() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_guid",
                "01234567890123456789012345678901234567890123456");
        monthTag.put("month_tag_name", "month_name");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> yearMonthTagList = new ArrayList<NamedTagHead>();
        yearMonthTagList.add(new NamedTagHead(TagType.YEAR_MONTH,
                new ContentGUID("01234567890123456789012345678901234567890123456"),
                "month_name"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                yearMonthTagList));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0130"
    )
    public void test130() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_guid", "");
        monthTag.put("month_tag_name", "month_name");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0131"
    )
    public void test131() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_guid",
                "012345678901234567890123456789012345678901234567");
        monthTag.put("month_tag_name", "month_name");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0132"
    )
    public void test132() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_name", "month_name");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0133"
    )
    public void test133() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_guid", "month_guid");
        monthTag.put("month_tag_name", "0");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> yearMonthTagList = new ArrayList<NamedTagHead>();
        yearMonthTagList.add(new NamedTagHead(TagType.YEAR_MONTH,
                new ContentGUID("month_guid"), "0"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                yearMonthTagList));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0134"
    )
    public void test134() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_guid", "month_guid");
        monthTag.put("month_tag_name", "01234567890123456789");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.ALL_DETAILS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        List<NamedTagHead> yearMonthTagList = new ArrayList<NamedTagHead>();
        yearMonthTagList.add(new NamedTagHead(TagType.YEAR_MONTH,
                new ContentGUID("month_guid"), "01234567890123456789"));
        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0123456789012345"),
                "name_0", FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                yearMonthTagList));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0135"
    )
    public void test135() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_guid", "month_guid");
        monthTag.put("month_tag_name", "");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0136"
    )
    public void test136() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_guid", "month_guid");
        monthTag.put("month_tag_name", "012345678901234567890");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                ((ParameterException)cause).getReason());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0137"
    )
    public void test137() throws Exception {
        // make mocks.
        JSONObject monthTag = new JSONObject();
        monthTag.put("month_tag_guid", "month_guid");
        JSONArray monthList = new JSONArray();
        monthList.put(monthTag);
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0123456789012345");
        obj.put("content_name", "name_0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        obj.put("month_info_list", monthList);
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0138"
    )
    public void test138() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "0");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, true,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0139"
    )
    public void test139() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 1);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "0");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0140"
    )
    public void test140() throws Exception {
        // make mocks.
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "0");
        obj.put("content_name", "0");
        obj.put("file_type_cd", 0);
        obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
        obj.put("mdate", "1970-01-01T00:00:00+09:00");
        obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
        obj.put("file_data_size", 0);
        obj.put("resize_ng_flg", "1");
        obj.put("file_data_xy_rate", "0.75");
        JSONArray list = new JSONArray();
        list.put(obj);
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 1);
        root.put("result", 0);
        byte[] data = root.toString().getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 1);
        expectList.getList().add(new DetailedContentInfo(
                new ContentGUID("0"),
                "0",
                FileType.ALL,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, "0.75", null, null, false,
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>(),
                new ArrayList<NamedTagHead>()));

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0141"
    )
    public void test141() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"next_page\" : 0," +
                "\"content_list\" : ["
                + "]}").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        ListResponse<DetailedContentInfo> responseList = null;
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.DETAILS_WITHOUT_TAGS));
            responseList = logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNull(responseList);
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0142"
    )
    public void test142() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 1," +
                "\"content_list\" : ["
                + "]}").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        ListResponse<DetailedContentInfo> responseList = null;
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            responseList = logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNull(responseList);
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(JSONException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0143"
    )
    public void test143() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 1," +
                "\"next_page\" : 0" +
                " }").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        GetContentIDListWithTagsLogic logic =
                new GetContentIDListWithTagsLogic();
        Whitebox.setInternalState(logic, "arg",
                createArgs(ProjectionType.FILE_COUNT));
        ListResponse<DetailedContentInfo> responseList =
                logic.parseResponse(response);
        assertNotNull(responseList);

        ListResponse<DetailedContentInfo> expectList =
                new ListResponse<DetailedContentInfo>(1, 0, 50);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=18",
            id = "GCILWTLPR0144"
    )
    public void test144() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 50," +
                "\"start\" : 1," +
                "\"next_page\" : 0" +
                " }").getBytes("UTF-8");

        NameValuePair pair = mock(NameValuePair.class);
        when(pair.getName()).thenReturn("charset");
        when(pair.getValue()).thenReturn("UTF-8");
        HeaderElement element = mock(HeaderElement.class);
        when(element.getParameterByName("charset")).thenReturn(pair);
        Header header = mock(Header.class);
        when(header.getElements()).thenReturn(
            new HeaderElement[] { element });
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContentType()).thenReturn(header);
        when(entity.getContentLength()).thenReturn(
            Integer.valueOf(data.length).longValue());
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(data));
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);

        // execute parseResponse().
        ListResponse<DetailedContentInfo> responseList = null;
        Exception exception = null;
        try {
            GetContentIDListWithTagsLogic logic =
                    new GetContentIDListWithTagsLogic();
            Whitebox.setInternalState(logic, "arg",
                    createArgs(ProjectionType.ALL_DETAILS));
            responseList = logic.parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNull(responseList);
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(ParameterException.class, cause.getClass());

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    protected void assertListResponse(
            ListResponse<DetailedContentInfo> expectList,
            ListResponse<DetailedContentInfo> actualList)
        throws Exception
    {
        assertNotNull(actualList);

        assertEquals(expectList.getStart(), actualList.getStart());
        assertEquals(expectList.getNextPage(), actualList.getNextPage());
        assertEquals(expectList.getCount(), actualList.getCount());

        assertNotNull(actualList.getList());
        assertEquals(expectList.getList().size(), actualList.getList().size());
        for (int i = 0; i < actualList.getList().size(); ++i) {
            assertContentInfo(expectList.getList().get(i),
                    actualList.getList().get(i));
        }
    }

    protected void assertContentInfo(
            DetailedContentInfo expect,
            DetailedContentInfo actual)
        throws Exception
    {
        assertEquals(expect.getClass(), actual.getClass());
        assertNotNull(actual.getGuid());
        assertEquals(expect.getGuid().getString(), actual.getGuid().getString());
        assertEquals(expect.getName(), actual.getName());
        assertEquals(expect.getFileType(), actual.getFileType());
        assertEquals(expect.getExifCameraDate(), actual.getExifCameraDate());
        assertEquals(expect.getModifiedDate(), actual.getModifiedDate());
        assertEquals(expect.getUploadedDate(), actual.getUploadedDate());
        assertEquals(expect.getFileDataSize(), actual.getFileDataSize());
        assertEquals(expect.isResizable(), actual.isResizable());
        assertEquals(expect.getRatio(), actual.getRatio());
        if (expect.getScore() != null) {
            assertNotNull(actual.getScore());
            assertEquals(expect.getScore(), actual.getScore());
        } else {
            assertNull(actual.getScore());
        }
        if (expect.getOrientation() != null) {
            assertNotNull(actual.getOrientation());
            assertEquals(expect.getOrientation(), actual.getOrientation());
        } else {
            assertNull(actual.getOrientation());
        }
        assertNamedTagHeadList(expect.getPersons(), actual.getPersons());
        assertNamedTagHeadList(expect.getEvents(), actual.getEvents());
        assertNamedTagHeadList(expect.getFavorites(), actual.getFavorites());
        assertNamedTagHeadList(expect.getPlaces(), actual.getPlaces());
        assertNamedTagHeadList(expect.getYearMonths(), actual.getYearMonths());
    }

    protected void assertNamedTagHeadList(
            List<NamedTagHead> expect,
            List<NamedTagHead> actual)
        throws Exception
    {
        assertNotNull(actual);
        assertEquals(expect.size(), actual.size());
        for (int i = 0; i < actual.size(); ++i) {
            assertNamedTagHead(expect.get(i), actual.get(i));
        }
    }

    protected void assertNamedTagHead(
            NamedTagHead expect,
            NamedTagHead actual)
        throws Exception
    {
        assertNotNull(actual);
        assertEquals(expect.getTagType(), actual.getTagType());
        assertNotNull(actual.getTagGUID());
        assertEquals(expect.getTagGUID().getString(), actual.getTagGUID().getString());
        assertEquals(expect.getName(), actual.getName());
    }

    @SuppressLint("NewApi")
    protected Date parse3339(String str) {
        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339(str);
        return new Date(time.normalize(false));
    }

    private GetContentIDListWithTagsLogic.Args createArgs(ProjectionType projectionType) {
        return new GetContentIDListWithTagsLogic.Args(
                mock(AuthenticationContext.class),
                projectionType,
                FileType.IMAGE,
                null,
                false,
                null,
                null,
                null,
                SortType.CREATION_DATETIME_DESC);
    }
}
