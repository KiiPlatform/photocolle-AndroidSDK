package com.kii.sdk.photocolle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;


@SuppressLint("SimpleDateFormat")
class GetTagIDListLogic implements
        Logic<GetTagIDListLogic.Args, ListResponse<Tag>>
{
    private static final URL DEFAULT_URL = Command.toUrl(
            "https://xlb.photocolle-docomo.com/file_a2/2.0/ext/tag_info/get_all");

    public static class Args {

        public final AuthenticationContext context;
        public final Category category;
        public final FileType fileType;
        public final Date minDateModified;

        public Args(
            AuthenticationContext context,
            Category category,
            FileType fileType,
            Date minDateModified)
        {
            if (context == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "context must not be null.");
            }
            if (category == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "category must not be null.");
            }
            // no need to check minDateModified.

            this.context = context;
            this.category = category;
            this.fileType = fileType;
            this.minDateModified = minDateModified;
        }

    }

    @Override
    public HttpUriRequest createRequest(URL url, Args arg) {
        try {
            HttpPost retval = new HttpPost(url.toURI());

            // Set arguments for 2.02 request as HTTP header.
            Command.signRequest(retval, arg.context);

            // Set arguments for 2.02 request as JSON object.
            JSONObject json = new JSONObject();
            json.put("category", arg.category.getNumber());
            if (arg.fileType != null) {
                json.put("file_type_cd", arg.fileType.getNumber());
            }
            if (arg.minDateModified != null) {
                json.put("min_date_modified",
                        MiscUtils.formatAsUTC(arg.minDateModified));
            }

            retval.setEntity(Command.generateJSONEntity(json));
            return retval;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ListResponse<Tag> parseResponse(
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
        } catch (org.apache.http.ParseException e) {
            throw new ResponseBodyParseException(e);
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    private static ListResponse<Tag> toListResponse(
            JSONObject json)
        throws ResponseBodyParseException
    {
        List<Tag> list = new ArrayList<Tag>();
        list.addAll(toPersonTagList(json.optJSONArray("person_tag_list")));
        list.addAll(toEventTagList(json.optJSONArray("event_tag_list")));
        list.addAll(toFavoriteTagList(json.optJSONArray("optional_tag_list")));
        list.addAll(toPlacementTagList(json.optJSONArray("place_info_list")));
        list.addAll(toYearMonthTagList(json.optJSONArray("month_info_list")));
        ListResponse<Tag> retval = new ListResponse<Tag>();
        retval.getList().addAll(list);
        return retval;
    }

    private static List<PersonTag> toPersonTagList(
            JSONArray array)
        throws ResponseBodyParseException
    {
        if (array == null || array.length() == 0) {
            return Collections.<PersonTag>emptyList();
        }
        List<PersonTag> retval = new ArrayList<PersonTag>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        for (int i = 0, len = array.length(); i < len; ++i) {
            retval.add(toPersonTag(
                    ResponseUtil.getJSONObjectFromArray(array, i), format));
        }
        return retval;
    }

    private static PersonTag toPersonTag(
            JSONObject json,
            DateFormat format)
        throws ResponseBodyParseException
    {
        try {
            String birth = ResponseUtil.optString(json, "birth_date");
            Date birthDate = (birth != null) ? format.parse(birth) : null;
            return new PersonTag(
                    ResponseUtil.getContentGUID(json, "person_tag_guid"),
                    ResponseUtil.getString(json, "person_tag_name"),
                    ResponseUtil.getInt(json, "content_cnt"),
                    birthDate);
        } catch (ParseException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    private static List<EventTag> toEventTagList(
            JSONArray array)
        throws ResponseBodyParseException
    {
        if (array == null || array.length() == 0) {
            return Collections.<EventTag>emptyList();
        }
        List<EventTag> retval = new ArrayList<EventTag>();
        for (int i = 0, len = array.length(); i < len; ++i) {
            retval.add(toEventTag(
                    ResponseUtil.getJSONObjectFromArray(array, i)));
        }
        return retval;
    }

    private static EventTag toEventTag(
            JSONObject json)
        throws ResponseBodyParseException
    {
        return new EventTag(
                ResponseUtil.getContentGUID(json, "event_tag_guid"),
                ResponseUtil.getString(json, "event_tag_name"),
                ResponseUtil.getInt(json, "content_cnt"));
    }

    private static List<FavoriteTag> toFavoriteTagList(
            JSONArray array)
        throws ResponseBodyParseException
    {
        if (array == null || array.length() == 0) {
            return Collections.<FavoriteTag>emptyList();
        }
        List<FavoriteTag> retval = new ArrayList<FavoriteTag>();
        for (int i = 0, len = array.length(); i < len; ++i) {
            retval.add(toFavoriteTag(
                    ResponseUtil.getJSONObjectFromArray(array, i)));
        }
        return retval;
    }

    private static FavoriteTag toFavoriteTag(
            JSONObject json)
        throws ResponseBodyParseException
    {
        return new FavoriteTag(
                ResponseUtil.getContentGUID(json, "optional_tag_guid"),
                ResponseUtil.getString(json, "optional_tag_name"),
                ResponseUtil.getInt(json, "content_cnt"));
    }

    private static List<PlacementTag> toPlacementTagList(
            JSONArray array)
        throws ResponseBodyParseException
    {
        if (array == null || array.length() == 0) {
            return Collections.<PlacementTag>emptyList();
        }
        List<PlacementTag> retval = new ArrayList<PlacementTag>();
        for (int i = 0, len = array.length(); i < len; ++i) {
            retval.add(toPlacementTag(
                    ResponseUtil.getJSONObjectFromArray(array, i)));
        }
        return retval;
    }

    private static PlacementTag toPlacementTag(
            JSONObject json)
        throws ResponseBodyParseException
    {
        return new PlacementTag(
                ResponseUtil.getContentGUID(json, "place_tag_guid"),
                ResponseUtil.getString(json, "place_name"),
                ResponseUtil.getInt(json, "content_cnt"));
    }

    private static List<YearMonthTag> toYearMonthTagList(
            JSONArray array)
        throws ResponseBodyParseException
    {
        if (array == null || array.length() == 0) {
            return Collections.<YearMonthTag>emptyList();
        }
        List<YearMonthTag> retval = new ArrayList<YearMonthTag>();
        for (int i = 0, len = array.length(); i < len; ++i) {
            retval.add(toYearMonthTag(
                    ResponseUtil.getJSONObjectFromArray(array, i)));
        }
        return retval;
    }

    private static YearMonthTag toYearMonthTag(
            JSONObject json)
        throws ResponseBodyParseException
    {
        return new YearMonthTag(
                ResponseUtil.getContentGUID(json, "month_tag_guid"),
                ResponseUtil.getString(json, "month_tag_name"),
                ResponseUtil.getInt(json, "content_cnt"));
    }

    public URL getDefaultUrl() {
        return DEFAULT_URL;
    }
}
