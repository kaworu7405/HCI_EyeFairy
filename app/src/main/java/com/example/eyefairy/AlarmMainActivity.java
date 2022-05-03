package com.example.eyefairy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eyefairy.adapter.AlarmAdapter;
import com.example.eyefairy.data.alarmData;

import java.util.ArrayList;

public class AlarmMainActivity extends AppCompatActivity {

    ArrayList<alarmData> alarmDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alram_main);
        this.InitializeAlarmData();

        ListView listView = (ListView)findViewById(R.id.alarm_listview);
        final AlarmAdapter myAdapter = new AlarmAdapter(this, alarmDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void InitializeAlarmData()
    {
        alarmDataList = new ArrayList<alarmData>();

        alarmDataList.add(new alarmData("잼티스트","240", "4", 1, 30));
        alarmDataList.add(new alarmData("잼지나","240", "3", 2, 10));
        alarmDataList.add(new alarmData("잼지원","240", "3", 2,10));
    }
}