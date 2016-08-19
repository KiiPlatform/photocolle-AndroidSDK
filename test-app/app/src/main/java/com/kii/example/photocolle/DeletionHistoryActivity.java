package com.kii.example.photocolle;

import java.util.Date;

import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.ContentGUID;
import com.kii.sdk.photocolle.FileType;
import com.kii.sdk.photocolle.ListResponse;
import com.kii.sdk.photocolle.PhotoColle;
import com.kii.sdk.photocolle.PhotocolleException;

import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DeletionHistoryActivity extends ListBaseActivity<ContentGUID> {

    private DeletionHistorySettingDialogFragment dialog;

    @Override
    protected PhotoColleDTOAdapter<ContentGUID> getPhotoColleDTOAdapter() {
        return new PhotoColleDTOAdapter<ContentGUID>(this, R.layout.item) {

            @Override
            protected String getTitle(int position) {
                return getItem(position).getString();
            }
        };
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
    public void onStart() {
        super.onStart();

        dialog = new DeletionHistorySettingDialogFragment();

        getHistory(FileType.IMAGE, null, null, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_get:
                getHistory(dialog.fileType, dialog.minDateDeleted,
                        dialog.maxResults, dialog.start);
                return true;
            case R.id.menu_setting:
                dialog.show(getSupportFragmentManager(), "Setting");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getHistory(
            final FileType fileType,
            final Date minDateDeleted,
            final Integer max,
            final Integer start)
    {
        try {
            final PhotoColle photoColle = new PhotoColle(
                    getApplicationContext(), AuthenticationContext.loadFrom(
                        this, EntryPoint.getUserID(this),
                        MiscUtils.getClientId(this),
                        MiscUtils.getClientSecret(this)));

            AsyncTask<Void, Void, ListResponse<ContentGUID>> task =
                    new AsyncTask<Void, Void, ListResponse<ContentGUID>>() {
                private PhotocolleException exception = null;

                protected ListResponse<ContentGUID> doInBackground(
                        Void... args)
                {
                    try {
                        return photoColle.getContentDeletionHistory(
                                fileType, minDateDeleted, max, start);
                    } catch (PhotocolleException e) {
                        this.exception = e;
                        return null;
                    }
                }

                protected void onPostExecute(
                        ListResponse<ContentGUID> response)
                {
                    if (response != null) {
                        setupDataSource(response.getList());
                        MiscUtils.showDialog(DeletionHistoryActivity.this,
                                "Get deletion history.", "Success");
                    } else {
                        if (this.exception != null) {
                            Log.e("DeletionHistoryActivity", "Get deletion history.", this.exception);
                        }
                        MiscUtils.showDialog(DeletionHistoryActivity.this,
                                "Get deletion history.", "Failed");
                    }
                }
            };
            task.execute();
        } catch (Exception e) {
            Log.e("DeletionHistoryActivity", "Get deletion history.", e);
            MiscUtils.showDialog(this, "Get deletion history",
                    "fail to load PhotoColle.");
        }
    }

}
