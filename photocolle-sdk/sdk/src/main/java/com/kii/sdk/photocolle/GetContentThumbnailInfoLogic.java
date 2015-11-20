package com.kii.sdk.photocolle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


class GetContentThumbnailInfoLogic implements
        Logic<GetContentThumbnailInfoLogic.Args, ContentThumbnailInfoList>
{

    private static final URL DEFAULT_URL = Command.toUrl(
            "https://xlb.photocolle-docomo.com/file_a2/4.0/ext/thumbnail_list/get");

    public static class Args {

        public final AuthenticationContext context;
        public final List<ContentGUID> contentGUIDs;

        public Args(AuthenticationContext context, ContentGUID... contentGUIDs) {
            if (context == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "context must not be null.");
            }
            if (contentGUIDs == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "contextGUIDs must not be null.");
            } else if (contentGUIDs.length < 1 || contentGUIDs.length > 100) {
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        String.format("length of contentGUIDs is out of range: %d",
                                contentGUIDs.length));
            } else {
                for (ContentGUID guid : contentGUIDs) {
                    if (guid == null) {
                        throw new ParameterException(
                                ParameterException.Reason.NULL_ASSIGNED,
                                "contextGUID must not be null.");
                    } else if (guid.getString() == null) {
                        throw new ParameterException(
                                ParameterException.Reason.NULL_ASSIGNED,
                                "value of contentGUID must not be null.");
                    } else if (guid.getString().length() < 1 ||
                            guid.getString().length() > 50) {
                        throw new ParameterException(
                                ParameterException.Reason.OUT_OF_RANGE,
                                String.format("length of contentGUID is out of range: %d",
                                        guid.getString().length()));
                    }
                }
            }

            this.context = context;
            this.contentGUIDs = Arrays.asList(contentGUIDs);
        }

    }

    @Override
    public HttpUriRequest createRequest(URL url, Args arg) {
        try {
            HttpPost retval = new HttpPost(url.toURI());

            // Set arguments for 2.30 request as HTTP header.
            Command.signRequest(retval, arg.context);

            // Set arguments for 2.30 request as JSON object.
            JSONArray array = new JSONArray();
            for (ContentGUID guid : arg.contentGUIDs) {
                array.put(guid.getString());
            }
            JSONObject json = new JSONObject();
            json.put("content_info_list", array);

            retval.setEntity(Command.generateJSONEntity(json));
            return retval;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ContentThumbnailInfoList parseResponse(
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

            // check result.
            JSONObject json = ResponseUtil.newJSONObject(EntityUtils.toString(
                    response.getEntity(), "UTF-8"));
            int result = ResponseUtil.getInt(json, "result");
            switch (result) {
                case 0:
                    return toContentThumbnailInfoList(json);
                case 1:
                    Log.e("GetContentThumbnailInfoLogic",
                            String.format("Error: %s", json.toString()));
                    throw ResponseUtil.newApplicationLayerException(json);
                default:
                    throw new ParameterException(
                            ParameterException.Reason.OUT_OF_RANGE,
                            "result is out of range: " + result);
            }
        } catch (IOException e) {
            throw new ResponseBodyParseException(e);
        } catch (ParseException e) {
            throw new ResponseBodyParseException(e);
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    private ContentThumbnailInfoList toContentThumbnailInfoList(
            JSONObject json)
        throws ResponseBodyParseException
    {
        ContentThumbnailInfoList retval = new ContentThumbnailInfoList();
        JSONArray array = ResponseUtil.getArrayInRangeMinMax(json,
                "content_info_list", 1, 100);
        List<ContentThumbnailInfo> list = retval.getList();
        for (int i = 0, len = array.length(); i < len; ++i) {
            list.add(toContentThumbnailInfo(
                    ResponseUtil.getJSONObjectFromArray(array, i)));
        }
        JSONArray ngArray = ResponseUtil.optArrayInRangeMinMax(json,
                "ng_list", 0, 99);
        if (ngArray != null) {
            List<ContentGUID> ngList = retval.getNGList();
            for (int i = 0, len = ngArray.length(); i < len; ++i) {
                ngList.add(ResponseUtil.newContentGUIDWithLengthRange(
                        ResponseUtil.getStringFromArray(ngArray, i), 1, 50));
            }
        }
        return retval;
    }

    private ContentThumbnailInfo toContentThumbnailInfo(
            JSONObject json)
        throws ResponseBodyParseException
    {
        return new ContentThumbnailInfo(
                ResponseUtil.getContentGUIDWithLengthRange(json,
                        "content_guid", 1, 50),
                ResponseUtil.getMimeType(json, "mime_type"),
                ResponseUtil.getThumbnail(json, "thumbnail"));
    }

    public URL getDefaultUrl() {
        return DEFAULT_URL;
    }
}
