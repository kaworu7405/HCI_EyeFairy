package com.example.eyefairy.AlarmFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eyefairy.R;
import com.example.eyefairy.Adapter.AlarmAdapter;
import com.example.eyefairy.DB.AlarmData;

import java.util.ArrayList;

public class alarmMainActivity extends AppCompatActivity {

    ArrayList<AlarmData> alarmDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alram_main);
        this.InitializeAlarmData();

        ListView listView = (ListView)findViewById(R.id.alarm_listview);
       // final AlarmAdapter myAdapter = new AlarmAdapter(this, alarmDataList);
/*
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
*/
    }

    public void InitializeAlarmData()
    {
        alarmDataList = new ArrayList<AlarmData>();
    }
}