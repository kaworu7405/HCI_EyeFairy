package com.example.eyefairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.example.eyefairy.DB.UserDB;
import com.example.eyefairy.DB.UserData;
import com.example.eyefairy.Fragment.TimePickerFragment;
import com.example.eyefairy.Fragment.datePickerFragment;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class getUserInfoActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    String month_string;
    String day_string;
    String year_string;

    int hour;
    int min;
    String ampm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, splash.class));
        setContentView(R.layout.activity_get_user_info);
        //TypefaceProvider.registerCustomIconSet();
        UserDB db = UserDB.getInstance(this);

        /*
        // When db value changes, it is called
        db.userDao().get().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {

            }
        });

         */

        //Get greetText from layout
        TextView greetText = (TextView)findViewById(R.id.greetText);
        greetText.setText("Hi! Nice to meet you.\n I have some questions.");
        TextView userNameInput = (TextView)findViewById(R.id.userName);
        TextView surgeryDateInput = (TextView)findViewById(R.id.surgeryDateInput);
        TextView textView=(TextView) findViewById(R.id.textView5);

        Button nextBtn = (Button)findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            //called when Next Button is Clicked
            @Override
            public void onClick(View view) {
                    if(userNameInput.getText().length()<1 || userNameInput.getText().length()>9){
                        Toast.makeText(getApplicationContext(), "Please enter your name with 1-8 characters!", Toast.LENGTH_LONG).show();
                    }
                    else if(surgeryDateInput.getText()==""){
                        Toast.makeText(getApplicationContext(), "Please enter your surgery date!", Toast.LENGTH_LONG).show();
                    }
                    else if(textView.getText().equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please enter your surgery time!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        UserData newUserData = new UserData();
                        newUserData.setUserData(userNameInput.getText().toString(), Integer.parseInt(year_string), Integer.parseInt(month_string), Integer.parseInt(day_string), ampm, hour, min );
                        db.userDao().insert(newUserData);

                        Intent intent = new Intent(getApplicationContext(), getEyedropInfoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        );

        ImageButton datePicker = (ImageButton) findViewById(R.id.datePicker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            //called when Next Button is Clicked
            @Override
            public void onClick(View view) {
                    showDatePicker(view);
                }
            }
        );

        ImageButton timePicker = (ImageButton) findViewById(R.id.timePicker1);
        timePicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new datePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day){
        month_string = Integer.toString(month+1);
        day_string = Integer.toString(day);
        year_string = Integer.toString(year);
        String dateMessage = (year_string +  "/"  + month_string +  "/" + day_string);

        TextView surgeryDateInput = (TextView)findViewById(R.id.surgeryDateInput);
        surgeryDateInput.setText(dateMessage);
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
        TextView textView=(TextView) findViewById(R.id.textView5);
        String timeText = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        textView.setText(timeText);

    }
}