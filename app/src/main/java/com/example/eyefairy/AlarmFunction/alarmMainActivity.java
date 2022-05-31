package com.example.eyefairy.AlarmFunction;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eyefairy.DB.AlarmDB;
import com.example.eyefairy.R;
import com.example.eyefairy.Adapter.AlarmAdapter;
import com.example.eyefairy.DB.AlarmData;
import com.example.eyefairy.getEyedropInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class alarmMainActivity extends AppCompatActivity {
    ListView listview = null;
    int itemPosition;
    List<AlarmData> alarmDataList;
    AlarmDB alarmDB=null;
    AlarmAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alram_main);
        alarmDB=alarmDB.getInstance(this);
        this.InitializeAlarmData();
        this.registerForContextMenu(listview);
        ImageButton addEyeDropBtn = (ImageButton)findViewById(R.id.addEyedropBtn2);
        addEyeDropBtn.setOnClickListener(new View.OnClickListener() {
            //called when Finish Button is Clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(alarmMainActivity.this, addAlarmActivity.class);
                startActivityForResult(intent, 1234);
            }
            }
        );

    }

    public void InitializeAlarmData()
    {
        alarmDataList = new ArrayList<AlarmData>();
        alarmDataList = alarmDB.alarmDao().getAll();
        ArrayList<AlarmData> arrayList = new ArrayList<AlarmData>();
        arrayList.addAll(alarmDataList);

        alarmDB = AlarmDB.getInstance(this);
        listview = (ListView)findViewById(R.id.alarm_listview);
        adapter = new AlarmAdapter(this, arrayList);
        listview.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234 && resultCode == RESULT_OK){
            AlarmData alarmData=(AlarmData) data.getSerializableExtra("newAlarm");

            adapter.add(alarmData);
            adapter.notifyDataSetChanged();
            alarmDB.alarmDao().insert(alarmData);

        }else if(requestCode == 5678 && resultCode == RESULT_OK){
            AlarmData alarmData=(AlarmData) data.getSerializableExtra("modifiedData");
            Log.i("main", Integer.toString(alarmData.getIntervalM()));
            alarmDB.alarmDao().update(alarmData);
            adapter.update(itemPosition, alarmData);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub

        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, 0, 0, "Delete");
        menu.add(0, 1, 1, "Modify");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info
                = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        itemPosition = info.position;
        int selected = 0;
        switch (item.getItemId()) {

            case 0:
                alarmDB.alarmDao().delete(adapter.getItem(itemPosition));
                adapter.delete(itemPosition);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                Intent intent = new Intent(alarmMainActivity.this, modifyAlarmActivity.class);
                intent.putExtra("data", adapter.getItem(itemPosition));
                startActivityForResult(intent, 5678);
                break;

        }
        return super.onContextItemSelected(item);

    }
}