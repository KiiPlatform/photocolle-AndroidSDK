package com.kii.example.photocolle;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kii.sdk.photocolle.ContentGUID;
import com.kii.sdk.photocolle.ContentInfo;
import com.kii.sdk.photocolle.DetailedContentInfo;
import com.kii.sdk.photocolle.EventTag;
import com.kii.sdk.photocolle.FavoriteTag;
import com.kii.sdk.photocolle.FileType;
import com.kii.sdk.photocolle.NamedTagHead;
import com.kii.sdk.photocolle.Orientation;
import com.kii.sdk.photocolle.PersonTag;
import com.kii.sdk.photocolle.PlacementTag;
import com.kii.sdk.photocolle.Score;
import com.kii.sdk.photocolle.Tag;
import com.kii.sdk.photocolle.TagType;
import com.kii.sdk.photocolle.YearMonthTag;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class MiscUtils {

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            Log.w("MiscUtils", "exception raised", e);
        }
    }

    public static String getClientId(Context context) throws Exception {
        return getPhotoColleSetting(context).getString("clientId");
    }

    public static String getClientSecret(Context context)  throws Exception {
        return getPhotoColleSetting(context).getString("clientSecret");
    }

    public static String getRedirectUri(Context context)  throws Exception {
        return getPhotoColleSetting(context).getString("redirectUri");
    }

    private static JSONObject getPhotoColleSetting(
            Context context)
        throws Exception
    {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(
                        context.getResources().getAssets().open(
                            "photocolle_setting.json")));
            for (String str = reader.readLine(); str != null;
                    str = reader.readLine()) {
                builder.append(str);
            }
        } finally {
            // BufferedReader and InputStreamReader close source stream.
            closeQuietly(reader);
        }
        return new JSONObject(builder.toString());
    }

    public static void showDialog(
            Context context,
            String title,
            String message)
    {
        (new AlertDialog.Builder(context)).setTitle(title).setMessage(message)
            .setPositiveButton ("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(
                        DialogInterface dialog,
                        int which)
                    {
                        dialog.cancel();
                    }
                }).show();
    }

    public static JSONObject fromDetailedContentInfoToJson(DetailedContentInfo obj) {
        try {
            JSONObject json = fromContentInfoToJSONObject(obj);
            json.put("score", MiscUtils.toString(obj.getScore()));;
            json.put("orientation", MiscUtils.toString(obj.getOrientation()));
            json.put("persons", MiscUtils.toJSONArray(obj.getPersons()));
            json.put("events", MiscUtils.toJSONArray(obj.getEvents()));
            json.put("favorites", MiscUtils.toJSONArray(obj.getFavorites()));
            json.put("places", MiscUtils.toJSONArray(obj.getPlaces()));
            json.put("yearMonths", MiscUtils.toJSONArray(obj.getYearMonths()));
            return json;
        } catch (Exception e) {
            Log.e("MiscUtils", "exception raised", e);
            return null;
        }
    }


    public static JSONObject fromContentInfoToJson(ContentInfo obj) {
        try {
            return fromContentInfoToJSONObject(obj);
        } catch (Exception e) {
            Log.e("MiscUtils", "exception raised", e);
            return null;
        }
    }

    private static JSONObject fromContentInfoToJSONObject(
            ContentInfo obj)
        throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put("guid", MiscUtils.toString(obj.getGuid()));
        json.put("name", obj.getName());
        json.put("fileType", MiscUtils.toString(obj.getFileType()));
        json.put("exifCameraDay",
                MiscUtils.toString(obj.getExifCameraDate()));
        json.put("mdate", MiscUtils.toString(obj.getModifiedDate()));
        json.put("uploadDatetime",
                MiscUtils.toString(obj.getUploadedDate()));
        json.put("fileDataSize", Long.toString(obj.getFileDataSize()));
        json.put("resizeNGFlag", Boolean.toString(!obj.isResizable()));
        return json;
    }

    private static String toString(ContentGUID guid) {
        return guid == null ? null : guid.getString();
    }

    private static String toString(FileType fileType) {
        return fileType == null ? null : fileType.getLabel();
    }

    private static String toString(Date date) {
        return date == null ? null : date.toString();
    }

    private static String toString(Score score) {
        return score == null ? null : Integer.toString(score.getNumber());
    }

    private static String toString(Orientation orientation) {
        return orientation == null ? null : Integer.toString(
                orientation.getNumber());
    }

    private static JSONArray toJSONArray(
            List<NamedTagHead> tags)
        throws JSONException
    {
        JSONArray retval = new JSONArray();
        for (NamedTagHead tag : tags) {
            retval.put(toJSONObject(tag));
        }
        return retval;
    }

    private static JSONObject toJSONObject(
            NamedTagHead tag)
        throws JSONException
    {
        JSONObject retval = new JSONObject();
        retval.put("guid", MiscUtils.toString(tag.getTagGUID()));
        retval.put("type", MiscUtils.toString(tag.getTagType()));
        retval.put("name", tag.getName());
        return retval;
    }

    private static String toString(TagType tagType) {
        return tagType == null ? null : Integer.toString(tagType.getNumber());
    }

    public static JSONObject fromTagToJson(Tag tag) {
        if (tag == null) {
            return null;
        }
        try {
            Class<?> clazz = tag.getClass();
            if (clazz == EventTag.class) {
                return fromEventTagToJson((EventTag)tag);
            } else if (clazz == FavoriteTag.class) {
                return fromFavoriteTagToJson((FavoriteTag)tag);
            } else if (clazz == PersonTag.class) {
                return fromPersonTagToJson((PersonTag)tag);
            } else if (clazz == PlacementTag.class) {
                return fromPlacementTagToJson((PlacementTag)tag);
            } else if (clazz == YearMonthTag.class) {
                return fromYearMonthTagToJson((YearMonthTag)tag);
            } else {
                throw new RuntimeException(String.format("Unknown class: %s",
                                clazz.getName()));
            }
        } catch (Exception e) {
            Log.e("MiscUtils", "exception raised", e);
            return null;
        }
    }

    private static JSONObject fromEventTagToJson(
            EventTag tag)
        throws JSONException
    {
        return fromTagToJson2(tag);
    }

    private static JSONObject fromFavoriteTagToJson(
            FavoriteTag tag)
        throws JSONException
    {
        return fromTagToJson2(tag);
    }

    private static JSONObject fromPersonTagToJson(
            PersonTag tag)
        throws JSONException
    {
        return fromTagToJson2(tag).put("birthDate", MiscUtils.toString(
                    tag.getBirthDate()));
    }

    private static JSONObject fromPlacementTagToJson(
            PlacementTag tag)
        throws JSONException
    {
        return fromTagToJson2(tag);
    }

    private static JSONObject fromYearMonthTagToJson(
            YearMonthTag tag)
        throws JSONException
    {
        return fromTagToJson2(tag);
    }

    private static JSONObject fromTagToJson2(Tag tag) throws JSONException {
        JSONObject retval = new JSONObject();
        retval.put("guid", toString(tag.getTagGUID()));
        retval.put("type", toString(tag.getTagType()));
        retval.put("name", tag.getName());
        retval.put("contentsCount", tag.getContentsCount());
        return retval;
    }


}
