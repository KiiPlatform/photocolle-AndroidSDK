package com.kii.sdk.photocolle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
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


class GetContentIDListWithTagsLogic implements
        Logic<GetContentIDListWithTagsLogic.Args, ListResponse<DetailedContentInfo>>
{
    private static final URL DEFAULT_URL = Command.toUrl(
            "https://xlb.photocolle-docomo.com/file_a2/2.0/ext/file_search/get_detail");

    public static class Args {

        public final AuthenticationContext context;
        public final ProjectionType projectionType;
        public final FileType fileType;
        public final List<TagHead> criteriaList;
        public final Boolean forDustbox;
        public final DateFilter dateFilter;
        public final Integer maxResults;
        public final Integer start;
        public final SortType sortType;

        public Args(
                AuthenticationContext context,
                ProjectionType projectionType,
                FileType fileType,
                List<TagHead> criteriaList,
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
            if (projectionType == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "projectionType must not be null.");
            }
            if (fileType == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "fileType must not be null.");
            }
            if (projectionType == ProjectionType.FILE_COUNT) {
                // If projectionType is FILE_COUNT, then parameters except
                // fileType should not be sent to server.
                criteriaList = null;
                dateFilter = null;
                maxResults = null;
                start = null;
                sortType = null;
            } else {
                checkArgumentsDefault(criteriaList, dateFilter, maxResults,
                        start, sortType);
            }

            this.context = context;
            this.projectionType = projectionType;
            this.fileType = fileType;
            this.criteriaList = criteriaList;
            this.forDustbox = (projectionType != ProjectionType.FILE_COUNT ?
                    forDustbox : null);
            this.dateFilter = dateFilter;
            this.maxResults = maxResults;
            this.start = start;
            this.sortType = sortType;
        }

        private static void checkArgumentsDefault(
                List<TagHead> criteriaList,
                DateFilter dateFilter,
                Integer maxResults,
                Integer start,
                SortType sortType)
        {
            if (criteriaList != null) {
                if (criteriaList.size() > 5) {
                    throw new ParameterException(
                            ParameterException.Reason.OUT_OF_RANGE,
                            String.format("size of criteriaList is out of range: %d",
                                    criteriaList.size()));
                }
                for (TagHead criteria : criteriaList) {
                    if (criteria != null) {
                        if (criteria.getTagType() == null ||
                                criteria.getTagGUID() == null) {
                            throw new ParameterException(
                                    ParameterException.Reason.NULL_ASSIGNED,
                                    "member of criteria must not be null.");
                        }
                        String guidStr = criteria.getTagGUID().getString();
                        if (guidStr == null) {
                            throw new ParameterException(
                                    ParameterException.Reason.NULL_ASSIGNED,
                                    "value of ContentGUID must not be null.");
                        } else if (guidStr.length() < 1 ||
                                guidStr.length() > 47) {
                            throw new ParameterException(
                                    ParameterException.Reason.OUT_OF_RANGE,
                                    String.format("length of guid string is out of range: %d",
                                            guidStr.length()));
                        }
                    }
                }
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
                if (maxResults.intValue() < 1 || maxResults.intValue() > 1000) {
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
            }
        }

    }

    private Args arg = null;

    @Override
    public HttpUriRequest createRequest(URL url, Args arg) {
        try {
            HttpPost retval = new HttpPost(url.toURI());
            this.arg = arg;

            // Set arguments for 2.01 request as HTTP header.
            Command.signRequest(retval, arg.context);

            // Set arguments for 2.01 request as JSON object.
            JSONObject json = new JSONObject();
            json.put("projection",
                    Integer.toString(arg.projectionType.getNumber()));
            json.put("file_type_cd", arg.fileType.getNumber());

            // set criteria.
            setCriteria(json, arg.criteriaList, 0, "criteria_1_tag_type",
                    "criteria_1_guid");
            setCriteria(json, arg.criteriaList, 1, "criteria_2_tag_type",
                    "criteria_2_guid");
            setCriteria(json, arg.criteriaList, 2, "criteria_3_tag_type",
                    "criteria_3_guid");
            setCriteria(json, arg.criteriaList, 3, "criteria_4_tag_type",
                    "criteria_4_guid");
            setCriteria(json, arg.criteriaList, 4, "criteria_5_tag_type",
                    "criteria_5_guid");

            if (arg.forDustbox != null) {
                json.put("dustbox_condition", arg.forDustbox ? 2 : 1);
            }
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
            if (arg.sortType != null) {
                json.put("sort_type", arg.sortType.getNumber());
            }

            retval.setEntity(Command.generateJSONEntity(json));
            return retval;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setCriteria(
            JSONObject json,
            List<TagHead> criteriaList,
            int index,
            String keyTagType,
            String keyGuid)
        throws JSONException
    {
        if (criteriaList == null) {
            return;
        }
        if (index >= criteriaList.size()) {
            return;
        }
        TagHead criteria = criteriaList.get(index);
        if (criteria != null) {
            json.put(keyTagType, criteria.getTagType().getNumber());
            json.put(keyGuid, criteria.getTagGUID().getString());
        }
    }

    @Override
    public ListResponse<DetailedContentInfo> parseResponse(
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

    private ListResponse<DetailedContentInfo> convertToListResponse(
            JSONObject json)
        throws ResponseBodyParseException
    {
        assert(this.arg != null);

        int start;
        int nextPage;
        switch (this.arg.projectionType) {
            case FILE_COUNT:
                start = ResponseUtil.optIntInRangeMin(json, "start", 1,
                        -1);
                nextPage = ResponseUtil.optIntInRangeMin(json,
                        "next_page", 0, -1);
                break;
            case ALL_DETAILS:
            case DETAILS_WITHOUT_TAGS:
                start = ResponseUtil.getIntInRangeMin(json, "start", 1);
                nextPage = ResponseUtil.getIntInRangeMin(json,
                        "next_page", 0);
                break;
            default:
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        "unknown projectionType: " +
                        this.arg.projectionType.name());
        }

        ListResponse<DetailedContentInfo> retval = new ListResponse<DetailedContentInfo>(
                start,
                nextPage,
                ResponseUtil.getIntInRangeMin(json, "content_cnt", 0));
        JSONArray array = json.optJSONArray("content_list");
        switch (this.arg.projectionType) {
            case FILE_COUNT:
                if (array != null) {
                    throw new ParameterException(
                            ParameterException.Reason.OUT_OF_RANGE,
                            "response must have no content_list.");
                }
                break;
            case ALL_DETAILS:
            case DETAILS_WITHOUT_TAGS:
                if (array != null) {
                    if (array.length() > 1000) {
                        throw new ParameterException(
                                ParameterException.Reason.OUT_OF_RANGE,
                                String.format(
                                    "Count of contents of content_list must equal or be lesser than 100 but %d",
                                    array.length()));
                    }
                    List<DetailedContentInfo> list = retval.getList();
                    for (int i = 0, len = array.length(); i < len; ++i) {
                        list.add(convertToDetailedContentInfo(
                                ResponseUtil.getJSONObjectFromArray(array, i)));
                    }
                } else {
                    throw new ParameterException(
                            ParameterException.Reason.NULL_ASSIGNED,
                            "content_list must not be optional, when projection type is not FILE_COUNT.");
                }
                break;
    
            default:
                throw new ParameterException(
                        ParameterException.Reason.OUT_OF_RANGE,
                        "unknown projectionType: " +
                        this.arg.projectionType.name());
        }
        return retval;
    }

    private static DetailedContentInfo convertToDetailedContentInfo(
            JSONObject json)
        throws ResponseBodyParseException
    {
        return new DetailedContentInfo(
                ResponseUtil.getContentGUIDWithLengthRange(json,
                        "content_guid", 1, 50),
                ResponseUtil.getStringInRangeMinMax(json, "content_name", 1,
                        255),
                ResponseUtil.getFileType(json, "file_type_cd"),
                ResponseUtil.getDate(json, "exif_camera_day"),
                ResponseUtil.getDate(json, "mdate"),
                ResponseUtil.getDate(json, "upload_datetime"),
                ResponseUtil.getLong(json, "file_data_size"),
                ResponseUtil.getString(json, "file_data_xy_rate"),
                ResponseUtil.optScore(json, "prf5score"),
                ResponseUtil.optOrientation(json, "orientation"),
                ResponseUtil.getString(json, "resize_ng_flg").equals("0"),
                toNamedTagHeadList(
                        ResponseUtil.optArrayInRangeMinMax(json,
                                "person_tag_list", 1, 50),
                        TagType.PERSON,
                        "person_tag_guid",
                        "person_tag_name"),
                toNamedTagHeadList(
                        ResponseUtil.optArrayInRangeMinMax(json,
                                "event_tag_list", 1, 100),
                        TagType.EVENT,
                        "event_tag_guid",
                        "event_tag_name"),
                toNamedTagHeadList(
                        ResponseUtil.optArrayInRangeMinMax(json,
                                "optional_tag_list", 1, 100),
                        TagType.FAVORITE,
                        "optional_tag_guid",
                        "optional_tag_name"),
                toNamedTagHeadList(
                        ResponseUtil.optArrayInRangeMin(json,
                                "place_info_list", 1),
                        TagType.PLACEMENT,
                        "place_tag_guid",
                        "place_name"),
                toNamedTagHeadList(
                        ResponseUtil.optArrayInRangeMin(json,
                                "month_info_list", 1),
                        TagType.YEAR_MONTH,
                        "month_tag_guid",
                        "month_tag_name"));
    }

    private static List<NamedTagHead> toNamedTagHeadList(
            JSONArray array,
            TagType type,
            String guidKey,
            String nameKey)
        throws ResponseBodyParseException
    {
        if (array == null) {
            return new ArrayList<NamedTagHead>();
        }
        List<NamedTagHead> retval = new ArrayList<NamedTagHead>();
        for (int i = 0, len = array.length(); i < len; ++i) {
            retval.add(toNamedTagHead(
                    ResponseUtil.getJSONObjectFromArray(array, i), type,
                    guidKey, nameKey));
        }
        return retval;
    }

    private static NamedTagHead toNamedTagHead(
            JSONObject json,
            TagType type,
            String guidKey,
            String nameKey)
        throws ResponseBodyParseException
    {
        return new NamedTagHead(
                type,
                ResponseUtil.getContentGUIDWithLengthRange(json, guidKey, 1,
                        47),
                ResponseUtil.getStringInRangeMinMax(json, nameKey, 1, 20));
    }

    public URL getDefaultUrl() {
        return DEFAULT_URL;
    }
}
