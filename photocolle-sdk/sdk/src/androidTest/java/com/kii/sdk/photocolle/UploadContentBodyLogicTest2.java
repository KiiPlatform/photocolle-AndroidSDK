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

public class UploadContentBodyLogicTest2 extends TestCaseBase {

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=14",
            id = "UCBLPR0001"
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
            (new UploadContentBodyLogic()).parseResponse(response);
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=14",
            id = "UCBLPR0002"
    )
    public void test2() throws Exception {
        // make mocks.
        byte[] data = ("{ \"result\" : \"0\", \"data_id\" :" +
                "\"01234567890123456789012345678901234567890\" }")
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
        DataID res = (new UploadContentBodyLogic()).parseResponse(response);
        assertNotNull(res);

        DataID expect = new DataID(
                "01234567890123456789012345678901234567890");
        assertDataID(expect, res);

        // verify mock access.
        verify(response, times(1)).getStatusLine();
        verify(response, times(1)).getEntity();
        verify(statusLine, times(1)).getStatusCode();
        // other mocks are accessed in EntityUtils.toString().
        // We have no responsibility to EntityUtils.toString(), so we do
        // not verify these accesses.
    }

    private void assertDataID(DataID expect, DataID actual) throws Exception {
        assertNotNull(actual);
        assertEquals(expect.getString(), actual.getString());
    }
}

