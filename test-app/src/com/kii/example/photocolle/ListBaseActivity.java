package com.kii.example.photocolle;

import java.util.List;

import com.kii.sdk.photocolle.DTO;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

abstract class ListBaseActivity<T extends DTO> extends FragmentActivity {

    protected static abstract class PhotoColleDTOAdapter<T>
            extends ArrayAdapter<T>
    {
        private final int textViewResourceId;
        private final LayoutInflater inflater;

        public PhotoColleDTOAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
            this.textViewResourceId = textViewResourceId;
            this.inflater = (LayoutInflater)context.getSystemService(
                    LAYOUT_INFLATER_SERVICE);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout retval = (LinearLayout)convertView;
            if (retval == null) {
                retval = (LinearLayout)this.inflater.inflate(
                        this.textViewResourceId, null);
            }
            ((TextView)retval.findViewById(R.id.title)).setText(getTitle(
                        position));
            return retval;
        }

        protected abstract String getTitle(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

        ListView listView = getListView();
        listView.setAdapter(getPhotoColleDTOAdapter());
    }

    protected abstract PhotoColleDTOAdapter<T> getPhotoColleDTOAdapter();

    protected abstract int getListViewID();

    protected abstract int getLayoutID();

    protected void setupDataSource(List<T> list) {
        ArrayAdapter<T> adapter = getArrayAdapter();

        if (adapter == null) {
            return;
        }
        adapter.clear();
        for (T item : list) {
            adapter.add(item);
        }
        adapter.notifyDataSetChanged();
    }

    protected ListView getListView() {
        return (ListView)findViewById(getListViewID());
    }

    @SuppressWarnings("unchecked")
    protected ArrayAdapter<T> getArrayAdapter() {
        return (ArrayAdapter<T>)(getListView()).getAdapter();
    }
}
