package com.example.eyefairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class getEyedropInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_eyedrop_info);

        TextView greetText = (TextView)findViewById(R.id.instructionText);
        greetText.setText("Please\nadd the info of your eye drops");

        Button nextBtn = (Button)findViewById(R.id.finishBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            //called when Finish Button is Clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), main.class);
                startActivity(intent);
                }
            }
        );
    }
}
