package com.kii.example.photocolle;

import java.util.Calendar;

import com.kii.sdk.photocolle.DateFilter;
import com.kii.sdk.photocolle.FileType;
import com.kii.sdk.photocolle.ModifiedDateFilter;
import com.kii.sdk.photocolle.ProjectionType;
import com.kii.sdk.photocolle.SortType;
import com.kii.sdk.photocolle.UploadDateFilter;

import android.os.Bundle;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class IDWithTagsSettingDialogFragment extends DialogFragment
    implements OnClickListener, OnDismissListener
{

    public ProjectionType projectionType;
    public FileType fileType;
    public boolean forDustBox;
    public DateFilter dateFilter;
    public Integer maxResults;
    public Integer start;
    public SortType sortType;

    public IDWithTagsSettingDialogFragment() {
        super();
        projectionType = ProjectionType.ALL_DETAILS;
        fileType = FileType.IMAGE;
        forDustBox = false;
        sortType = SortType.CREATION_DATETIME_ASC;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());

        dialog.setContentView(R.layout.id_with_tags_setting);
        dialog.setTitle(R.string.id_with_tags_setting_title);
        dialog.setOnDismissListener(this);

        Spinner projetionTypeSpinner = (Spinner)dialog.findViewById(
                R.id.id_with_tags_setting_projectiontype_spinner);
        projetionTypeSpinner.setSelection(projectionType.getNumber() - 1);

        Spinner filtTypeSpinner = (Spinner)dialog.findViewById(
                R.id.id_with_tags_setting_filetype_spinner);
        filtTypeSpinner.setSelection(fileType.getNumber());

        CheckBox dustbox = (CheckBox)dialog.findViewById(
                R.id.id_with_tags_setting_dustbox_check);
        dustbox.setChecked(forDustBox);

        Spinner filterSpinner = (Spinner)dialog.findViewById(
                R.id.id_with_tags_setting_filter_spinner);
        if (dateFilter == null) {
            filterSpinner.setSelection(0);
        } else if (dateFilter.getClass() == UploadDateFilter.class) {
            filterSpinner.setSelection(1);
        } else {
            filterSpinner.setSelection(2);
        }

        TimePicker timePicker = (TimePicker)dialog.findViewById(
                R.id.id_with_tags_setting_time_Picker);
        timePicker.setIs24HourView(true);

        EditText maxResultsEdit = (EditText)dialog.findViewById(
                R.id.id_with_tags_setting_maxresults_edit);
        if (maxResults == null) {
            maxResultsEdit.setText("");
        } else {
            maxResultsEdit.setText(maxResults.toString());
        }

        EditText startEdit = (EditText)dialog.findViewById(
                R.id.id_with_tags_setting_start_edit);
        if (start == null) {
            startEdit.setText("");
        } else {
            startEdit.setText(start.toString());
        }

        Spinner sortSpinner = (Spinner)dialog.findViewById(
                R.id.id_with_tags_setting_sort_spinner);
        sortSpinner.setSelection(sortType.getNumber() - 1);

        View button = dialog.findViewById(R.id.id_with_tags_setting_button);
        button.setOnClickListener(this);

        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        projectionType = getProjectionType((Dialog)dialog);
        fileType = getFileType((Dialog)dialog);
        forDustBox = getForDustbox((Dialog)dialog);
        dateFilter = getDateFilter((Dialog)dialog);
        maxResults = getMaxResults((Dialog)dialog);
        start = getStart((Dialog)dialog);
        sortType = getSortType((Dialog)dialog);

        super.onDismiss(dialog);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    private static ProjectionType getProjectionType(Dialog dialog) {
        Spinner spinner = (Spinner)dialog.findViewById(
                R.id.id_with_tags_setting_projectiontype_spinner);
        return ProjectionType.valueOf((String)spinner.getSelectedItem());
    }

    private static FileType getFileType(Dialog dialog) {
        Spinner spinner = (Spinner)dialog.findViewById(
                R.id.id_with_tags_setting_filetype_spinner);
        return FileType.valueOf((String)spinner.getSelectedItem());
    }

    private static boolean getForDustbox(Dialog dialog) {
        CheckBox dustbox = (CheckBox)dialog.findViewById(
                R.id.id_with_tags_setting_dustbox_check);
        return dustbox.isChecked();
    }

    private static DateFilter getDateFilter(Dialog dialog) {
        DatePicker datePicker = (DatePicker)dialog.findViewById(
                R.id.id_with_tags_setting_date_Picker);
        TimePicker timePicker = (TimePicker)dialog.findViewById(
                R.id.id_with_tags_setting_time_Picker);

        Calendar calender = Calendar.getInstance();
        calender.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute(), 0);

        DateFilter retval = null;
        Spinner spinner = (Spinner)dialog.findViewById(
                R.id.id_with_tags_setting_filter_spinner);
        switch (spinner.getSelectedItemPosition()) {
            case 1:// Upload
                retval = new UploadDateFilter(calender.getTime());
                break;
            case 2:// Modified
                retval = new ModifiedDateFilter(calender.getTime());
                break;
            case 0:// NONE
            default:
                break;
        }
        return retval;
    }

    private static Integer getMaxResults(Dialog dialog) {
        EditText edit = (EditText)dialog.findViewById(R.id.id_with_tags_setting_maxresults_edit);
        String text = edit.getText().toString();
        if (text.length() == 0) {
            return null;
        }
        return Integer.parseInt(text);
    }

    private static Integer getStart(Dialog dialog) {
        EditText edit = (EditText)dialog.findViewById(R.id.id_with_tags_setting_start_edit);
        String text = edit.getText().toString();
        if (text.length() == 0) {
            return null;
        }
        return Integer.parseInt(text);
    }

    private static SortType getSortType(Dialog dialog) {
        Spinner sortSpinner = (Spinner)dialog.findViewById(
                R.id.id_with_tags_setting_sort_spinner);
        return SortType.valueOf((String)sortSpinner.getSelectedItem());
    }
}
