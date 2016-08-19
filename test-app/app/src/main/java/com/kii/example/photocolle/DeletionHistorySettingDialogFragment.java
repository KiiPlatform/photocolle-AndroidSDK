package com.kii.example.photocolle;

import java.util.Calendar;
import java.util.Date;

import com.kii.sdk.photocolle.FileType;

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

public class DeletionHistorySettingDialogFragment extends DialogFragment
    implements OnClickListener, OnDismissListener
{

    public FileType fileType;
    public Date minDateDeleted;
    public Integer maxResults;
    public Integer start;

    public DeletionHistorySettingDialogFragment() {
        super();
        fileType = FileType.IMAGE;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());

        dialog.setContentView(R.layout.del_history_setting);
        dialog.setTitle(R.string.del_history_setting_title);
        dialog.setOnDismissListener(this);

        Spinner spinner = (Spinner)dialog.findViewById(
                R.id.del_history_setting_filetype_spinner);
        spinner.setSelection(fileType.getNumber() - 1);

        CheckBox check = (CheckBox)dialog.findViewById(
                R.id.del_history_setting_date_check);
        check.setChecked(minDateDeleted == null);

        // Ignore set values to pickers.

        TimePicker timePicker = (TimePicker)dialog.findViewById(
                R.id.del_history_setting_time_Picker);
        timePicker.setIs24HourView(true);

        EditText maxResultsEdit = (EditText)dialog.findViewById(
                R.id.del_history_setting_maxresults_edit);
        if (maxResults == null) {
            maxResultsEdit.setText("");
        } else {
            maxResultsEdit.setText(maxResults.toString());
        }

        EditText startEdit = (EditText)dialog.findViewById(
                R.id.del_history_setting_start_edit);
        if (start == null) {
            startEdit.setText("");
        } else {
            startEdit.setText(start.toString());
        }

        View button = dialog.findViewById(R.id.del_history_setting_button);
        button.setOnClickListener(this);

        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        this.fileType = getFileType((Dialog)dialog);
        this.minDateDeleted = getMinDateDeleted((Dialog)dialog);
        this.maxResults = getMaxResults((Dialog)dialog);
        this.start = getStart((Dialog)dialog);

        super.onDismiss(dialog);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    private static FileType getFileType(Dialog dialog) {
        Spinner spinner = (Spinner)dialog.findViewById(
                R.id.del_history_setting_filetype_spinner);
        return FileType.valueOf((String)spinner.getSelectedItem());
    }

    private static Date getMinDateDeleted(Dialog dialog) {
        CheckBox check = (CheckBox)dialog.findViewById(
                R.id.del_history_setting_date_check);
        if (check.isChecked()) {
            return null;
        }
        DatePicker datePicker = (DatePicker)dialog.findViewById(
                R.id.del_history_setting_date_Picker);
        TimePicker timePicker = (TimePicker)dialog.findViewById(
                R.id.del_history_setting_time_Picker);

        Calendar calender = Calendar.getInstance();
        calender.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute(), 0);
        return calender.getTime();
    }

    private static Integer getMaxResults(Dialog dialog) {
        EditText edit = (EditText)dialog.findViewById(R.id.del_history_setting_maxresults_edit);
        String text = edit.getText().toString();
        if (text.length() == 0) {
            return null;
        }
        return Integer.parseInt(text);
    }

    private static Integer getStart(Dialog dialog) {
        EditText edit = (EditText)dialog.findViewById(R.id.del_history_setting_start_edit);
        String text = edit.getText().toString();
        if (text.length() == 0) {
            return null;
        }
        return Integer.parseInt(text);
    }

}
