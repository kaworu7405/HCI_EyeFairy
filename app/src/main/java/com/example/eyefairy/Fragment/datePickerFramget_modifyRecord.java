package com.example.eyefairy.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.eyefairy.RecordFunction.recordAddActivity;
import com.example.eyefairy.RecordFunction.recordModifyActivity;

import java.util.Calendar;

public class datePickerFramget_modifyRecord extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        recordModifyActivity activity = (recordModifyActivity) getActivity();
        activity.processDatePickerResult(year, month, day);
    }
}
