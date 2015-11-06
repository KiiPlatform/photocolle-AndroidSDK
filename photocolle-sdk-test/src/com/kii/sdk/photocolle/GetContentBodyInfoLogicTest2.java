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

import android.test.AndroidTestCase;

public class GetContentBodyInfoLogicTest2 extends AndroidTestCase {

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=15",
            id = "GCBILPR0001"
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
        when(header.getValue()).thenReturn("application/json");
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
            (new GetContentBodyInfoLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=15",
            id = "GCBILPR0002"
    )
    public void test2() throws Exception {
        // make mocks.
        byte[] data = "dummy".getBytes("UTF-8");

        Header header = mock(Header.class);
        when(header.getValue()).thenReturn("image/jpeg");
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
        ContentBodyInfo info =
                (new GetContentBodyInfoLogic()).parseResponse(response);
        assertNotNull(info);

        ContentBodyInfo expect = new ContentBodyInfo(MimeType.JPEG,
                new ByteArrayInputStream("dummy".getBytes()));

        assertContentBodyInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=15",
            id = "GCBILPR0003"
    )
    public void test3() throws Exception {
        // make mocks.
        byte[] data = "dummy".getBytes("UTF-8");

        Header header = mock(Header.class);
        when(header.getValue()).thenReturn("image/pjpeg");
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
        ContentBodyInfo info =
                (new GetContentBodyInfoLogic()).parseResponse(response);
        assertNotNull(info);

        ContentBodyInfo expect = new ContentBodyInfo(MimeType.PJPEG,
                new ByteArrayInputStream("dummy".getBytes()));

        assertContentBodyInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=15",
            id = "GCBILPR0004"
    )
    public void test4() throws Exception {
        // make mocks.
        byte[] data = "dummy".getBytes("UTF-8");

        Header header = mock(Header.class);
        when(header.getValue()).thenReturn("video/3gpp");
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
        ContentBodyInfo info =
                (new GetContentBodyInfoLogic()).parseResponse(response);
        assertNotNull(info);

        ContentBodyInfo expect = new ContentBodyInfo(MimeType.THREE_GP,
                new ByteArrayInputStream("dummy".getBytes()));

        assertContentBodyInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=15",
            id = "GCBILPR0005"
    )
    public void test5() throws Exception {
        // make mocks.
        byte[] data = "dummy".getBytes("UTF-8");

        Header header = mock(Header.class);
        when(header.getValue()).thenReturn("video/avi");
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
        ContentBodyInfo info =
                (new GetContentBodyInfoLogic()).parseResponse(response);
        assertNotNull(info);

        ContentBodyInfo expect = new ContentBodyInfo(MimeType.AVI,
                new ByteArrayInputStream("dummy".getBytes()));

        assertContentBodyInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=15",
            id = "GCBILPR0006"
    )
    public void test6() throws Exception {
        // make mocks.
        byte[] data = "dummy".getBytes("UTF-8");

        Header header = mock(Header.class);
        when(header.getValue()).thenReturn("video/quicktime");
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
        ContentBodyInfo info =
                (new GetContentBodyInfoLogic()).parseResponse(response);
        assertNotNull(info);

        ContentBodyInfo expect = new ContentBodyInfo(MimeType.QUICKTIME,
                new ByteArrayInputStream("dummy".getBytes()));

        assertContentBodyInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=15",
            id = "GCBILPR0007"
    )
    public void test7() throws Exception {
        // make mocks.
        byte[] data = "dummy".getBytes("UTF-8");

        Header header = mock(Header.class);
        when(header.getValue()).thenReturn("video/mp4");
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
        ContentBodyInfo info =
                (new GetContentBodyInfoLogic()).parseResponse(response);
        assertNotNull(info);

        ContentBodyInfo expect = new ContentBodyInfo(MimeType.MP4,
                new ByteArrayInputStream("dummy".getBytes()));

        assertContentBodyInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=15",
            id = "GCBILPR0008"
    )
    public void test8() throws Exception {
        // make mocks.
        byte[] data = "dummy".getBytes("UTF-8");

        Header header = mock(Header.class);
        when(header.getValue()).thenReturn("video/vnd.mts");
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
        ContentBodyInfo info =
                (new GetContentBodyInfoLogic()).parseResponse(response);
        assertNotNull(info);

        ContentBodyInfo expect = new ContentBodyInfo(MimeType.VND_MTS,
                new ByteArrayInputStream("dummy".getBytes()));

        assertContentBodyInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=15",
            id = "GCBILPR0009"
    )
    public void test9() throws Exception {
        // make mocks.
        byte[] data = "dummy".getBytes("UTF-8");

        Header header = mock(Header.class);
        when(header.getValue()).thenReturn("video/mpeg");
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
        ContentBodyInfo info =
                (new GetContentBodyInfoLogic()).parseResponse(response);
        assertNotNull(info);

        ContentBodyInfo expect = new ContentBodyInfo(MimeType.MPEG,
                new ByteArrayInputStream("dummy".getBytes()));

        assertContentBodyInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=15",
            id = "GCBILPR0010"
    )
    public void test10() throws Exception {
        // make mocks.
        byte[] data = "012345678901234567890123456789".getBytes("UTF-8");

        Header header = mock(Header.class);
        when(header.getValue()).thenReturn("image/jpeg");
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
        ContentBodyInfo info =
                (new GetContentBodyInfoLogic()).parseResponse(response);
        assertNotNull(info);

        ContentBodyInfo expect = new ContentBodyInfo(MimeType.JPEG,
                new ByteArrayInputStream(data));

        assertContentBodyInfo(expect, info);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    private void assertContentBodyInfo(
            ContentBodyInfo expect,
            ContentBodyInfo actual)
        throws Exception
    {
        assertNotNull(actual);
        assertEquals(expect.getContentType(), actual.getContentType());
        byte[] expectHash = TestUtils.getHashFromInputStream(
                expect.getInputStream());
        byte[] actualHash = TestUtils.getHashFromInputStream(
                actual.getInputStream());
        assertEquals(TestUtils.toHexString(expectHash),
                TestUtils.toHexString(actualHash));
    }
}

