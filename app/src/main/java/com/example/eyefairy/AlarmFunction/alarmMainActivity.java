package com.example.eyefairy.AlarmFunction;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import com.example.eyefairy.DB.UserDB;
import com.example.eyefairy.DB.UserData;
import com.example.eyefairy.R;
import com.example.eyefairy.Adapter.AlarmAdapter;
import com.example.eyefairy.DB.AlarmData;
import com.example.eyefairy.getEyedropInfoActivity;

import java.util.ArrayList;
import java.util.Calendar;
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
                Log.e("테스트", "ccccc");
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234 && resultCode == RESULT_OK){
            AlarmData alarmData=(AlarmData) data.getSerializableExtra("newAlarm");
            adapter.add(alarmData);
            adapter.notifyDataSetChanged();
            alarmDB.alarmDao().insert(alarmData);

            setAlarm(alarmData);

        }else if(requestCode == 5678 && resultCode == RESULT_OK){
            AlarmData alarmData=(AlarmData) data.getSerializableExtra("modifiedData");
            alarmDB.alarmDao().update(alarmData);
            adapter.update(itemPosition, alarmData);
            adapter.notifyDataSetChanged();


            setAlarm(alarmData);
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
        Intent intent;
        switch (item.getItemId()) {

            case 0: //delete
                AlarmManager alarmManager=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(this, AlarmReceiver.class);
                for(int i=0; i<adapter.getItem(itemPosition).getHowManyTimes(); i++) {
                    int requestCode = adapter.getItem(itemPosition).getKey()*8 + i;
                    PendingIntent cancelIntent = PendingIntent.getBroadcast(alarmMainActivity.this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.cancel(cancelIntent);
                }

                alarmDB.alarmDao().delete(adapter.getItem(itemPosition));
                adapter.delete(itemPosition);
                adapter.notifyDataSetChanged();

                break;

            case 1:
                intent = new Intent(alarmMainActivity.this, modifyAlarmActivity.class);
                intent.putExtra("data", adapter.getItem(itemPosition));
                startActivityForResult(intent, 5678);
                break;

        }
        return super.onContextItemSelected(item);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setAlarm(AlarmData alarmData){
        UserDB db = UserDB.getInstance(this);
        UserData userData = db.userDao().getAll().get(db.userDao().getAll().size()-1);

        AlarmManager alarmManager=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, userData.getSurgeryYear()); //알람 년
        calendar.set(Calendar.MONTH, userData.getSurgeryMonth()-1); //알람  월
        calendar.set(Calendar.DAY_OF_MONTH, userData.getSurgeryDay()); //알람 일

        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, alarmData.getHour());
        calendar.set(Calendar.MINUTE, alarmData.getMin());

        boolean check = false;

        int i=0;
        for(; i<alarmData.getHowManyTimes(); i++){
            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("eyedropName", alarmData.getName());
            intent.putExtra("requestCode", (alarmData.getKey()*8 + i));
            int requestCode = alarmData.getKey()*8 + i;

            PendingIntent alarmIntent = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            if(userData.getSurgery_hour()>=calendar.get(Calendar.HOUR_OF_DAY)&&
                    userData.getSurgery_min()>=calendar.get(Calendar.MINUTE)){
                if(!check){
                    Log.e("date", Integer.toString(userData.getSurgery_hour()));
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    check=true;
                }
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        alarmIntent);
            }
            else {
                if(check){
                    calendar.add(Calendar.DAY_OF_MONTH, -1);
                    check=false;
                }
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        alarmIntent);
            }
            Log.e("date", calendar.getTime().toString());

            calendar.add(Calendar.HOUR_OF_DAY, alarmData.getIntervalH());
            calendar.add(Calendar.MINUTE, alarmData.getIntervalM());
        }
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        for(int j=0; j<alarmData.getHowManyDays(); j++){
            calendar.set(Calendar.HOUR_OF_DAY, alarmData.getHour());
            calendar.set(Calendar.MINUTE, alarmData.getMin());

            for(int k=0; k<alarmData.getHowManyTimes(); k++, i++){
                Intent intent = new Intent(this, AlarmReceiver.class);
                intent.putExtra("eyedropName", alarmData.getName());
                intent.putExtra("requestCode", (alarmData.getKey()*8 + i));
                int requestCode = alarmData.getKey()*8 + i;

                PendingIntent alarmIntent = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                Log.e("date", calendar.getTime().toString());
                calendar.add(Calendar.HOUR_OF_DAY, alarmData.getIntervalH());
                calendar.add(Calendar.MINUTE, alarmData.getIntervalM());
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }


    }




}