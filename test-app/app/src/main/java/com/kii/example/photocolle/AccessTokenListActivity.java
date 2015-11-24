package com.kii.example.photocolle;

import java.util.ArrayList;
import java.util.Collections;

import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.AuthenticationContextAccessException;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AccessTokenListActivity extends ListActivity {

    private static class AccessTokenAdapter extends BaseAdapter {

        private final SharedPreferences sharedPrefernces;
        private final Context context;

        public AccessTokenAdapter(Context context) {
            this.sharedPrefernces = getPhotoCollePreference(context);
            this.context = context;
        }

        @Override
        public int getCount() {
            return getKeys(this.context).size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup group) {
            String key = getKey(position);
            String value = this.sharedPrefernces.getString(key, "NO VALUE");

            TextView retval = new TextView(this.context);
            retval.setText(String.format("Key=%s\nValue=%s", key, value));
            return retval;
        }

        private String getKey(int position) {
            return getKeys(this.context).get(position);
        }

    }

    static ArrayList<String> getKeys(Context context) {
        SharedPreferences sharedPrefernces = getPhotoCollePreference(context);
        ArrayList<String> keys = new ArrayList<String>(
                sharedPrefernces.getAll().keySet());
        ArrayList<String> retval = new ArrayList<String>();
        for (String key : keys) {
            if (key.endsWith(".accessToken")) {
                retval.add(key);
            }
        }
        Collections.sort(retval);
        return retval;
    }

    static SharedPreferences getPhotoCollePreference(Context context) {
        StringBuilder builder = new StringBuilder();
        builder.append(context.getPackageName()).append('.').
                append("photocollePref");
        return context.getApplicationContext().getSharedPreferences(
                builder.toString(), Context.MODE_PRIVATE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access_token_list);

        ListView listView = (ListView)findViewById(android.R.id.list);
        listView.setAdapter(new AccessTokenAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                String key = getKeys(getApplicationContext()).get(position);
                String storeKey = key.replace(".accessToken", "");
                Intent intent = new Intent(getApplicationContext(),
                        AccessTokenEditActivity.class);
                intent.putExtra(AccessTokenEditActivity.KEY_STOREKEY, storeKey);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listView = (ListView)findViewById(android.R.id.list);
        listView.invalidateViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.access_token_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_clear_access_tokens:
            try {
                AuthenticationContext.removeAll(this);
            } catch (AuthenticationContextAccessException e) {
                Log.e("AccessTokenListActivity", "failed to removeAll.", e);
            } finally {
                ListView listView = (ListView)findViewById(android.R.id.list);
                listView.invalidateViews();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

}
