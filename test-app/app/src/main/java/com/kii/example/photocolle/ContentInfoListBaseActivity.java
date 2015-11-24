package com.kii.example.photocolle;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.kii.sdk.photocolle.ContentGUID;
import com.kii.sdk.photocolle.ContentInfo;

abstract class ContentInfoListBaseActivity<T extends ContentInfo>
        extends ListBaseActivity<T> implements OnItemClickListener
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(
            AdapterView<?> parent,
            View view,
            int position,
            long id)
    {
        T obj = getArrayAdapter().getItem(position);
        Intent intent = new Intent(getApplicationContext(),
                ContentInfoViewActivity.class);
        intent.putExtra(ContentInfoViewActivity.KEY_JSON,
                toJson(obj).toString());
        intent.putExtra(ContentInfoViewActivity.KEY_GUID,
                getGuid(obj).getString());
        startActivity(intent);
    }

    protected abstract ContentGUID getGuid(T obj);

    protected abstract JSONObject toJson(T obj);

}
