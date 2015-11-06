package com.kii.example.photocolle;

import org.json.JSONException;
import org.json.JSONObject;

import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.CapacityInfo;
import com.kii.sdk.photocolle.PhotoColle;
import com.kii.sdk.photocolle.PhotocolleException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CapacityInfoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capacity_info);
    }

    public void onGetCapacityInfo(View v) {
        try {
            final PhotoColle photoColle = new PhotoColle(
                    getApplicationContext(), AuthenticationContext.loadFrom(
                        this, EntryPoint.getUserID(this),
                        MiscUtils.getClientId(this),
                        MiscUtils.getClientSecret(this)));

            AsyncTask<Void, Void, CapacityInfo> task =
                    new AsyncTask<Void, Void, CapacityInfo>()
            {
                private PhotocolleException exception = null;

                protected CapacityInfo doInBackground(Void... args) {
                    try {
                        return photoColle.getCapacityInfo();
                    } catch (PhotocolleException e) {
                        this.exception = e;
                        return null;
                    }
                }

                protected void onPostExecute(CapacityInfo response) {
                    if (response != null) {
                        MiscUtils.showDialog(CapacityInfoActivity.this,
                                "Get capacity information", "Success");
                        setResponse(response);
                    } else {
                        if (this.exception != null) {
                            Log.e("CapacityInfoActivity",
                                    "Get capacity information.", this.exception);
                        }
                        MiscUtils.showDialog(CapacityInfoActivity.this,
                                "Get capacity information", "Failed");
                    }
                }
            };
            task.execute();
        } catch (Exception e) {
            Log.e("CapacityInfoActivity", "Get capacity information.", e);
            MiscUtils.showDialog(this, "Get capacity information",
                    "fail to load PhotoColle.");
        }
    }

    private void setResponse(CapacityInfo response) {
        JSONObject json = new JSONObject();
        try {
            json.put("maxSpace", response.getMaxSpace());
            json.put("freeSpace", response.getFreeSpace());
        } catch (JSONException e) {
            Log.e("CapacityInfoActivity", "set response.", e);
        }
        TextView text = (TextView)findViewById(R.id.capacity_info_text);
        text.setText(json.toString());
    }
}
