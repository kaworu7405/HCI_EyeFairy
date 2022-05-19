package com.example.eyefairy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eyefairy.DB.UserDB;
import com.example.eyefairy.DB.UserData;
import com.example.eyefairy.Fragment.datePickerFragment;

public class getUserInfoActivity extends AppCompatActivity {

    Uri uri = null;
    String month_string;
    String day_string;
    String year_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_info);

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


        Button nextBtn = (Button)findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            //called when Next Button is Clicked
            @Override
            public void onClick(View view) {
                    if(userNameInput.getText().length()<1 || userNameInput.getText().length()>9){
                        Toast.makeText(getApplicationContext(), "Please enter your name with 1-8 characters!", Toast.LENGTH_LONG).show();
                    }
                    else if(uri == null){
                        Toast.makeText(getApplicationContext(), "Please enter your profile image!", Toast.LENGTH_LONG).show();
                    }
                    else if(surgeryDateInput.getText()==""){
                        Toast.makeText(getApplicationContext(), "Please enter your surgery date!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        UserData newUserData = new UserData();
                        newUserData.setUserData(userNameInput.getText().toString(), uri.toString(), Integer.parseInt(year_string), Integer.parseInt(month_string), Integer.parseInt(day_string));
                        db.userDao().insert(newUserData);

                        Intent intent = new Intent(getApplicationContext(), getEyedropInfoActivity.class);
                        startActivity(intent);
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

        ImageButton userImage = (ImageButton) findViewById(R.id.userImageBtn);
        userImage.setOnClickListener(new View.OnClickListener() {
            //called when Next Button is Clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    ImageButton userImageBtn = (ImageButton) findViewById(R.id.userImageBtn);
                    uri = data.getData();
                    userImageBtn.setImageURI(uri);
                }
                break;
        }
    }
}