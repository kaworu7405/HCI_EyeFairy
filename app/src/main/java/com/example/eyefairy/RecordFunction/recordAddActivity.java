package com.example.eyefairy.RecordFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eyefairy.DB.UserDB;
import com.example.eyefairy.DB.UserData;
import com.example.eyefairy.R;

public class recordAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_add);
        EditText left_eye_text = (EditText)findViewById(R.id.left_eys_sight);
        EditText right_eye_text = (EditText)findViewById(R.id.right_eye_sight);
        UserDB db = UserDB.getInstance(this);
        UserData userData = db.userDao().getAll().get(db.userDao().getAll().size()-1);
        float left_eye_float;
        float right_eye_float;

        Button submit_btn = findViewById(R.id.record_eyesight_submit);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    float left_eye_float = Float.parseFloat(left_eye_text.getText().toString());
                    float right_eye_float = Float.parseFloat(right_eye_text.getText().toString());
                }catch (Exception exception){
                    Toast.makeText(recordAddActivity.this, "시력을 정확하게 입력해주세요",  Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}