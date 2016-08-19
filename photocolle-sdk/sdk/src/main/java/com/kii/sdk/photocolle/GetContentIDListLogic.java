package com.kii.sdk.photocolle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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



class GetContentIDListLogic implements
        Logic<GetContentIDListLogic.Args, ListResponse<ContentInfo>>
{
    private static final URL DEFAULT_URL = Command.toUrl(
            "https://xlb.photocolle-docomo.com/file_a2/2.0/ext/file_search/get_list");

    public static class Args {

        public final AuthenticationContext context;
        public final FileType fileType;
        public final boolean forDustbox;
        public final DateFilter dateFilter;
        public final Integer maxResults;
        public final Integer start;
        public final SortType sortType;

        public Args(
                AuthenticationContext context,
                FileType fileType,
                boolean forDustbox,
                DateFilter dateFilter,
                Integer maxResults,
                Integer start,
                SortType sortType)
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
            if (dateFilter != null) {
                Class<?> clazz = dateFilter.getClass();
                if (clazz != UploadDateFilter.class &&
                        clazz != ModifiedDateFilter.class) {
                    throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        String.format("Unknown dateFilter class: %s",
                                clazz.getName()));
                }
            }
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
            if (sortType == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "sortType must not be null.");
            } else if (sortType == SortType.SCORE_DESC) {
                throw new ParameterException(
                    ParameterException.Reason.OUT_OF_RANGE,
                    "This method can not use SortType.SCORE_DESC.");
            }

            this.context = context;
            this.fileType = fileType;
            this.forDustbox = forDustbox;
            this.dateFilter = dateFilter;
            this.maxResults = maxResults;
            this.start = start;
            this.sortType = sortType;
        }

    }

    @Override
    public HttpUriRequest createRequest(
            URL url,
            Args arg)
    {
        try {
            HttpPost retval = new HttpPost(url.toURI());

            // Set arguments for 2.00 request as HTTP header.
            Command.signRequest(retval, arg.context);

            // Set arguments for 2.00 request as JSON object.
            JSONObject json = new JSONObject();
            json.put("projection", "3");
            json.put("file_type_cd", arg.fileType.getNumber());
            json.put("dustbox_condition", arg.forDustbox ? 2 : 1);
            // dateFilter is optional.
            if (arg.dateFilter != null) {
                json.put(arg.dateFilter.getFilterName(),
                        arg.dateFilter.getFilterValue());
            }
            // maxResults is optional.
            if (arg.maxResults  != null) {
                json.put("max_results", arg.maxResults);
            }
            // start is optional.
            if (arg.start != null) {
                json.put("start", arg.start);
            }
            json.put("sort_type", arg.sortType.getNumber());

            retval.setEntity(Command.generateJSONEntity(json));
            return retval;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ListResponse<ContentInfo> parseResponse(
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
                    return convertToListResponse(json);
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

    private ListResponse<ContentInfo> convertToListResponse(
            JSONObject json)
        throws ResponseBodyParseException
    {
        ListResponse<ContentInfo> retval = new ListResponse<ContentInfo>(
                ResponseUtil.getIntInRangeMin(json, "start", 1),
                ResponseUtil.getIntInRangeMin(json, "next_page", 0),
                ResponseUtil.getIntInRangeMin(json, "content_cnt", 0));
        List<ContentInfo> list = retval.getList();
        JSONArray array = ResponseUtil.getJSONArray(json, "content_list");
        if (array.length() > 100) {
            throw new ParameterException(ParameterException.Reason.OUT_OF_RANGE,
                    String.format(
                        "Count of contents of content_list must equal or be lesser than 100 but %d",
                        array.length()));
        }
        for (int i = 0, len = array.length(); i < len; ++i) {
            list.add(convertToContentInfo(
                        ResponseUtil.getJSONObjectFromArray(array, i)));
        }
        return retval;
    }

    private ContentInfo convertToContentInfo(
            JSONObject json)
        throws ResponseBodyParseException
    {
        return new ContentInfo(
                ResponseUtil.getContentGUIDWithLengthRange(json,
                        "content_guid", 1, 50),
                ResponseUtil.getStringInRangeMinMax(json, "content_name", 1,
                        255),
                ResponseUtil.getFileType(json, "file_type_cd"),
                ResponseUtil.getDate(json, "exif_camera_day"),
                ResponseUtil.getDate(json, "mdate"),
                ResponseUtil.getDate(json, "upload_datetime"),
                ResponseUtil.getLong(json, "file_data_size"),
                ResponseUtil.getString(json, "resize_ng_flg").equals("0"));
    }

    public URL getDefaultUrl() {
        return DEFAULT_URL;
    }
}
