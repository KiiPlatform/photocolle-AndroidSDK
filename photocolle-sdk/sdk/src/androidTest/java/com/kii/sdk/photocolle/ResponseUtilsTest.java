// FIXME: consider a position of this file.

package com.kii.sdk.photocolle;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.json.JSONException;
import org.json.JSONObject;

import com.kii.sdk.photocolle.BaseApplicationLayerException.ErrorCode;
import com.kii.sdk.photocolle.UploadException.ErrorItem;

public class ResponseUtilsTest extends TestCaseBase {

    public void testThrowHttpStatusRelatedException1() throws Exception {
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(401);
        when(statusLine.getReasonPhrase()).thenReturn(null);
        HttpEntity entity = mock(HttpEntity.class);
        byte[] body = "Unauthorized".getBytes("UTF-8");
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(body));
        when(entity.getContentLength()).thenReturn((long)body.length);
        Header header = mock(Header.class);
        when(header.getName()).thenReturn("WWW-Authenticate");
        when(header.getValue()).thenReturn("Bearer error=invalid_token");
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(response.getHeaders("WWW-Authenticate")).thenReturn(
            new Header[] {header});

        Exception exception = null;
        try {
            ResponseUtil.throwHttpStatusRelatedException(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(InvalidTokenException.class, exception.getClass());
    }

    public void testThrowHttpStatusRelatedException2() throws Exception {
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(400);
        when(statusLine.getReasonPhrase()).thenReturn("Phrase");
        HttpEntity entity = mock(HttpEntity.class);
        byte[] body = "Bad Request".getBytes("UTF-8");
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(body));
        when(entity.getContentLength()).thenReturn((long)body.length);
        Header header = mock(Header.class);
        when(header.getName()).thenReturn("WWW-Authenticate");
        when(header.getValue()).thenReturn("Bearer error=invalid_request");
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(response.getHeaders("WWW-Authenticate")).thenReturn(
            new Header[] {header});

        Exception exception = null;
        try {
            ResponseUtil.throwHttpStatusRelatedException(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(HttpException.class, exception.getClass());
        HttpException httpException = (HttpException)exception;
        assertEquals(httpException.getStatusCode(), 400);
        assertEquals(httpException.getReasonPhrase(), "Phrase");
        assertEquals(httpException.getResponseBody(), "Bad Request");
    }

    public void testThrowHttpStatusRelatedException3() throws Exception {
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(401);
        when(statusLine.getReasonPhrase()).thenReturn("Phrase");
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContent()).thenReturn(null);
        when(entity.getContentLength()).thenReturn((long)0);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(response.getHeaders("WWW-Authenticate")).thenReturn(null);

        Exception exception = null;
        try {
            ResponseUtil.throwHttpStatusRelatedException(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(HttpException.class, exception.getClass());
        HttpException httpException = (HttpException)exception;
        assertEquals(httpException.getStatusCode(), 401);
        assertEquals(httpException.getReasonPhrase(), "Phrase");
        assertEquals(httpException.getResponseBody(), "");
    }

    public void testThrowHttpStatusRelatedException4() throws Exception {
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(401);
        when(statusLine.getReasonPhrase()).thenReturn("Phrase");
        HttpEntity entity = mock(HttpEntity.class);
        byte[] body = "body".getBytes("UTF-8");
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(body));
        when(entity.getContentLength()).thenReturn((long)body.length);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(response.getHeaders("WWW-Authenticate")).thenReturn(null);

        Exception exception = null;
        try {
            ResponseUtil.throwHttpStatusRelatedException(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(HttpException.class, exception.getClass());
        HttpException httpException = (HttpException)exception;
        assertEquals(httpException.getStatusCode(), 401);
        assertEquals(httpException.getReasonPhrase(), "Phrase");
        assertEquals(httpException.getResponseBody(), "body");
    }

    public void testThrowHttpStatusRelatedException5() throws Exception {
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(900);
        when(statusLine.getReasonPhrase()).thenReturn("Phrase");
        HttpEntity entity = mock(HttpEntity.class);
        byte[] body = "Unauthorized".getBytes("UTF-8");
        when(entity.getContent()).thenReturn(new ByteArrayInputStream(body));
        when(entity.getContentLength()).thenReturn((long)body.length);
        Header header = mock(Header.class);
        when(header.getName()).thenReturn("WWW-Authenticate");
        when(header.getValue()).thenReturn("Bearer error=invalid_token");
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        when(response.getHeaders("WWW-Authenticate")).thenReturn(
            new Header[] {header});

        Exception exception = null;
        try {
            ResponseUtil.throwHttpStatusRelatedException(response);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(HttpException.class, exception.getClass());
        HttpException httpException = (HttpException)exception;
        assertEquals(httpException.getStatusCode(), 900);
        assertEquals(httpException.getReasonPhrase(), "Phrase");
        assertEquals(httpException.getResponseBody(), "Unauthorized");
    }

    public void testNewApplicationLayerExceptionSuccess() throws Exception {
        JSONObject json = ResponseUtil.newJSONObject("{ \"err_cd\" : 100 }");
        Exception exception = null;
        try {
            throw ResponseUtil.newApplicationLayerException(json);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ApplicationLayerException.class, exception.getClass());
        ApplicationLayerException appException =
                (ApplicationLayerException)exception;
        assertEquals(BaseApplicationLayerException.ErrorCode.PARAMETER_ERROR,
                appException.getErrorCode());
    }

    public void testNewApplicationLayerExceptionFail() throws Exception {
        JSONObject json = ResponseUtil.newJSONObject("{ \"err_cd\" : 0 }");
        Exception exception = null;
        try {
            throw ResponseUtil.newApplicationLayerException(json);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        ParameterException paramException = (ParameterException)exception;
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                paramException.getReason());
    }

    public void testNewUploadExceptionSuccess1() throws Exception {
        JSONObject json = ResponseUtil.newJSONObject(
                "{ \"err_list\" : [ { \"err_cd\" : \"1101\", \"err_item\" : \"title\" } ] }");
        Exception exception = null;
        try {
            throw ResponseUtil.newUploadException(json);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(UploadException.class, exception.getClass());
        UploadException uploadException = (UploadException)exception;
        List<ErrorItem> errItems = uploadException.getErrorItems();
        assertNotNull(errItems);
        assertEquals(1, errItems.size());
        ErrorItem item = errItems.get(0);
        assertNotNull(item);
        assertEquals(ErrorCode.CONTENTS_DUPLICATED, item.getErrorCode());
        assertEquals("title", item.getItem());
    }

    public void testNewUploadExceptionSuccess2() throws Exception {
        JSONObject json = ResponseUtil.newJSONObject(
                "{ \"err_list\" : [ { \"err_cd\" : \"1101\", \"err_item\" : \"" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234" +
                "\" } ] }");
        Exception exception = null;
        try {
            throw ResponseUtil.newUploadException(json);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(UploadException.class, exception.getClass());
        UploadException uploadException = (UploadException)exception;
        List<ErrorItem> errItems = uploadException.getErrorItems();
        assertNotNull(errItems);
        assertEquals(1, errItems.size());
        ErrorItem item = errItems.get(0);
        assertNotNull(item);
        assertEquals(ErrorCode.CONTENTS_DUPLICATED, item.getErrorCode());
        assertEquals(
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234", item.getItem());
    }

    public void testNewUploadExceptionFail1() throws Exception {
        JSONObject json = ResponseUtil.newJSONObject(
                "{ \"err_list\" : [ { \"err_cd\" : \"0\", \"err_item\" : \"title\" } ] }");
        Exception exception = null;
        try {
            throw ResponseUtil.newUploadException(json);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        ParameterException paramException = (ParameterException)exception;
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                paramException.getReason());
    }

    public void testNewUploadExceptionFail2() throws Exception {
        JSONObject json = ResponseUtil.newJSONObject(
                "{ \"err_list\" : [ { \"err_cd\" : \"1101\" } ] }");
        Exception exception = null;
        try {
            throw ResponseUtil.newUploadException(json);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ResponseBodyParseException.class, exception.getClass());
        ResponseBodyParseException responseException =
                (ResponseBodyParseException)exception;
        assertEquals(JSONException.class,
                responseException.getCause().getClass());
    }

}
