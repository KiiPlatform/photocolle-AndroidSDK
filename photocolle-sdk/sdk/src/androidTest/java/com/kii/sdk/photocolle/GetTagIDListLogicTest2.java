package com.kii.sdk.photocolle;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
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

@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class GetTagIDListLogicTest2 extends TestCaseBase {

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
        id = "GTILLPR0001"
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
            (new GetTagIDListLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0002"
        )
    public void test2() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"person_tag_list\" : ["
                + "{\"person_tag_guid\" : \"dummyGUID\","
                + "\"person_tag_name\" : \"dummy\","
                + "\"birth_date\" : \"2000-01-01\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        PersonTag expect = new PersonTag(
                new ContentGUID("dummyGUID"), "dummy", 1,
                new Date(t.toMillis(false)));
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0003"
        )
    public void test3() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 50; ++i) {
            Time birth = new Time(Time.TIMEZONE_UTC);
            birth.set(1, 0, 2000);
            birth.set(birth.toMillis(false) + (i * (24L * 60L * 60L * 1000L)));
            Date d = new Date(birth.toMillis(false));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject obj = new JSONObject();
            obj.put("person_tag_guid", Integer.toString(i));
            obj.put("person_tag_name", "dummy_" + i);
            obj.put("birth_date", format.format(d));
            obj.put("content_cnt", i);
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("person_tag_list", list);
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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<Tag> expectList = new ListResponse<Tag>();
        for (int i = 0; i < 50; ++i) {
            Time t = new Time(Time.TIMEZONE_UTC);
            t.set(1, 0, 2000);
            t.set(t.toMillis(false) + (i * (24L * 60L * 60L * 1000L)));
            PersonTag expect = new PersonTag(
                    new ContentGUID(Integer.toString(i)), "dummy_" + i, i,
                    new Date(t.toMillis(false)));
            expectList.getList().add(expect);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0004"
        )
    public void test4() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"event_tag_list\" : ["
                + "{\"event_tag_guid\" : \"dummyGUID\","
                + "\"event_tag_name\" : \"dummy\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        EventTag expect = new EventTag(new ContentGUID("dummyGUID"), "dummy", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0005"
        )
    public void test5() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 100; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("event_tag_guid", Integer.toString(i));
            obj.put("event_tag_name", "dummy_" + i);
            obj.put("content_cnt", i);
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("event_tag_list", list);
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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<Tag> expectList = new ListResponse<Tag>();
        for (int i = 0; i < 100; ++i) {
            EventTag expect = new EventTag(
                    new ContentGUID(Integer.toString(i)), "dummy_" + i, i);
            expectList.getList().add(expect);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0006"
        )
    public void test6() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"optional_tag_list\" : ["
                + "{\"optional_tag_guid\" : \"dummyGUID\","
                + "\"optional_tag_name\" : \"dummy\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        FavoriteTag expect = new FavoriteTag(new ContentGUID("dummyGUID"),
                "dummy", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0007"
        )
    public void test7() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 100; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("optional_tag_guid", Integer.toString(i));
            obj.put("optional_tag_name", "dummy_" + i);
            obj.put("content_cnt", i);
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("optional_tag_list", list);
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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<Tag> expectList = new ListResponse<Tag>();
        for (int i = 0; i < 100; ++i) {
            FavoriteTag expect = new FavoriteTag(
                    new ContentGUID(Integer.toString(i)), "dummy_" + i, i);
            expectList.getList().add(expect);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0008"
        )
    public void test8() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"place_info_list\" : ["
                + "{\"place_tag_guid\" : \"dummyGUID\","
                + "\"place_name\" : \"dummy\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        PlacementTag expect = new PlacementTag(new ContentGUID("dummyGUID"),
                "dummy", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0009"
        )
    public void test9() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 50; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("place_tag_guid", Integer.toString(i));
            obj.put("place_name", "dummy_" + i);
            obj.put("content_cnt", i);
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("place_info_list", list);
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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<Tag> expectList = new ListResponse<Tag>();
        for (int i = 0; i < 50; ++i) {
            PlacementTag expect = new PlacementTag(
                    new ContentGUID(Integer.toString(i)), "dummy_" + i, i);
            expectList.getList().add(expect);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0010"
        )
    public void test10() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"month_info_list\" : ["
                + "{\"month_tag_guid\" : \"dummyGUID\","
                + "\"month_tag_name\" : \"dummy\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        YearMonthTag expect = new YearMonthTag(new ContentGUID("dummyGUID"),
                "dummy", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0011"
        )
    public void test11() throws Exception {
        // make mocks.
        JSONArray list = new JSONArray();
        for (int i = 0; i < 50; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("month_tag_guid", Integer.toString(i));
            obj.put("month_tag_name", "dummy_" + i);
            obj.put("content_cnt", i);
            list.put(obj);
        }
        JSONObject root = new JSONObject();
        root.put("month_info_list", list);
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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<Tag> expectList = new ListResponse<Tag>();
        for (int i = 0; i < 50; ++i) {
            YearMonthTag expect = new YearMonthTag(
                    new ContentGUID(Integer.toString(i)), "dummy_" + i, i);
            expectList.getList().add(expect);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0012"
        )
    public void test12() throws Exception {
        // make mocks.
        JSONObject root = new JSONObject();
        JSONArray list = new JSONArray();
        for (int i = 0; i < 50; ++i) {
            Time birth = new Time(Time.TIMEZONE_UTC);
            birth.set(1, 0, 2000);
            birth.set(birth.toMillis(false) + (i * (24L * 60L * 60L * 1000L)));
            Date d = new Date(birth.toMillis(false));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject obj = new JSONObject();
            obj.put("person_tag_guid", Integer.toString(i));
            obj.put("person_tag_name", "dummy_" + i);
            obj.put("birth_date", format.format(d));
            obj.put("content_cnt", i);
            list.put(obj);
        }
        root.put("person_tag_list", list);
        list = new JSONArray();
        for (int i = 50; i < 150; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("event_tag_guid", Integer.toString(i));
            obj.put("event_tag_name", "dummy_" + i);
            obj.put("content_cnt", i);
            list.put(obj);
        }
        root.put("event_tag_list", list);
        list = new JSONArray();
        for (int i = 150; i < 250; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("optional_tag_guid", Integer.toString(i));
            obj.put("optional_tag_name", "dummy_" + i);
            obj.put("content_cnt", i);
            list.put(obj);
        }
        root.put("optional_tag_list", list);
        list = new JSONArray();
        for (int i = 250; i < 300; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("place_tag_guid", Integer.toString(i));
            obj.put("place_name", "dummy_" + i);
            obj.put("content_cnt", i);
            list.put(obj);
        }
        root.put("place_info_list", list);
        list = new JSONArray();
        for (int i = 300; i < 350; ++i) {
            JSONObject obj = new JSONObject();
            obj.put("month_tag_guid", Integer.toString(i));
            obj.put("month_tag_name", "dummy_" + i);
            obj.put("content_cnt", i);
            list.put(obj);
        }
        root.put("month_info_list", list);
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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        ListResponse<Tag> expectList = new ListResponse<Tag>();
        for (int i = 0; i < 50; ++i) {
            Time t = new Time(Time.TIMEZONE_UTC);
            t.set(1, 0, 2000);
            t.set(t.toMillis(false) + (i * (24L * 60L * 60L * 1000L)));
            PersonTag expect = new PersonTag(
                    new ContentGUID(Integer.toString(i)), "dummy_" + i, i,
                    new Date(t.toMillis(false)));
            expectList.getList().add(expect);
        }
        for (int i = 50; i < 150; ++i) {
            EventTag expect = new EventTag(
                    new ContentGUID(Integer.toString(i)), "dummy_" + i, i);
            expectList.getList().add(expect);
        }
        for (int i = 150; i < 250; ++i) {
            FavoriteTag expect = new FavoriteTag(
                    new ContentGUID(Integer.toString(i)), "dummy_" + i, i);
            expectList.getList().add(expect);
        }
        for (int i = 250; i < 300; ++i) {
            PlacementTag expect = new PlacementTag(
                    new ContentGUID(Integer.toString(i)), "dummy_" + i, i);
            expectList.getList().add(expect);
        }
        for (int i = 300; i < 350; ++i) {
            YearMonthTag expect = new YearMonthTag(
                    new ContentGUID(Integer.toString(i)), "dummy_" + i, i);
            expectList.getList().add(expect);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0013"
        )
    public void test13() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"person_tag_list\" : ["
                + "{\"person_tag_guid\" : \"01234567890123456789012345678901234567890123456\","
                + "\"person_tag_name\" : \"dummy\","
                + "\"birth_date\" : \"2000-01-01\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        PersonTag expect = new PersonTag(
                new ContentGUID("01234567890123456789012345678901234567890123456"),
                "dummy", 1, new Date(t.toMillis(false)));
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0014"
        )
    public void test14() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"person_tag_list\" : ["
                + "{\"person_tag_guid\" : \"dummyGUID\","
                + "\"person_tag_name\" : \"01234567890123456789\","
                + "\"birth_date\" : \"2000-01-01\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        PersonTag expect = new PersonTag(
                new ContentGUID("dummyGUID"), "01234567890123456789", 1,
                new Date(t.toMillis(false)));
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0015"
        )
    public void test15() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"event_tag_list\" : ["
                + "{\"event_tag_guid\" : \"01234567890123456789012345678901234567890123456\","
                + "\"event_tag_name\" : \"dummy\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        EventTag expect = new EventTag(
                new ContentGUID("01234567890123456789012345678901234567890123456"),
                "dummy", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0016"
        )
    public void test16() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"event_tag_list\" : ["
                + "{\"event_tag_guid\" : \"dummyGUID\","
                + "\"event_tag_name\" : \"01234567890123456789\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        EventTag expect = new EventTag(new ContentGUID("dummyGUID"),
                "01234567890123456789", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR00017"
        )
    public void test17() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"optional_tag_list\" : ["
                + "{\"optional_tag_guid\" : \"01234567890123456789012345678901234567890123456\","
                + "\"optional_tag_name\" : \"dummy\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        FavoriteTag expect = new FavoriteTag(
                new ContentGUID("01234567890123456789012345678901234567890123456"),
                "dummy", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0018"
        )
    public void test18() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"optional_tag_list\" : ["
                + "{\"optional_tag_guid\" : \"dummyGUID\","
                + "\"optional_tag_name\" : \"01234567890123456789\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        FavoriteTag expect = new FavoriteTag(new ContentGUID("dummyGUID"),
                "01234567890123456789", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0019"
        )
    public void test19() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"place_info_list\" : ["
                + "{\"place_tag_guid\" : \"01234567890123456789012345678901234567890123456\","
                + "\"place_name\" : \"dummy\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        PlacementTag expect = new PlacementTag(
                new ContentGUID("01234567890123456789012345678901234567890123456"),
                "dummy", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0020"
        )
    public void test20() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"place_info_list\" : ["
                + "{\"place_tag_guid\" : \"dummyGUID\","
                + "\"place_name\" : \"01234567890123456789\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        PlacementTag expect = new PlacementTag(new ContentGUID("dummyGUID"),
                "01234567890123456789", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0021"
        )
    public void test21() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"month_info_list\" : ["
                + "{\"month_tag_guid\" : \"01234567890123456789012345678901234567890123456\","
                + "\"month_tag_name\" : \"dummy\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        YearMonthTag expect = new YearMonthTag(
                new ContentGUID("01234567890123456789012345678901234567890123456"),
                "dummy", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0022"
        )
    public void test22() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"month_info_list\" : ["
                + "{\"month_tag_guid\" : \"dummyGUID\","
                + "\"month_tag_name\" : \"01234567890123456789\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        Time t = new Time(Time.TIMEZONE_UTC);
        t.set(1, 0, 2000);
        YearMonthTag expect = new YearMonthTag(new ContentGUID("dummyGUID"),
                "01234567890123456789", 1);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=13",
            id = "GTILLPR0023"
        )
    public void test23() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : 0, \"person_tag_list\" : ["
                + "{\"person_tag_guid\" : \"dummyGUID\","
                + "\"person_tag_name\" : \"dummy\","
                + "\"content_cnt\" : 1"
                + "}]}").getBytes("UTF-8");

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
        ListResponse<Tag> responseList =
                (new GetTagIDListLogic()).parseResponse(response);
        assertNotNull(responseList);

        PersonTag expect = new PersonTag(
                new ContentGUID("dummyGUID"), "dummy", 1,
                null);
        ListResponse<Tag> expectList = new ListResponse<Tag>();
        expectList.getList().add(expect);

        assertListResponse(expectList, responseList);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    protected void assertListResponse(
            ListResponse<Tag> expectList,
            ListResponse<Tag> actualList)
        throws Exception
    {
        assertNotNull(actualList);
        assertNotNull(actualList.getList());
        assertEquals(expectList.getList().size(), actualList.getList().size());
        for (int i = 0; i < actualList.getList().size(); ++i) {
            Tag expect = expectList.getList().get(i);
            Tag actual = actualList.getList().get(i);
            assertEquals(expect.getClass(), actual.getClass());
            if (expect.getClass() == PersonTag.class) {
                assertPersonTag((PersonTag)expect, (PersonTag)actual);
            } else {
                assertTag(expect, actual);
            }
        }
    }

    private void assertPersonTag(PersonTag expect, PersonTag actual) {
        assertEquals(expect.getTagType(), actual.getTagType());
        assertEquals(expect.getTagGUID().getString(),
                actual.getTagGUID().getString());
        assertEquals(expect.getName(), actual.getName());
        assertEquals(expect.getBirthDate(), actual.getBirthDate());
        assertEquals(expect.getContentsCount(), actual.getContentsCount());
    }

    private void assertTag(Tag expect, Tag actual) {
        assertEquals(expect.getTagType(), actual.getTagType());
        assertEquals(expect.getTagGUID().getString(),
                actual.getTagGUID().getString());
        assertEquals(expect.getName(), actual.getName());
        assertEquals(expect.getContentsCount(), actual.getContentsCount());
    }
}

