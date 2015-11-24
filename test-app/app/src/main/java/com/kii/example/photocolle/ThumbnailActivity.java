package com.kii.example.photocolle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.ContentGUID;
import com.kii.sdk.photocolle.ContentThumbnailInfo;
import com.kii.sdk.photocolle.ContentThumbnailInfoList;
import com.kii.sdk.photocolle.PhotoColle;
import com.kii.sdk.photocolle.PhotocolleException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

public class ThumbnailActivity extends Activity {

    static final String KEY_GUID = "contentGUID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thumbnail);
    }

    @Override
    public void onStart() {
        super.onStart();

        final ContentGUID guid = new ContentGUID(
                getIntent().getStringExtra(KEY_GUID));
        getThumbnail(guid);
    }

    private void getThumbnail(ContentGUID...contentGUIDs) {
        try {
            final PhotoColle photoColle = new PhotoColle(
                    getApplicationContext(), AuthenticationContext.loadFrom(
                        this, EntryPoint.getUserID(this),
                        MiscUtils.getClientId(this),
                        MiscUtils.getClientSecret(this)));

            AsyncTask<ContentGUID, Void, ContentThumbnailInfoList> task =
                    new AsyncTask<ContentGUID, Void, ContentThumbnailInfoList>()
            {
                private PhotocolleException exception = null;

                protected ContentThumbnailInfoList doInBackground(ContentGUID... args) {
                    try {
                        return photoColle.getContentThumbnailInfo(args);
                    } catch (PhotocolleException e) {
                        this.exception = e;
                        return null;
                    }
                }

                protected void onPostExecute(ContentThumbnailInfoList response) {
                    if (response != null) {
                        MiscUtils.showDialog(ThumbnailActivity.this,
                                "Get thumbnail", "Success");
                        setResponse(response);
                    } else {
                        if (this.exception != null) {
                            Log.e("ThumbnailActivity",
                                    "Get thumbnail.", this.exception);
                        }
                        MiscUtils.showDialog(ThumbnailActivity.this,
                                "Get thumbnail", "Failed");
                    }
                }
            };
            task.execute(contentGUIDs);
        } catch (Exception e) {
            Log.e("ThumbnailActivity", "Get thumbnail.", e);
            MiscUtils.showDialog(this, "Get thumbnail",
                    "fail to load PhotoColle.");
        }
    }

    private void setResponse(ContentThumbnailInfoList response) {
        JSONArray root = new JSONArray();
        try {
            for (ContentThumbnailInfo info : response.getList()) {
                root.put(getThumbToJson(info));
            }
        } catch (JSONException e) {
            Log.e("ThumbnailActivity", "set response.", e);
        }
        TextView text = (TextView)findViewById(R.id.thumbnail_text);
        try {
            text.setText(root.toString(4));
        } catch (JSONException e) {
            Log.e("ThumbnailActivity", "exception raised", e);
        }
    }

    private JSONObject getThumbToJson(ContentThumbnailInfo info)
        throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put("GUID", info.getGUID().toString());
        json.put("MimeType", info.getMimeType().toString());
        json.put("Thumbnail", Base64.encodeToString(
                info.getThumbnailBytes(), Base64.DEFAULT));
        return json;
    }
}
