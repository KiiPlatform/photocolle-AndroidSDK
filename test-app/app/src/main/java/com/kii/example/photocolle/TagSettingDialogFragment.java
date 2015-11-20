package com.kii.example.photocolle;

import java.util.Calendar;
import java.util.Date;

import com.kii.sdk.photocolle.Category;
import com.kii.sdk.photocolle.FileType;

import android.os.Bundle;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

public class TagSettingDialogFragment extends DialogFragment
    implements android.view.View.OnClickListener, OnDismissListener
{

    public Category category;
    public FileType fileType;
    public Date minDateModified;

    public TagSettingDialogFragment() {
        super();
        category = Category.ALL;
        fileType = FileType.ALL;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());

        dialog.setContentView(R.layout.tag_setting);
        dialog.setTitle(R.string.tag_setting_title);

        Spinner spinner = (Spinner)dialog.findViewById(R.id.tag_setting_category_spinner);
        spinner.setSelection(category.getNumber());

        Spinner filtTypeSpinner = (Spinner)dialog.findViewById(
                R.id.tag_setting_filetype_spinner);
        filtTypeSpinner.setSelection(fileType.getNumber());

        CheckBox check = (CheckBox)dialog.findViewById(
                R.id.tag_setting_date_check);
        check.setChecked(minDateModified == null);

        // Ignore set values to pickers.

        TimePicker timePicker = (TimePicker)dialog.findViewById(
                R.id.tag_setting_time_Picker);
        timePicker.setIs24HourView(true);

        View button = dialog.findViewById(R.id.tag_setting_button);
        button.setOnClickListener(this);

        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        this.category = getCategory((Dialog)dialog);
        this.fileType = getFileType((Dialog)dialog);
        this.minDateModified = getMinDateModified((Dialog)dialog);

        super.onDismiss(dialog);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    private static Category getCategory(Dialog dialog) {
        Spinner spinner = (Spinner)dialog.findViewById(
                R.id.tag_setting_category_spinner);
        return Category.valueOf((String)spinner.getSelectedItem());
    }

    private static FileType getFileType(Dialog dialog) {
        Spinner spinner = (Spinner)dialog.findViewById(
                R.id.tag_setting_filetype_spinner);
        return FileType.valueOf((String)spinner.getSelectedItem());
    }

    private static Date getMinDateModified(Dialog dialog) {
        CheckBox check = (CheckBox)dialog.findViewById(
                R.id.tag_setting_date_check);
        if (check.isChecked()) {
            return null;
        }
        DatePicker datePicker = (DatePicker)dialog.findViewById(
                R.id.tag_setting_date_Picker);
        TimePicker timePicker = (TimePicker)dialog.findViewById(
                R.id.tag_setting_time_Picker);

        Calendar calender = Calendar.getInstance();
        calender.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute(), 0);
        return calender.getTime();
    }
}
