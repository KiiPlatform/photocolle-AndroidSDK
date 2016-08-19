package com.kii.sdk.photocolle;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.ParameterParser;
import org.apache.http.client.methods.HttpPost;

import com.kii.example.photocolle.annotation.TestInformation;
import com.kii.example.photocolle.multipart.FileField;
import com.kii.example.photocolle.multipart.MultiPartFormData;
import com.kii.example.photocolle.multipart.Field;

import com.kii.sdk.photocolle.http.entity.mime.MultipartEntity;

public class UploadContentBodyLogicTest extends TestCaseBase {

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0001"
    )
    public void test1() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "dummy.jpg",
                data.length,
                MimeType.JPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "image/jpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0002"
    )
    public void test2() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.MPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/mpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0003"
    )
    public void test3() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.SLIDE_MOVIE,
                            "dummy.jpg",
                            data.length,
                            MimeType.JPEG,
                            new ByteArrayInputStream(data)));
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
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0004"
    )
    public void test4() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            null,
                            "dummy.jpg",
                            data.length,
                            MimeType.JPEG,
                            new ByteArrayInputStream(data)));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0005"
    )
    public void test5() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "dummy.jpg",
                data.length,
                MimeType.JPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "image/jpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0006"
    )
    public void test6() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "j",
                data.length,
                MimeType.JPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "j"),
                new Field("size", "5"),
                new Field("mime_type", "image/jpeg"),
                new FileField("j", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0007"
    )
    public void test7() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        String fileName =
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "01234567890.jpg";
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                fileName,
                data.length,
                MimeType.JPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", fileName),
                new Field("size", "5"),
                new Field("mime_type", "image/jpeg"),
                new FileField(fileName, TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0008"
    )
    public void test8() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();

        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "",
                            data.length,
                            MimeType.JPEG,
                            new ByteArrayInputStream(data)));
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
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0009"
    )
    public void test9() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        String fileName =
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901234567890123456789012345678901234567890123456789" +
                "012345678901.jpg";

        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            fileName,
                            data.length,
                            MimeType.JPEG,
                            new ByteArrayInputStream(data)));
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
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0010"
    )
    public void test10() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            null,
                            data.length,
                            MimeType.JPEG,
                            new ByteArrayInputStream(data)));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0011"
    )
    public void test11() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "dummy.jpg",
                data.length,
                MimeType.JPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "image/jpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0012"
    )
    public void test12() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "dummy.jpg",
                1L,
                MimeType.JPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "dummy.jpg"),
                new Field("size", "1"),
                new Field("mime_type", "image/jpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0013"
    )
    public void test13() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "dummy.jpg",
                30L * 1024L * 1024L,
                MimeType.JPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "dummy.jpg"),
                new Field("size", "31457280"),
                new Field("mime_type", "image/jpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0014"
    )
    public void test14() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "dummy.jpg",
                            30L * 1024L * 1024L + 1L,
                            MimeType.JPEG,
                            new ByteArrayInputStream(data)));
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
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0015"
    )
    public void test15() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "dummy.jpg",
                data.length,
                MimeType.JPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "image/jpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0016"
    )
    public void test16() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "dummy.jpg",
                data.length,
                MimeType.PJPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "image/pjpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0017"
    )
    public void test17() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.THREE_GP,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/3gpp"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0018"
    )
    public void test18() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.AVI,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/avi"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0019"
    )
    public void test19() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.QUICKTIME,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/quicktime"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0020"
    )
    public void test20() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.MP4,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/mp4"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0021"
    )
    public void test21() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.VND_MTS,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/vnd.mts"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0022"
    )
    public void test22() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.MPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/mpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0023"
    )
    public void test23() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "dummy.jpg",
                            data.length,
                            null,
                            new ByteArrayInputStream(data)));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0024"
    )
    public void test24() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "dummy.jpg",
                data.length,
                MimeType.JPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "image/jpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0025"
    )
    public void test25() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "dummy.jpg",
                            data.length,
                            MimeType.JPEG,
                            null));
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                ((ParameterException)exception).getReason());

        // verify mock access.
        verify(authenticateContext, times(0)).getAccessToken();
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0026"
    )
    public void test26() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                100L * 1024L * 1024L,
                MimeType.MPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "104857600"),
                new Field("mime_type", "video/mpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0027"
    )
    public void test27() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.VIDEO,
                            "dummy.jpg",
                            100L * 1024L * 1024L + 1L,
                            MimeType.JPEG,
                            new ByteArrayInputStream(data)));
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
        url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
        id = "UCBLCR0028"
    )
    public void test28() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "dummy.jpg",
                            0L,
                            MimeType.JPEG,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0029"
    )
    public void test29() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "dummy.jpg",
                data.length,
                MimeType.JPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "image/jpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0030"
    )
    public void test30() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.IMAGE,
                "dummy.jpg",
                data.length,
                MimeType.PJPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Image"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "image/pjpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0031"
    )
    public void test31() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "dummy.jpg",
                            0L,
                            MimeType.THREE_GP,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0032"
    )
    public void test32() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "dummy.jpg",
                            0L,
                            MimeType.AVI,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0033"
    )
    public void test33() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "dummy.jpg",
                            0L,
                            MimeType.QUICKTIME,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0034"
    )
    public void test34() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "dummy.jpg",
                            0L,
                            MimeType.MP4,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0035"
    )
    public void test35() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "dummy.jpg",
                            0L,
                            MimeType.VND_MTS,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0036"
    )
    public void test36() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.IMAGE,
                            "dummy.jpg",
                            0L,
                            MimeType.MPEG,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0037"
    )
    public void test37() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.VIDEO,
                            "dummy.jpg",
                            0L,
                            MimeType.JPEG,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0038"
    )
    public void test38() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.VIDEO,
                            "dummy.jpg",
                            0L,
                            MimeType.PJPEG,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0039"
    )
    public void test39() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.THREE_GP,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/3gpp"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0040"
    )
    public void test40() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.AVI,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/avi"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0041"
    )
    public void test41() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.QUICKTIME,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/quicktime"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0042"
    )
    public void test42() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.MP4,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/mp4"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0043"
    )
    public void test43() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.VND_MTS,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/vnd.mts"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0044"
    )
    public void test44() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        UploadContentBodyLogic.Args arg =
            new UploadContentBodyLogic.Args(
                authenticateContext,
                FileType.VIDEO,
                "dummy.jpg",
                data.length,
                MimeType.MPEG,
                new ByteArrayInputStream(data));
        UploadContentBodyLogic logic = new UploadContentBodyLogic();
        HttpPost request = (HttpPost)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());

        // check entity.
        MultipartEntity entity = (MultipartEntity)request.getEntity();
        assertNotNull(entity);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        bos.close();

        String c = entity.getContentType().getValue();
        String boundary = c.substring(c.lastIndexOf(" boundary=") + 10);
        @SuppressWarnings("deprecation")
        MultipartStream ms = new MultipartStream(
                new ByteArrayInputStream(bos.toByteArray()),
                boundary.getBytes());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("dummy".getBytes());
        MultiPartFormData expect = new MultiPartFormData(
                new Field("type", "Video"),
                new Field("title", "dummy.jpg"),
                new Field("size", "5"),
                new Field("mime_type", "video/mpeg"),
                new FileField("dummy.jpg", TestUtils.toHexString(hash)));

        MultiPartFormData actual = getMultiPartFormData(ms);

        assertEquals(expect, actual);
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0045"
    )
    public void test45() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.SLIDE_MOVIE,
                            "dummy.jpg",
                            0L,
                            MimeType.JPEG,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0046"
    )
    public void test46() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.SLIDE_MOVIE,
                            "dummy.jpg",
                            0L,
                            MimeType.PJPEG,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0047"
    )
    public void test47() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.SLIDE_MOVIE,
                            "dummy.jpg",
                            0L,
                            MimeType.THREE_GP,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0048"
    )
    public void test48() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.SLIDE_MOVIE,
                            "dummy.jpg",
                            0L,
                            MimeType.AVI,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0049"
    )
    public void test49() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.SLIDE_MOVIE,
                            "dummy.jpg",
                            0L,
                            MimeType.QUICKTIME,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0050"
    )
    public void test50() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.SLIDE_MOVIE,
                            "dummy.jpg",
                            0L,
                            MimeType.MP4,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0051"
    )
    public void test51() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.SLIDE_MOVIE,
                            "dummy.jpg",
                            0L,
                            MimeType.VND_MTS,
                            new ByteArrayInputStream(data)));
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
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=9",
            id = "UCBLCR0052"
    )
    public void test52() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        byte[] data = "dummy".getBytes();
        Exception exception = null;
        try {
            (new UploadContentBodyLogic()).createRequest(
                    new URL("http://example.com"),
                    new UploadContentBodyLogic.Args(
                            authenticateContext,
                            FileType.SLIDE_MOVIE,
                            "dummy.jpg",
                            0L,
                            MimeType.MPEG,
                            new ByteArrayInputStream(data)));
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

    private Map<String, String> getParameters(MultipartStream ms) throws Exception {
        ParameterParser parser = new ParameterParser();
        parser.setLowerCaseNames(true);
        String[] lines = ms.readHeaders().split("\r\n");
        Map<String, String> retval = new HashMap<String, String>();
        for (String line : lines) {
            if (line.startsWith("Content-Disposition:")) {
                retval.putAll(parser.parse(line, ';'));
            }
        }
        return retval;
    }

    private byte[] getBodyData(MultipartStream ms) throws Exception {
        ByteArrayOutputStream body = new ByteArrayOutputStream();
        ms.readBodyData(body);
        body.close();
        return body.toByteArray();
    }

    private MultiPartFormData getMultiPartFormData(MultipartStream ms) throws Exception {
        MultiPartFormData retval = new MultiPartFormData();
        while (ms.skipPreamble()) {
            Map<String, String> params = getParameters(ms);
            String name = params.get("name");
            byte[] body = getBodyData(ms);
            if ("file".equals(name)) {
                String filename = params.get("filename");
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] hash = md.digest(body);
                retval.fields.add(new FileField(filename,
                        TestUtils.toHexString(hash)));
            } else {
                retval.fields.add(new Field(name, new String(body)));
            }
            ms.discardBodyData();
        }
        return retval;
    }
}

