package com.kii.sdk.photocolle;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

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
import android.util.Base64;

@SuppressLint("NewApi")
public class GetContentThumbnailInfoLogicTest2 extends TestCaseBase {

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
        id = "GCTILPR0001"
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
            (new GetContentThumbnailInfoLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0002"
        )
    public void test2() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"dummyGUID\", \"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                +"\"}]}").getBytes("UTF-8");

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
        ContentThumbnailInfoList responseInfo =
                (new GetContentThumbnailInfoLogic()).parseResponse(response);
        assertNotNull(responseInfo);

        ContentThumbnailInfo expect = new ContentThumbnailInfo(
                new ContentGUID("dummyGUID"), MimeType.JPEG,
                "dummy".getBytes());
        ContentThumbnailInfoList expectList = new ContentThumbnailInfoList();
        expectList.getList().add(expect);

        assertContentThumbnailInfoList(expectList, responseInfo);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0003"
        )
    public void test3() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 100; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("content_guid", Integer.toString(i));
            obj.put("mime_type", "image/jpeg");
            obj.put("thumbnail", Base64.encodeToString("dummy".getBytes(),
                    Base64.DEFAULT));
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("content_info_list", list);
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
        ContentThumbnailInfoList responseInfo =
                (new GetContentThumbnailInfoLogic()).parseResponse(response);
        assertNotNull(responseInfo);

        ContentThumbnailInfoList expectList = new ContentThumbnailInfoList();
        for (int i =0; i < 100; ++i) {
            ContentThumbnailInfo expect = new ContentThumbnailInfo(
                    new ContentGUID(Integer.toString(i)), MimeType.JPEG,
                    "dummy".getBytes());
            expectList.getList().add(expect);
        }

        assertContentThumbnailInfoList(expectList, responseInfo);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0004"
        )
    public void test4() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"01234567890123456789012345678901234567890123456789\","
                + "\"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                +"\"}]}").getBytes("UTF-8");

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
        ContentThumbnailInfoList responseInfo =
                (new GetContentThumbnailInfoLogic()).parseResponse(response);
        assertNotNull(responseInfo);

        ContentThumbnailInfo expect = new ContentThumbnailInfo(
                new ContentGUID("01234567890123456789012345678901234567890123456789"),
                MimeType.JPEG, "dummy".getBytes());
        ContentThumbnailInfoList expectList = new ContentThumbnailInfoList();
        expectList.getList().add(expect);

        assertContentThumbnailInfoList(expectList, responseInfo);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0005"
        )
    public void test5() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        JSONObject root = new JSONObject();
        root.put("content_info_list", list);
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
            (new GetContentThumbnailInfoLogic()).parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0006"
        )
    public void test6() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 101; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("content_guid", Integer.toString(i));
            obj.put("mime_type", "image/jpeg");
            obj.put("thumbnail", Base64.encodeToString("dummy".getBytes(),
                    Base64.DEFAULT));
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("content_info_list", list);
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
            (new GetContentThumbnailInfoLogic()).parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0007"
        )
    public void test7() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"dummyGUID\", \"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                +"\"} ], \"ng_list\" : [ ] }").getBytes("UTF-8");

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
        ContentThumbnailInfoList responseInfo =
                (new GetContentThumbnailInfoLogic()).parseResponse(response);
        assertNotNull(responseInfo);

        ContentThumbnailInfo expect = new ContentThumbnailInfo(
                new ContentGUID("dummyGUID"), MimeType.JPEG,
                "dummy".getBytes());
        ContentThumbnailInfoList expectList = new ContentThumbnailInfoList();
        expectList.getList().add(expect);

        assertContentThumbnailInfoList(expectList, responseInfo);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0008"
        )
    public void test8() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"dummyGUID\", \"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                + "\"}], "
                + "\"ng_list\" : [ \"ng1\" ]"
                + "}").getBytes("UTF-8");

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
        ContentThumbnailInfoList responseInfo =
                (new GetContentThumbnailInfoLogic()).parseResponse(response);
        assertNotNull(responseInfo);

        ContentThumbnailInfo expect = new ContentThumbnailInfo(
                new ContentGUID("dummyGUID"), MimeType.JPEG,
                "dummy".getBytes());
        ContentThumbnailInfoList expectList = new ContentThumbnailInfoList();
        expectList.getList().add(expect);
        expectList.getNGList().add(new ContentGUID("ng1"));

        assertContentThumbnailInfoList(expectList, responseInfo);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0009"
        )
    public void test9() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "dummyGUID");
        obj.put("mime_type", "image/jpeg");
        obj.put("thumbnail", Base64.encodeToString("dummy".getBytes(),
                Base64.DEFAULT));
        list.put(obj);
        JSONArray ngList = new JSONArray();
        for (int i = 0; i < 99; ++i) {
            ngList.put("ng" + Integer.toString(i));
        }
        JSONObject root = new JSONObject();
        root.put("content_info_list", list);
        root.put("ng_list", ngList);
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
        ContentThumbnailInfoList responseInfo =
                (new GetContentThumbnailInfoLogic()).parseResponse(response);
        assertNotNull(responseInfo);

        ContentThumbnailInfo expect = new ContentThumbnailInfo(
                new ContentGUID("dummyGUID"), MimeType.JPEG,
                "dummy".getBytes());
        ContentThumbnailInfoList expectList = new ContentThumbnailInfoList();
        expectList.getList().add(expect);
        for (int i = 0; i < 99; ++i) {
            expectList.getNGList().add(new ContentGUID("ng" + Integer.toString(i)));
        }

        assertContentThumbnailInfoList(expectList, responseInfo);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0010"
        )
    public void test10() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("content_guid", "dummyGUID");
        obj.put("mime_type", "image/jpeg");
        obj.put("thumbnail", Base64.encodeToString("dummy".getBytes(),
                Base64.DEFAULT));
        list.put(obj);
        JSONArray ngList = new JSONArray();
        for (int i = 0; i < 100; ++i) {
            ngList.put("ng" + Integer.toString(i));
        }
        JSONObject root = new JSONObject();
        root.put("content_info_list", list);
        root.put("ng_list", ngList);
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
            (new GetContentThumbnailInfoLogic()).parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0011"
        )
    public void test11() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"dummyGUID\", \"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                + "\"}], "
                + "\"ng_list\" : [ \"01234567890123456789012345678901234567890123456789\" ]"
                + "}").getBytes("UTF-8");

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
        ContentThumbnailInfoList responseInfo =
                (new GetContentThumbnailInfoLogic()).parseResponse(response);
        assertNotNull(responseInfo);

        ContentThumbnailInfo expect = new ContentThumbnailInfo(
                new ContentGUID("dummyGUID"), MimeType.JPEG,
                "dummy".getBytes());
        ContentThumbnailInfoList expectList = new ContentThumbnailInfoList();
        expectList.getList().add(expect);
        expectList.getNGList().add(new ContentGUID(
                "01234567890123456789012345678901234567890123456789"));

        assertContentThumbnailInfoList(expectList, responseInfo);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0012"
        )
    public void test12() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"0\","
                + "\"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                +"\"}]}").getBytes("UTF-8");

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
        ContentThumbnailInfoList responseInfo =
                (new GetContentThumbnailInfoLogic()).parseResponse(response);
        assertNotNull(responseInfo);

        ContentThumbnailInfo expect = new ContentThumbnailInfo(
                new ContentGUID("0"),
                MimeType.JPEG, "dummy".getBytes());
        ContentThumbnailInfoList expectList = new ContentThumbnailInfoList();
        expectList.getList().add(expect);

        assertContentThumbnailInfoList(expectList, responseInfo);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0013"
        )
    public void test13() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"\","
                + "\"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                +"\"}]}").getBytes("UTF-8");

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
            (new GetContentThumbnailInfoLogic()).parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0014"
        )
    public void test14() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"012345678901234567890123456789012345678901234567890\","
                + "\"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                +"\"}]}").getBytes("UTF-8");

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
            (new GetContentThumbnailInfoLogic()).parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0015"
        )
    public void test15() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"dummyGUID\", \"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                + "\"}], "
                + "\"ng_list\" : [ \"0\" ]"
                + "}").getBytes("UTF-8");

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
        ContentThumbnailInfoList responseInfo =
                (new GetContentThumbnailInfoLogic()).parseResponse(response);
        assertNotNull(responseInfo);

        ContentThumbnailInfo expect = new ContentThumbnailInfo(
                new ContentGUID("dummyGUID"), MimeType.JPEG,
                "dummy".getBytes());
        ContentThumbnailInfoList expectList = new ContentThumbnailInfoList();
        expectList.getList().add(expect);
        expectList.getNGList().add(new ContentGUID("0"));

        assertContentThumbnailInfoList(expectList, responseInfo);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0016"
        )
    public void test16() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"dummyGUID\", \"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                + "\"}], "
                + "\"ng_list\" : [ \"\" ]"
                + "}").getBytes("UTF-8");

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
            (new GetContentThumbnailInfoLogic()).parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
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

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=12",
            id = "GCTILPR0017"
        )
    public void test17() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"content_info_list\" : ["
                + "{\"content_guid\" : \"dummyGUID\", \"mime_type\" : \"image/jpeg\","
                + "\"thumbnail\" : \""
                + Base64.encodeToString("dummy".getBytes(), Base64.DEFAULT)
                + "\"}], "
                + "\"ng_list\" : [ \"012345678901234567890123456789012345678901234567890\" ]"
                + "}").getBytes("UTF-8");

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
            (new GetContentThumbnailInfoLogic()).parseResponse(response);
        } catch (Exception e) {
            exception = e;
        }
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

    public void assertContentThumbnailInfoList(
            ContentThumbnailInfoList expectList,
            ContentThumbnailInfoList actualList)
        throws Exception
    {
        assertNotNull(actualList);
        assertNotNull(actualList.getList());
        assertEquals(expectList.getList().size(), actualList.getList().size());
        for (int i = 0; i < actualList.getList().size(); ++i) {
            ContentThumbnailInfo expect = expectList.getList().get(i);
            ContentThumbnailInfo actual = actualList.getList().get(i);
            assertEquals(expect.getGUID().getString(),
                    actual.getGUID().getString());
            assertEquals(expect.getMimeType(), actual.getMimeType());
            assertTrue(Arrays.equals(expect.getThumbnailBytes(),
                    actual.getThumbnailBytes()));
        }
        assertNotNull(actualList.getNGList());
        assertEquals(expectList.getNGList().size(), actualList.getNGList().size());
        for (int i = 0; i < actualList.getNGList().size(); ++i) {
            ContentGUID expect = expectList.getNGList().get(i);
            ContentGUID actual = actualList.getNGList().get(i);
            assertEquals(expect.getString(), actual.getString());
        }
    }
}

