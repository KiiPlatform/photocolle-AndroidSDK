package com.kii.example.photocolle;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TagViewActivity extends Activity {

    static final String KEY_JSON = "JSON";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_view);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle bundle = getIntent().getExtras();
        String str = "fail to convert.";

        // FIXME: receive json object.
        try {
            JSONObject json = new JSONObject(bundle.getString(KEY_JSON));
            str = json.toString(4);
        } catch (JSONException e) {
            Log.e("TagViewActivity", "exception raised.", e);
        }
        ((TextView)findViewById(R.id.tag_view_json)).setText(str);
    }

}
