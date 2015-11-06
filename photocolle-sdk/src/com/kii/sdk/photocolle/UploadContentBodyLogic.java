package com.kii.sdk.photocolle;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.kii.sdk.photocolle.http.entity.mime.HttpMultipartMode;
import com.kii.sdk.photocolle.http.entity.mime.MultipartEntity;
import com.kii.sdk.photocolle.http.entity.mime.content.InputStreamBody;
import com.kii.sdk.photocolle.http.entity.mime.content.StringBody;


class UploadContentBodyLogic implements
        Logic<UploadContentBodyLogic.Args, DataID>
{

    private static final URL DEFAULT_URL = Command.toUrl(
            "https://xlb.photocolle-docomo.com/file_a2/1.0/docomo/create");

    public static class Args {

        public final AuthenticationContext context;
        public final FileType fileType;
        public final String fileName;
        public final long size;
        public final MimeType mimeType;
        public final InputStream bodyStream;

        public Args(
                AuthenticationContext context,
                FileType fileType,
                String fileName,
                long size,
                MimeType mimeType,
                InputStream bodyStream)
        {
            if (context == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "context must not be null.");
            }
            if (fileType == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "fileType must not be null.");
            } else {
                switch (fileType) {
                    case IMAGE:
                    case VIDEO:
                        // OK.
                        break;
                    case SLIDE_MOVIE:
                    default:
                        throw new ParameterException(
                                ParameterException.Reason.OUT_OF_RANGE,
                                "fileType must be IMAGE or VIDEO.");
                }
            }
            if (fileName == null) {
                throw new ParameterException(
                        ParameterException.Reason.NULL_ASSIGNED,
                        "fileName must not be null.");
            } else if (fileName.length() < 1 || fileName.length() > 255) {
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        String.format("length of fileName is out of range: %d",
                                fileName.length()));
            }
            if (size < 1L || size > getMaxSize(fileType)) {
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        String.format("size is out of range: %d", size));
            }
            if (mimeType == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "mimeType must not be null.");
            } else if (!isValidMimeTypeAndFileTypeCombnation(mimeType, fileType)) {
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        "This combination of FileType and MimeType is invalid.");
            }
            if (bodyStream == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "bodyStream must not be null.");
            }

            this.context = context;
            this.fileType = fileType;
            this.fileName = fileName;
            this.size = size;
            this.mimeType = mimeType;
            this.bodyStream = bodyStream;
        }

        private static long getMaxSize(FileType fileType) {
            long retval = 0;
            switch (fileType) {
                case IMAGE:
                    retval = 30 * 1024 * 1024;
                    break;
                case VIDEO:
                    retval = 100 * 1024 * 1024;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            return retval;
        }
    }

    @Override
    public HttpUriRequest createRequest(URL url, Args arg) {
        try {
            HttpPost retval = new HttpPost(url.toURI());

            // Set arguments for 2.06 request as HTTP header.
            Command.signRequest(retval, arg.context);

            // Set arguments for 2.06 request as multi part.
            MultipartEntity entity = new MultipartEntity(
                    HttpMultipartMode.BROWSER_COMPATIBLE);
            entity.addPart("type", new StringBody(arg.fileType.getLabel()));
            entity.addPart("title", new StringBody(arg.fileName));
            entity.addPart("size", new StringBody(Long.toString(arg.size)));
            entity.addPart("mime_type",
                    new StringBody(arg.mimeType.getLabel()));
            entity.addPart("file", new InputStreamBody(arg.bodyStream,
                            arg.fileName));

            retval.setEntity(entity);
            return retval;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DataID parseResponse(
            HttpResponse response)
        throws HttpException,
            ResponseBodyParseException, UploadException, InvalidTokenException
    {
        try {
            // check status code.
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                ResponseUtil.throwHttpStatusRelatedException(response);
            }

            // check result.
            JSONObject json = ResponseUtil.newJSONObject(EntityUtils.toString(
                    response.getEntity(), "UTF-8"));
            int result = ResponseUtil.getInt(json, "result");
            switch (result) {
                case 0:
                    return toDataID(json);
                case 1:
                    throw ResponseUtil.newUploadException(json);
                default:
                    throw new ParameterException(
                            ParameterException.Reason.OUT_OF_RANGE,
                            "result is out of range: " + result);
            }
        } catch (IOException e) {
            throw new ResponseBodyParseException(e);
        } catch (org.apache.http.ParseException e) {
            throw new ResponseBodyParseException(e);
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    private DataID toDataID(JSONObject json) throws ResponseBodyParseException {
        return new DataID(ResponseUtil.getString(json, "data_id"));
    }

    public URL getDefaultUrl() {
        return DEFAULT_URL;
    }

    private static boolean isValidMimeTypeAndFileTypeCombnation(
            MimeType mimeType,
            FileType fileType)
    {
        switch (mimeType) {
            case JPEG:
            case PJPEG:
                return (fileType == FileType.IMAGE);
            case AVI:
            case MP4:
            case MPEG:
            case QUICKTIME:
            case THREE_GP:
            case VND_MTS:
                return (fileType == FileType.VIDEO);
            default:
                throw new RuntimeException(
                        "Unknown MimeType: " + mimeType.getLabel());
        }
    }
}
