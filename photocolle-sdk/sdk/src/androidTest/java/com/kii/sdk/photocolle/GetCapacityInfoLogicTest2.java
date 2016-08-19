package com.kii.sdk.photocolle;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.json.JSONException;

import com.kii.example.photocolle.annotation.TestInformation;

public class GetCapacityInfoLogicTest2 extends TestCaseBase {

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=11",
            id = "GCILPR0001"
    )
    public void test1() throws Exception {
        // make mocks.
        byte[] data = "{ \"result\" : \"1\" }".getBytes("UTF-8");

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
            (new GetCapacityInfoLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=11",
            id = "GCILPR0002"
    )
    public void test2() throws Exception {
        // make mocks.
        byte[] data = "{ \"result\" : \"0\", \"free_space\" : \"3221225472\" }"
                .getBytes("UTF-8");

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
        CapacityInfo info =
                (new GetCapacityInfoLogic()).parseResponse(response);
        assertNotNull(info);
        CapacityInfo expect = new CapacityInfo(-1L, 3221225472L);
        assertCapacityInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=11",
            id = "GCILPR0003"
    )
    public void test3() throws Exception {
        // make mocks.
        byte[] data = "{ \"result\" : \"0\", \"free_space\" : \"0\" }"
                .getBytes("UTF-8");

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
        CapacityInfo info =
                (new GetCapacityInfoLogic()).parseResponse(response);
        assertNotNull(info);
        CapacityInfo expect = new CapacityInfo(-1L, 0L);
        assertCapacityInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=11",
            id = "GCILPR0004"
    )
    public void test4() throws Exception {
        // make mocks.
        byte[] data = "{ \"result\" : \"0\", \"free_space\" : \"1072668082176\" }"
                .getBytes("UTF-8");

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
        CapacityInfo info =
                (new GetCapacityInfoLogic()).parseResponse(response);
        assertNotNull(info);
        CapacityInfo expect = new CapacityInfo(-1L, 1072668082176L);
        assertCapacityInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=11",
            id = "GCILPR0005"
    )
    public void test5() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : \"0\", \"free_space\" : \"50\"," +
                 " \"max_space\" : \"3221225472\" }").getBytes("UTF-8");

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
        CapacityInfo info =
                (new GetCapacityInfoLogic()).parseResponse(response);
        assertNotNull(info);
        CapacityInfo expect = new CapacityInfo(3221225472L, 50L);
        assertCapacityInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=11",
            id = "GCILPR0006"
    )
    public void test6() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : \"0\", \"free_space\" : \"50\"," +
                 " \"max_space\" : \"0\" }").getBytes("UTF-8");

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
        CapacityInfo info =
                (new GetCapacityInfoLogic()).parseResponse(response);
        assertNotNull(info);
        CapacityInfo expect = new CapacityInfo(0L, 50L);
        assertCapacityInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=11",
            id = "GCILPR0007"
    )
    public void test7() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : \"0\", \"free_space\" : \"50\"," +
                 " \"max_space\" : \"1072668082176\" }").getBytes("UTF-8");

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
        CapacityInfo info =
                (new GetCapacityInfoLogic()).parseResponse(response);
        assertNotNull(info);
        CapacityInfo expect = new CapacityInfo(1072668082176L, 50L);
        assertCapacityInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=11",
            id = "GCILPR0008"
    )
    public void test8() throws Exception {
        // make mocks.
        byte[] data = "{ \"result\" : \"0\", \"free_space\" : \"3221225472\" }"
                .getBytes("UTF-8");

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
        CapacityInfo info =
                (new GetCapacityInfoLogic()).parseResponse(response);
        assertNotNull(info);
        CapacityInfo expect = new CapacityInfo(-1L, 3221225472L);
        assertCapacityInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    private void assertCapacityInfo(
            CapacityInfo expect,
            CapacityInfo actual)
        throws Exception
    {
        assertNotNull(actual);
        assertEquals(expect.getFreeSpace(), actual.getFreeSpace());
        assertEquals(expect.getMaxSpace(), actual.getMaxSpace());
    }
}

