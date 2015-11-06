package com.kii.example.photocolle;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ContentInfoViewActivity extends Activity {

    public static final String KEY_GUID = "GUID";
    public static final String KEY_JSON = "JSON";

    private String guid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_info_view);
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
            Log.e("ContentInfoViewActivity", "exception raised.", e);
        }
        ((TextView)findViewById(R.id.content_info_json)).setText(str);
        this.guid = bundle.getString(KEY_GUID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.content_info_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_get_body:
                startActivity(
                    (new Intent(getApplicationContext(),
                            ContentBodyActivity.class)).putExtra(
                                ContentBodyActivity.KEY_GUID, this.guid));
                return true;
            case R.id.menu_get_thumb:
                startActivity(
                    (new Intent(getApplicationContext(),
                            ThumbnailActivity.class)).putExtra(
                                ThumbnailActivity.KEY_GUID, this.guid));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
