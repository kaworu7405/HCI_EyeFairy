package com.example.eyefairy.AlarmFunction;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.eyefairy.DB.AlarmData;
import com.example.eyefairy.Fragment.TimePickerFragment;
import com.example.eyefairy.R;

import java.text.DateFormat;
import java.util.Calendar;

public class modifyAlarmActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    int hour;
    int min;
    String ampm="AM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_eyedrop);

        AlarmData data = (AlarmData)getIntent().getSerializableExtra("data");
        hour=data.getHour();
        min=data.getMin();
        TextView eyeDropNameEdit=(TextView)findViewById(R.id.eyeDropNameEdit);
        eyeDropNameEdit.setText(data.getName());
        TextView eyeDropIntervalHEdit=(TextView)findViewById(R.id.eyeDropIntervalHEdit2);
        eyeDropIntervalHEdit.setText(Integer.toString(data.getIntervalH()));
        TextView eyeDropIntervalMEdit=(TextView)findViewById(R.id.eyeDropIntervalMEdit2);
        eyeDropIntervalMEdit.setText(Integer.toString(data.getIntervalM()));
        TextView eyeDropHowManyTimesEdit=(TextView)findViewById(R.id.eyeDropHowManyTimesEdit2);
        eyeDropHowManyTimesEdit.setText(Integer.toString(data.getHowManyTimes()));
        TextView eyeDropHowManyDaysEdit = (TextView)findViewById(R.id.eyeDropHowManyDaysEdit2);
        eyeDropHowManyDaysEdit.setText(Integer.toString(data.getHowManyDays()));
        TextView eyeDropTimeText = (TextView)findViewById(R.id.textView7);
        eyeDropTimeText.setText(Integer.toString(data.getHour())+":"+Integer.toString(data.getMin())+" "+data.getAmPm());

        Switch sw=(Switch)findViewById(R.id.eyedropKindSwitch3);
        if(data.getKind().equals("artificial tears")){
            sw.setChecked(true);
            sw.setText("artificial tears");
        }
        sw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean checked = sw.isChecked();
                if(checked){
                    sw.setText("artificial tears");
                }
                else {
                    sw.setText("antibiotic");
                }
            }
        });


        Button modifyAlarmBtn = (Button)findViewById(R.id.modifyAlarmBtn);
        modifyAlarmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            if(eyeDropNameEdit.getText().toString().isEmpty()||eyeDropIntervalHEdit.getText().toString().isEmpty()||eyeDropIntervalMEdit.getText().toString().isEmpty()
               ||eyeDropHowManyTimesEdit.getText().toString().isEmpty()||eyeDropHowManyDaysEdit.getText().toString().isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "Please fill it all out!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        data.setName(eyeDropNameEdit.getText().toString());
                        data.setHowManyTimes(Integer.parseInt(eyeDropHowManyTimesEdit.getText().toString()));
                        data.setHowManyDays(Integer.parseInt(eyeDropHowManyDaysEdit.getText().toString()));
                        data.setIntervalH(Integer.parseInt(eyeDropIntervalHEdit.getText().toString()));
                        data.setIntervalM(Integer.parseInt(eyeDropIntervalMEdit.getText().toString()));
                        data.setAmPm(ampm);
                        data.setHour(hour);
                        data.setMin(min);
                        data.setKind(sw.getText().toString());
                        Log.i("modify", eyeDropIntervalMEdit.getText().toString());
                        Intent intent = new Intent();
                        intent.putExtra("modifiedData", data);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }
        );

        ImageButton timePicker = (ImageButton) findViewById(R.id.timePicker3);
        timePicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        hour=hourOfDay;
        min=minute;
        int tmp=c.get(Calendar.AM_PM);
        switch (tmp){
            case Calendar.AM:
                ampm="AM";
                break;
            case Calendar.PM:
                ampm="PM";
                break;
        }

        updateTimeText(c);
    }

    private void updateTimeText(Calendar c){
        TextView textView=(TextView) findViewById(R.id.textView7);
        String timeText = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        textView.setText(timeText);

    }
}
