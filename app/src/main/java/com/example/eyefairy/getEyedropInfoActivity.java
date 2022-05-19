package com.example.eyefairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eyefairy.DB.UserDB;
import com.example.eyefairy.DB.UserData;

public class getEyedropInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_eyedrop_info);
        UserDB db = UserDB.getInstance(this);
        UserData userData = db.userDao().getAll().get(db.userDao().getAll().size()-1);
        TextView greetText = (TextView)findViewById(R.id.instructionText);
        greetText.setText(userData.getUserName() +", Nice to meet you. \n Please add the info of your eye drops!");

        Button nextBtn = (Button)findViewById(R.id.finishBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            //called when Finish Button is Clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainActivity.class);
                startActivity(intent);
                }
            }
        );
    }
}
