package com.kii.example.photocolle;

import java.util.Arrays;
import java.util.EnumSet;

import com.kii.sdk.photocolle.Scope;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScopeSelectorListActivity extends ListActivity {

    static final String KEY_SCOPES = "scopes";

    private final Scope[] scopes;

    public ScopeSelectorListActivity() {
        this.scopes = EnumSet.allOf(Scope.class).toArray(new Scope[0]);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scope_selector_list);

        ListView listView = (ListView)findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(new ArrayAdapter<Scope>(this,
                android.R.layout.simple_list_item_multiple_choice,
                this.scopes));
        @SuppressWarnings("unchecked")
        EnumSet<Scope> selected =
                (EnumSet<Scope>)getIntent().getSerializableExtra(KEY_SCOPES);
        for (Scope scope : selected) {
            int position = Arrays.binarySearch(this.scopes, scope);
            listView.setItemChecked(position, true);
        }
    }

    @Override
    public void onBackPressed() {
        ListView listView = (ListView)findViewById(android.R.id.list);
        SparseBooleanArray positions = listView.getCheckedItemPositions();

        EnumSet<Scope> selected = EnumSet.noneOf(Scope.class);
        for (int i = 0; i < positions.size(); ++i) {
            int index = positions.keyAt(i);
            if (positions.valueAt(i)) {
                selected.add(this.scopes[index]);
            }
        }

        Intent data = new Intent();
        data.putExtra(KEY_SCOPES, selected);
        setResult(RESULT_OK, data);

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.scope_selector_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_select_all:
                setAllItemChecked(true);
                return true;
            case R.id.menu_select_none:
                setAllItemChecked(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setAllItemChecked(boolean value) {
        ListView listView = (ListView)findViewById(android.R.id.list);
        for (int i = 0; i < listView.getCount(); ++i) {
            listView.setItemChecked(i, value);
        }
    }
}
