package com.kii.sdk.photocolle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


class GetContentBodyInfoLogic implements
        Logic<GetContentBodyInfoLogic.Args, ContentBodyInfo>
{

    private static final URL DEFAULT_URL = Command.toUrl(
            "https://xlb.photocolle-docomo.com/file_a2/2.0/ext/content/get");

    public static class Args {

        public final AuthenticationContext context;
        public final FileType fileType;
        public final ContentGUID contentGUID;
        public final ResizeType resizeType;

        public Args(
                AuthenticationContext context,
                FileType fileType,
                ContentGUID contentGUID,
                ResizeType resizeType)
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
            } else if (fileType == FileType.ALL) {
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        "FileType.ALL is unsupported.");
            }
            if (contentGUID == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "contentGUID must not be null.");
            } else if (contentGUID.getString() == null) {
                throw new ParameterException(
                        ParameterException.Reason.NULL_ASSIGNED,
                        "contentGUID string must not be null.");
            } else if (contentGUID.getString().length() < 1 ||
                    contentGUID.getString().length() > 50) {
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        String.format("contentGUID length is out of range: %d",
                                contentGUID.getString().length()));
            }
            if (resizeType == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "resizeType must not be null.");
            } else if (resizeType == ResizeType.RESIZED_VIDEO &&
                    (fileType == FileType.IMAGE ||
                        fileType == FileType.SLIDE_MOVIE)) {
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        "This combination of FileType and ResizeType is invalid.");
            }

            this.context = context;
            this.fileType = fileType;
            this.contentGUID = contentGUID;
            this.resizeType = resizeType;
        }

    }

    @Override
    public HttpUriRequest createRequest(URL url, Args arg) {
        try {
            HttpPost retval = new HttpPost(url.toURI());

            // Set arguments for 2.04 request as HTTP header.
            Command.signRequest(retval, arg.context);

            // Set arguments for 2.04 request as JSON object.
            JSONObject json = new JSONObject();
            json.put("file_type_cd", arg.fileType.getNumber());
            json.put("content_guid", arg.contentGUID.getString());
            json.put("resize_type_cd", arg.resizeType.getNumber());

            retval.setEntity(Command.generateJSONEntity(json));
            return retval;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ContentBodyInfo parseResponse(
            HttpResponse response)
        throws HttpException, ApplicationLayerException,
            ResponseBodyParseException, InvalidTokenException
    {
        try {
            // check status code.
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                ResponseUtil.throwHttpStatusRelatedException(response);
            }

            HttpEntity entity = response.getEntity();
            if (entity.getContentType().getValue().equals("application/json")) {
                // check result.
                JSONObject json = ResponseUtil.newJSONObject(EntityUtils.toString(
                            entity, "UTF-8"));
                int result = ResponseUtil.getInt(json, "result");
                switch (result) {
                    case 1:
                        throw ResponseUtil.newApplicationLayerException(json);
                    default:
                        throw new ParameterException(
                                ParameterException.Reason.OUT_OF_RANGE,
                                "result is out of range: " + result);
                }
            }

            // get body.
            return toContentBodyInfo(entity);
        } catch (IOException e) {
            throw new ResponseBodyParseException(e);
        } catch (ParseException e) {
            throw new ResponseBodyParseException(e);
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    private ContentBodyInfo toContentBodyInfo(
            HttpEntity entity)
        throws ResponseBodyParseException
    {
        try {
            return new ContentBodyInfo(
                    MimeType.fromString(entity.getContentType().getValue()),
                    new ByteArrayInputStream(EntityUtils.toByteArray(entity)));
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        } catch (IOException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public URL getDefaultUrl() {
        return DEFAULT_URL;
    }
}
