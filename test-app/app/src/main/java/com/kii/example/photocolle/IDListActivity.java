package com.kii.example.photocolle;

import org.json.JSONObject;

import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.ContentGUID;
import com.kii.sdk.photocolle.ContentInfo;
import com.kii.sdk.photocolle.DateFilter;
import com.kii.sdk.photocolle.FileType;
import com.kii.sdk.photocolle.ListResponse;
import com.kii.sdk.photocolle.PhotoColle;
import com.kii.sdk.photocolle.PhotocolleException;
import com.kii.sdk.photocolle.SortType;

import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class IDListActivity extends ContentInfoListBaseActivity<ContentInfo> {

    private IDSettingDialogFragment dialog;

    @Override
    protected PhotoColleDTOAdapter<ContentInfo> getPhotoColleDTOAdapter() {
        return new PhotoColleDTOAdapter<ContentInfo>(this, R.layout.item) {

            @Override
            protected String getTitle(int position) {
                return getItem(position).getGuid().getString();
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();

        dialog = new IDSettingDialogFragment();

        getIDs(FileType.IMAGE, false, null, null, null,
                SortType.CREATION_DATETIME_ASC);
    }

    @Override
    protected int getListViewID() {
        return R.id.id_list;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.id_list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.id_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_get:
                getIDs(dialog.fileType, dialog.forDustBox, dialog.dateFilter,
                        dialog.maxResults, dialog.start, dialog.sortType);
                return true;
            case R.id.menu_setting:
                dialog.show(getSupportFragmentManager(), "Setting");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getIDs(
            final FileType fileType,
            final boolean forDustbox,
            final DateFilter dateFilter,
            final Integer max,
            final Integer start,
            final SortType sortType)
    {
        try {
            final PhotoColle photoColle = new PhotoColle(
                    getApplicationContext(), AuthenticationContext.loadFrom(
                        this, EntryPoint.getUserID(this),
                        MiscUtils.getClientId(this),
                        MiscUtils.getClientSecret(this)));

            AsyncTask<Void, Void, ListResponse<ContentInfo>> task =
                    new AsyncTask<Void, Void, ListResponse<ContentInfo>>() {
                private PhotocolleException exception = null;

                protected ListResponse<ContentInfo> doInBackground(
                        Void... args)
                {
                    try {
                        return photoColle.getContentIDList(
                                fileType, forDustbox, dateFilter, max, start,
                                sortType);
                    } catch (PhotocolleException e) {
                        this.exception = e;
                        return null;
                    }
                }

                protected void onPostExecute(
                        ListResponse<ContentInfo> response)
                {
                    if (response != null) {
                        setupDataSource(response.getList());
                        MiscUtils.showDialog(IDListActivity.this, "Get IDs",
                                "Success");
                    } else {
                        if (this.exception != null) {
                            Log.e("IDListActivity", "Get IDs.", this.exception);
                        }
                        MiscUtils.showDialog(IDListActivity.this, "Get IDs",
                                "Failed");
                    }
                }
            };
            task.execute();
        } catch (Exception e) {
            Log.e("IDListActivity", "Get IDs.", e);
            MiscUtils.showDialog(this, "Get IDs", "fail to load PhotoColle.");
        }
    }

    @Override
    protected ContentGUID getGuid(ContentInfo obj) {
        return obj.getGuid();
    }

    @Override
    protected JSONObject toJson(ContentInfo obj) {
        return MiscUtils.fromContentInfoToJson(obj);
    }

}
