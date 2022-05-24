package com.example.eyefairy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eyefairy.AlarmFunction.alarmMainActivity;
import com.example.eyefairy.RadioFunction.radioActivity;
import com.example.eyefairy.RecordFunction.recordMainActivity;

public class mainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton alarm_btn  = (ImageButton) findViewById(R.id.main_alarm_btn);

        alarm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), alarmMainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton record_btn = (ImageButton) findViewById(R.id.main_record_btn);

        record_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), recordMainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton radio_btn = (ImageButton) findViewById(R.id.main_radio_btn);

        radio_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), radioActivity.class);
                startActivity(intent);
            }

        });




    }
}
