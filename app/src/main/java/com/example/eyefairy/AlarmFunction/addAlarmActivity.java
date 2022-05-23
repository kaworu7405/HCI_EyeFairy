package com.example.eyefairy.AlarmFunction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eyefairy.DB.AlarmData;
import com.example.eyefairy.R;

import org.w3c.dom.Text;

public class addAlarmActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_eyedrop);

        Button addAlarmBtn = (Button)findViewById(R.id.addAlarmBtn);
        addAlarmBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    TextView eyeDropNameEdit = (TextView)findViewById(R.id.eyeDropNameEdit);
                    TextView eyeDropIntervalEdit = (TextView)findViewById(R.id.eyeDropIntervalEdit);
                    TextView eyeDropHowManyEdit = (TextView)findViewById(R.id.eyeDropHowManyEdit);

                    if(eyeDropNameEdit.getText().toString().isEmpty()||eyeDropIntervalEdit.getText().toString().isEmpty()
                    ||eyeDropHowManyEdit.getText().toString().isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "Please fill it all out!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        AlarmData newAlarm = new AlarmData(eyeDropNameEdit.getText().toString(), Integer.parseInt(eyeDropIntervalEdit.getText().toString()), Integer.parseInt(eyeDropHowManyEdit.getText().toString()), 1, 30);

                        Intent intent = new Intent();
                        intent.putExtra("newAlarm", newAlarm);
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                }
        }
        );

    }

}
