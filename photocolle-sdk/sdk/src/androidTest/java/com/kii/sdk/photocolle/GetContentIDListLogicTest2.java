package com.kii.sdk.photocolle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.Date;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kii.example.photocolle.annotation.TestInformation;

import android.annotation.SuppressLint;
import android.text.format.Time;

public class GetContentIDListLogicTest2 extends TestCaseBase {

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0001"
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
            (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0002"
    )
    public void test2() throws Exception {
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 50);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0003"
    )
    public void test3() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 0," +
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 0);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0004"
    )
    public void test4() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : 100," +
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 100);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0005"
    )
    public void test5() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
                "\"content_cnt\" : -1," +
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0006"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0007"
    )
    public void test7() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0," +
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0008"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(5, 0, 50);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0009"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 50);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0010"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0011"
    )
    public void test11() throws Exception {
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
        Exception exception = null;
        try {
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0012"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 5, 50);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0013"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 50);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0014"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0015"
    )
    public void test15() throws Exception {
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
        Exception exception = null;
        try {
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0016"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 50);
        for (int i = 0; i < 50; ++i) {
            ContentInfo info = new ContentInfo(new ContentGUID("dummy_" + i),
                    "name_" + i, FileType.IMAGE,
                    parse3339("1970-01-01T00:00:00+09:00"),
                    parse3339("1970-01-01T00:00:00+09:00"),
                    parse3339("1970-01-01T00:00:00+09:00"),
                    0, false);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0017"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 0);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0018"
    )
    public void test18() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 100; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("content_guid", "dummy_" + i);
            obj.put("content_name", "name_" + i);
            obj.put("file_type_cd", 1);
            obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
            obj.put("mdate", "1970-01-01T00:00:00+09:00");
            obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
            obj.put("file_data_size", 0);
            obj.put("resize_ng_flg", "1");
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 100);
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 100);
        for (int i = 0; i < 100; ++i) {
            ContentInfo info = new ContentInfo(new ContentGUID("dummy_" + i),
                    "name_" + i, FileType.IMAGE,
                    parse3339("1970-01-01T00:00:00+09:00"),
                    parse3339("1970-01-01T00:00:00+09:00"),
                    parse3339("1970-01-01T00:00:00+09:00"),
                    0, false);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0019"
    )
    public void test19() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 101; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("content_guid", "dummy_" + i);
            obj.put("content_name", "name_" + i);
            obj.put("file_type_cd", 1);
            obj.put("exif_camera_day", "1970-01-01T00:00:00+09:00");
            obj.put("mdate", "1970-01-01T00:00:00+09:00");
            obj.put("upload_datetime", "1970-01-01T00:00:00+09:00");
            obj.put("file_data_size", 0);
            obj.put("resize_ng_flg", "1");
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("content_list", list);
        root.put("next_page", 0);
        root.put("start", 1);
        root.put("content_cnt", 100);
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0020"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(
                new ContentGUID("0123456789012345"), "name_0",FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0021"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(
                new ContentGUID("0"), "name_0",FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0022"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(
                new ContentGUID("01234567890123456789012345678901234567890123456789"),
                "name_0",FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0023"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0024"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0025"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0026"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"),
                "012345678901234567890123456789012345678901234",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0027"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"),
                "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0028"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"),
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0029"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0030"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0031"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0032"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"), "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0033"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"), "0",
                FileType.VIDEO,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0034"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"), "0",
                FileType.SLIDE_MOVIE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0035"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0036"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0037"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0038"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"), "0",
                FileType.IMAGE,
                parse3339("2013-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0039"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0040"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"), "0",
                FileType.IMAGE,
                parse3339("2013-01-01T00:00:00+09:00"),
                parse3339("1998-06-15T13:30:25+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0041"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0042"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"), "0",
                FileType.IMAGE,
                parse3339("2013-01-01T00:00:00+09:00"),
                parse3339("1998-06-15T13:30:25+09:00"),
                parse3339("1989-01-07T12:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0043"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0044"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"), "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                1234L, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0045"
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
             (new GetContentIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0046"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"), "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, true));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0047"
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
        ListResponse<ContentInfo> responseList =
                (new GetContentIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<ContentInfo> expectList =
                new ListResponse<ContentInfo>(1, 0, 1);
        expectList.getList().add(new ContentInfo(new ContentGUID("0"), "0",
                FileType.IMAGE,
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                parse3339("1970-01-01T00:00:00+09:00"),
                0, false));

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=17",
            id = "GCILLPR0048"
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
             (new GetContentIDListLogic()).parseResponse(response);
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

    protected void assertListResponse(
            ListResponse<ContentInfo> expectList,
            ListResponse<ContentInfo> actualList)
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
            ContentInfo expect,
            ContentInfo actual)
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
    }

    @SuppressLint("NewApi")
    protected Date parse3339(String str) {
        Time time = new Time(Time.TIMEZONE_UTC);
        time.parse3339(str);
        return new Date(time.normalize(false));
    }
}
