package com.kii.example.photocolle;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.ContentGUID;
import com.kii.sdk.photocolle.DateFilter;
import com.kii.sdk.photocolle.DetailedContentInfo;
import com.kii.sdk.photocolle.FileType;
import com.kii.sdk.photocolle.ListResponse;
import com.kii.sdk.photocolle.PhotoColle;
import com.kii.sdk.photocolle.PhotocolleException;
import com.kii.sdk.photocolle.ProjectionType;
import com.kii.sdk.photocolle.SortType;
import com.kii.sdk.photocolle.TagHead;

public class IDWithTagListActivity
        extends ContentInfoListBaseActivity<DetailedContentInfo>
{
    private IDWithTagsSettingDialogFragment dialog;

    @Override
    protected PhotoColleDTOAdapter<DetailedContentInfo> getPhotoColleDTOAdapter() {
        return new PhotoColleDTOAdapter<DetailedContentInfo>(this, R.layout.item) {

            @Override
            protected String getTitle(int position) {
                return getItem(position).getGuid().getString();
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();

        dialog = new IDWithTagsSettingDialogFragment();

        getIDs(ProjectionType.ALL_DETAILS, FileType.IMAGE, false, null, null,
                null, SortType.CREATION_DATETIME_ASC);
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
        menuInflater.inflate(R.layout.id_with_tags_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_get:
                getIDs(dialog.projectionType, dialog.fileType,
                        dialog.forDustBox, dialog.dateFilter,
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
            final ProjectionType projectionType,
            final FileType fileType,
            final boolean forDustBox,
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

            AsyncTask<Void, Void, ListResponse<DetailedContentInfo>> task =
                    new AsyncTask<Void, Void, ListResponse<DetailedContentInfo>>() {
                private PhotocolleException exception = null;

                protected ListResponse<DetailedContentInfo> doInBackground(
                        Void... args)
                {
                    try {
                        List<TagHead> criteriaList = new ArrayList<TagHead>();
                        return photoColle.getContentIDListWithTags(
                                projectionType, fileType, criteriaList,
                                forDustBox, dateFilter, max, start, sortType);
                    } catch (PhotocolleException e) {
                        this.exception = e;
                        return null;
                    }
                }

                protected void onPostExecute(
                        ListResponse<DetailedContentInfo> response)
                {
                    if (response != null) {
                        setupDataSource(response.getList());
                        MiscUtils.showDialog(IDWithTagListActivity.this,
                                "Get IDs with Tags", "Success");
                    } else {
                        if (this.exception != null) {
                            Log.e("IDWithTagListActivity", "Get IDs with Tags.",
                                    this.exception);
                        }
                        MiscUtils.showDialog(IDWithTagListActivity.this,
                                "Get IDs with Tags", "Failed");
                    }
                }
            };
            task.execute();
        } catch (Exception e) {
            Log.e("IDWithTagListActivity", "Get IDs with Tags", e);
            MiscUtils.showDialog(this, "Get IDs sith Tags",
                    "fail to load PhotoColle.");
        }
    }

    @Override
    protected ContentGUID getGuid(DetailedContentInfo obj) {
        return obj.getGuid();
    }

    @Override
    protected JSONObject toJson(DetailedContentInfo obj) {
        return MiscUtils.fromDetailedContentInfoToJson(obj);
    }
}
