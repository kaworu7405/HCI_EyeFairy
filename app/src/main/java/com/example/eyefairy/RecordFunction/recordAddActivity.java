package com.example.eyefairy.RecordFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eyefairy.DB.RecordData;
import com.example.eyefairy.DB.UserDB;
import com.example.eyefairy.DB.UserData;
import com.example.eyefairy.Fragment.TimePickerFragment;
import com.example.eyefairy.Fragment.datePickerFragment;
import com.example.eyefairy.Fragment.datePickerFragment_record;
import com.example.eyefairy.R;

import java.text.DateFormat;
import java.util.Calendar;

public class recordAddActivity extends AppCompatActivity{
    String month_string;
    String day_string;
    String year_string;
    String dateMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_add);

        EditText left_eye_text = (EditText)findViewById(R.id.left_eys_sight);
        EditText right_eye_text = (EditText)findViewById(R.id.right_eye_sight);
        TextView date_text=(TextView)findViewById(R.id.checkDateTextView);
        float left_eye_float;
        float right_eye_float;

        Button submit_btn = findViewById(R.id.record_eyesight_submit);
        Button insert_date_btn = findViewById(R.id.insertDateBtn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(left_eye_text.getText().toString().isEmpty()||right_eye_text.getText().toString().isEmpty()||
                    date_text.getText().toString().isEmpty()){
                        Toast.makeText(recordAddActivity.this, "Please fill it all out!",  Toast.LENGTH_SHORT).show();
                    }
                    else{

                        RecordData newRecord=new RecordData(date_text.getText().toString(), Float.parseFloat(left_eye_text.getText().toString()), Float.parseFloat(right_eye_text.getText().toString()));

                        Intent intent=new Intent();
                        intent.putExtra("newRecord", newRecord);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }catch (Exception exception){
                    Toast.makeText(recordAddActivity.this, "시력을 정확하게 입력해주세요",  Toast.LENGTH_SHORT).show();
                }
            }
        });

        insert_date_btn.setOnClickListener(new View.OnClickListener() {
                                               //called when Next Button is Clicked
                                               @Override
                                               public void onClick(View view) {
                                                   showDatePicker(view);
                                               }
                                           }
        );
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new datePickerFragment_record();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day){

        month_string = Integer.toString(month+1);
        day_string = Integer.toString(day);
        year_string = Integer.toString(year);
        if(day>=1 && day<=9){
            day_string = "0"+Integer.toString(day);
        }
        dateMessage = (year_string +  "/"  + month_string +  "/" + day_string);

        TextView DateInput = (TextView)findViewById(R.id.checkDateTextView);
        DateInput.setText(dateMessage);
    }
}