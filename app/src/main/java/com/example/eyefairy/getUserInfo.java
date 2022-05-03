package com.example.eyefairy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class getUserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_info);
        // init activity
        // info
        // name -> inside DB
        // surgery -> inside DB

        //Get greetText from layout
        TextView greetText = (TextView)findViewById(R.id.greetText);
        greetText.setText("Hi! Nice to meet you.\n I have some questions.");

        //Get Today's date
        ImageButton date_btn = (ImageButton)findViewById(R.id.init_calender_btn);
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        //Invoke next Screen
        Button nextBtn = (Button)findViewById(R.id.init_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            //called when Next Button is Clicked
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), getEyedropInfo.class);
                    startActivity(intent);
                }
            }
        );
    }
}