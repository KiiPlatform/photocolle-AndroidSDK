package com.kii.sdk.photocolle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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


class GetContentDeletionHistoryLogic implements
        Logic<GetContentDeletionHistoryLogic.Args, ListResponse<ContentGUID>>
{

    private static final URL DEFAULT_URL = Command.toUrl(
            "https://xlb.photocolle-docomo.com/file_a2/2.0/ext/content/get_delete_history");

    public static class Args {

        public final AuthenticationContext context;
        public final FileType fileType;
        public final Date minDateDeleted;
        public final Integer maxResults;
        public final Integer start;

        public Args(
                AuthenticationContext context,
                FileType fileType,
                Date minDateDeleted,
                Integer maxResults,
                Integer start)
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
            // no need to check minDateDeleted.
            if (maxResults != null) {
                if (maxResults.intValue() < 1 || maxResults.intValue() > 100) {
                    throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        String.format("Value of maxResults is out of range: %d",
                                maxResults.intValue()));
                }
            }
            if (start != null) {
                if (start.intValue() < 1) {
                    throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        String.format("Value of start is out of range: %d",
                                start.intValue()));
                }
            }

            this.context = context;
            this.fileType = fileType;
            this.minDateDeleted = minDateDeleted;
            this.maxResults = maxResults;
            this.start = start;
        }

    }

    @Override
    public HttpUriRequest createRequest(URL url, Args arg) {
        try {
            HttpPost retval = new HttpPost(url.toURI());

            // Set arguments for 2.03 request as HTTP header.
            Command.signRequest(retval, arg.context);

            // Set arguments for 2.03 request as JSON object.
            JSONObject json = new JSONObject();
            json.put("file_type_cd", arg.fileType.getNumber());
            // minDateDeleted is optional.
            if (arg.minDateDeleted != null) {
                json.put("min_date_deleted",
                        MiscUtils.formatAsUTC(arg.minDateDeleted));
            }
            // maxResults is optional.
            if (arg.maxResults != null) {
                json.put("max_results", arg.maxResults.intValue());
            }
            // start is optional.
            if (arg.start != null) {
                json.put("start", arg.start.intValue());
            }

            retval.setEntity(Command.generateJSONEntity(json));
            return retval;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ListResponse<ContentGUID> parseResponse(
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
                    return toListResponse(json);
                case 1:
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

    private static ListResponse<ContentGUID> toListResponse(
            JSONObject json)
        throws ResponseBodyParseException
    {
        ListResponse<ContentGUID> retval = new ListResponse<ContentGUID>(
                ResponseUtil.getIntInRangeMin(json, "start", 1),
                ResponseUtil.getIntInRangeMin(json, "next_page", 0),
                ResponseUtil.getIntInRangeMinMax(json, "content_cnt", 0, 100));
        retval.getList().addAll(
                toContentGUIDList(ResponseUtil.getJSONArray(json,
                        "content_list")));
        return retval;
    }

    private static List<ContentGUID> toContentGUIDList(
            JSONArray array)
        throws ResponseBodyParseException
    {
        if (array == null || array.length() == 0) {
            return Collections.<ContentGUID>emptyList();
        } else if (array.length() > 100) {
            throw new ParameterException(ParameterException.Reason.OUT_OF_RANGE,
                    String.format(
                        "Count of contents of content_list must equal or be lesser than 100 but %d",
                        array.length()));
        }
        List<ContentGUID> retval = new ArrayList<ContentGUID>();
        for (int i = 0, len = array.length(); i < len; ++i) {
            retval.add(toContentGUID(
                    ResponseUtil.getStringFromArray(array, i)));
        }
        return retval;
    }

    private static ContentGUID toContentGUID(String guid)
        throws ResponseBodyParseException
    {
        try {
            return ResponseUtil.newContentGUIDWithLengthRange(guid, 1, 50);
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public URL getDefaultUrl() {
        return DEFAULT_URL;
    }
}
