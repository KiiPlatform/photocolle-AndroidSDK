package com.kii.example.photocolle;

import java.util.Date;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.Category;
import com.kii.sdk.photocolle.FileType;
import com.kii.sdk.photocolle.ListResponse;
import com.kii.sdk.photocolle.PhotoColle;
import com.kii.sdk.photocolle.PhotocolleException;
import com.kii.sdk.photocolle.Tag;

public class TagListActivity extends ListBaseActivity<Tag>
        implements OnItemClickListener
{
    private TagSettingDialogFragment dialog;

    @Override
    protected PhotoColleDTOAdapter<Tag> getPhotoColleDTOAdapter() {
        return new PhotoColleDTOAdapter<Tag>(this, R.layout.item) {

            @Override
            protected String getTitle(int position) {
                return getItem(position).getTagGUID().getString();
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();

        dialog = new TagSettingDialogFragment();

        getTags(Category.ALL, null, null);
    }

    @Override
    protected int getListViewID() {
        // FIXME: specific layout should be made.
        return R.id.id_list;
    }

    @Override
    protected int getLayoutID() {
        // FIXME: specific layout should be made.
        return R.layout.id_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.tag_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_get:
                getTags(dialog.category, dialog.fileType,
                        dialog.minDateModified);
                return true;
            case R.id.menu_get_tags_setting:
                dialog.show(getSupportFragmentManager(), "Setting");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getTags(
            final Category category,
            final FileType fileType,
            final Date minDateModified)
    {
        try {
            final PhotoColle photoColle = new PhotoColle(
                    getApplicationContext(), AuthenticationContext.loadFrom(
                        this, EntryPoint.getUserID(this),
                        MiscUtils.getClientId(this),
                        MiscUtils.getClientSecret(this)));

            AsyncTask<Void, Void, ListResponse<Tag>> task =
                    new AsyncTask<Void, Void, ListResponse<Tag>>() {

                private PhotocolleException exception = null;

                protected ListResponse<Tag> doInBackground(Void... args) {
                    try {
                        return photoColle.getTagIDList(category, fileType,
                                minDateModified);
                    } catch (PhotocolleException e) {
                        this.exception = e;
                        return null;
                    }
                }

                protected void onPostExecute(ListResponse<Tag> response) {
                    if (response != null) {
                        setupDataSource(response.getList());
                        MiscUtils.showDialog(TagListActivity.this, "Get Tags",
                                "Success");
                    } else {
                        if (this.exception != null) {
                            Log.e("IDListActivity", "Get Tags.",
                                    this.exception);
                        }
                        MiscUtils.showDialog(TagListActivity.this, "Get Tags",
                                "Failed");
                    }
                }
            };
            task.execute();
        } catch (Exception e) {
            Log.e("IDListActivity", "Get Tags.", e);
            MiscUtils.showDialog(this, "Get Tags", "fail to load PhotoColle.");
        }
    }

    @Override
    public void onItemClick(
            AdapterView<?> parent,
            View view,
            int position,
            long id)
    {
        JSONObject json = MiscUtils.fromTagToJson(
                (Tag)parent.getAdapter().getItem(position));
        startActivity(
            (new Intent(getApplicationContext(),
                    TagViewActivity.class)).putExtra(TagViewActivity.KEY_JSON,
                            json.toString()));
    }

}
