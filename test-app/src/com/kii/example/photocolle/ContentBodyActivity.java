package com.kii.example.photocolle;

import java.io.InputStream;

import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.ContentBodyInfo;
import com.kii.sdk.photocolle.ContentGUID;
import com.kii.sdk.photocolle.FileType;
import com.kii.sdk.photocolle.PhotoColle;
import com.kii.sdk.photocolle.PhotocolleException;
import com.kii.sdk.photocolle.ResizeType;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class ContentBodyActivity extends Activity {

    static final String KEY_GUID = "contentGUID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_body);
    }

    @Override
    public void onStart() {
        super.onStart();

        ContentGUID guid = new ContentGUID(
                getIntent().getStringExtra(KEY_GUID));
        getContentBodyInfo(guid);
    }

    private void getContentBodyInfo(ContentGUID guid) {
        try {
            final PhotoColle photoColle = new PhotoColle(
                    getApplicationContext(), AuthenticationContext.loadFrom(
                        this, EntryPoint.getUserID(this),
                        MiscUtils.getClientId(this),
                        MiscUtils.getClientSecret(this)));

            AsyncTask<ContentGUID, Void, ContentBodyInfo> task =
                    new AsyncTask<ContentGUID, Void, ContentBodyInfo>()
            {
                private PhotocolleException exception = null;

                protected ContentBodyInfo doInBackground(ContentGUID... args) {
                    try {
                        return photoColle.getContentBodyInfo(FileType.IMAGE,
                                args[0], ResizeType.ORIGINAL);
                    } catch (PhotocolleException e) {
                        this.exception = e;
                        return null;
                    }
                }

                protected void onPostExecute(ContentBodyInfo response) {
                    if (response != null) {
                        MiscUtils.showDialog(ContentBodyActivity.this,
                                "Get content body", "Success");
                        setResponse(response);
                    } else {
                        if (this.exception != null) {
                            Log.e("ContentBodyActivity",
                                    "Get content body.", this.exception);
                        }
                        MiscUtils.showDialog(ContentBodyActivity.this,
                                "Get content body", "Failed");
                    }
                }
            };
            task.execute(guid);
        } catch (Exception e) {
            Log.e("ContentBodyActivity", "Get content body.", e);
            MiscUtils.showDialog(this, "Get content body",
                    "fail to load PhotoColle.");
        }
    }

    private void setResponse(ContentBodyInfo response) {
        this.setTitle(response.getContentType().toString());
        ImageView imgView = (ImageView)findViewById(R.id.content_body_image);
        InputStream is = response.getInputStream();
        try {
            Bitmap body = BitmapFactory.decodeStream(is);
            imgView.setImageBitmap(body);
        } finally {
            MiscUtils.closeQuietly(is);
        }
    }
}
