package com.example.eyefairy.AlarmFunction;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class addAlarmActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener  {
    int hour=1;
    int min=0;
    String ampm="AM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_eyedrop);

        Switch sw=(Switch)findViewById(R.id.eyedropKindSwitch2);
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

        Button addAlarmBtn = (Button)findViewById(R.id.addAlarmBtn);
        addAlarmBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    TextView eyeDropNameEdit = (TextView)findViewById(R.id.eyeDropNameEdit);
                    TextView eyeDropIntervalHEdit = (TextView)findViewById(R.id.eyeDropIntervalHEdit1);
                    TextView eyeDropIntervalMEdit = (TextView)findViewById(R.id.eyeDropIntervalMEdit1);
                    TextView eyeDropHowManyTimesEdit = (TextView)findViewById(R.id.eyeDropHowManyTimesEdit1);
                    TextView eyeDropHowManyDaysEdit = (TextView)findViewById(R.id.eyeDropHowManyDaysEdit1);
                    TextView eyeDropTimeText = (TextView)findViewById(R.id.textView6);

                    if(eyeDropNameEdit.getText().toString().isEmpty()||eyeDropIntervalHEdit.getText().toString().isEmpty()||eyeDropIntervalMEdit.getText().toString().isEmpty()
                    ||eyeDropHowManyTimesEdit.getText().toString().isEmpty()||eyeDropTimeText.getText().toString().isEmpty()||eyeDropHowManyDaysEdit.getText().toString().isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "Please fill it all out!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        AlarmData newAlarm = new AlarmData(eyeDropNameEdit.getText().toString(), Integer.parseInt(eyeDropHowManyTimesEdit.getText().toString()), Integer.parseInt(eyeDropHowManyDaysEdit.getText().toString()), Integer.parseInt(eyeDropIntervalHEdit.getText().toString()), Integer.parseInt(eyeDropIntervalMEdit.getText().toString()), ampm, hour, min, sw.getText().toString());

                        Intent intent = new Intent();
                        intent.putExtra("newAlarm", newAlarm);
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                }
        }
        );

        ImageButton timePicker = (ImageButton) findViewById(R.id.timePicker2);
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
        TextView textView=(TextView) findViewById(R.id.textView6);
        String timeText = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        textView.setText(timeText);
    }
}
