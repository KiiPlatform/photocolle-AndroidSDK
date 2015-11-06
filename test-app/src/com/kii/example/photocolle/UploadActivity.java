package com.kii.example.photocolle;

import org.json.JSONException;
import org.json.JSONObject;

import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.DataID;
import com.kii.sdk.photocolle.FileType;
import com.kii.sdk.photocolle.MimeType;
import com.kii.sdk.photocolle.PhotoColle;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class UploadActivity extends Activity {

    private static final int REQUEST_GALLERY = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
    }

    public void onUploadContentBody(View v) {
        Intent intent = new Intent();
        intent.setType("image/jpeg");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data)
      {
          switch (requestCode) {
              case REQUEST_GALLERY:
                  if (resultCode != RESULT_OK) {
                      Log.w("UploadActivity", "fail to get content.");
                  } else {
                      upload(data.getData());
                  }
                  break;
          }
      }

      private void upload(Uri fileUri) {
          try {
              final PhotoColle photoColle = new PhotoColle(
                      getApplicationContext(), AuthenticationContext.loadFrom(
                          this, EntryPoint.getUserID(this),
                          MiscUtils.getClientId(this),
                          MiscUtils.getClientSecret(this)));

              AsyncTask<Uri, Void, DataID> task =
                      new AsyncTask<Uri, Void, DataID>() {
                  private Exception exception = null;

                  protected DataID doInBackground(Uri... args) {
                      try {
                          Uri uri = args[0];
                          ContentResolver resolver = getContentResolver();
                          Cursor cursor = resolver.query(uri,
                                  new String[] {
                                      MediaStore.Images.ImageColumns.SIZE,
                                      MediaStore.Images.ImageColumns.DISPLAY_NAME },
                                  null, null, null);
                          cursor.moveToFirst();
                          long size = cursor.getLong(cursor.getColumnIndex(
                                      MediaStore.Images.ImageColumns.SIZE));
                          String filename = cursor.getString(cursor.getColumnIndex(
                                      MediaStore.Images.ImageColumns.DISPLAY_NAME));
                          cursor.close();
                          return photoColle.uploadContentBody(
                                  FileType.IMAGE, filename, size,
                                  MimeType.JPEG, resolver.openInputStream(uri));
                      } catch (Exception e) {
                          this.exception = e;
                          return null;
                      }
                  }

                  protected void onPostExecute(DataID response) {
                      if (response != null) {
                          setResponse(response);
                      } else {
                          if (this.exception != null) {
                              Log.e("UploadActivity", "Upload content body",
                                      this.exception);
                          }
                          MiscUtils.showDialog(UploadActivity.this,
                                  "Upload content body", "Failed");
                      }
                  }
              };
              task.execute(fileUri);
          } catch (Exception e) {
              Log.e("UploadActivity", "Upload content body", e);
              MiscUtils.showDialog(this, "Upload content body",
                      "fail to load PhotoColle.");
          }
      }

      private void setResponse(DataID response) {
          JSONObject json = new JSONObject();
          try {
              json.put("dataId", response.getString());
          } catch (JSONException e) {
              Log.e("UploadActivity", "set response.", e);
          }
          TextView text = (TextView)findViewById(R.id.upload_text);
          text.setText(json.toString());
      }
}
