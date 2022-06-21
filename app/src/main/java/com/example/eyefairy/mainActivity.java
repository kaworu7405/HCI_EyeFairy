package com.example.eyefairy;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.eyefairy.AlarmFunction.alarmMainActivity;
import com.example.eyefairy.CareFunction.careActivity;
import com.example.eyefairy.RadioFunction.radioActivity;
import com.example.eyefairy.DB.UserDB;
import com.example.eyefairy.DB.UserData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.example.eyefairy.RecordFunction.recordMainActivity;

public class mainActivity extends AppCompatActivity {
    public static Context context;
    // 현재 날짜를 알기 위해 사용
    UserDB db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        context = this;

        init();

        ImageButton alarm_btn = (ImageButton) findViewById(R.id.main_alarm_btn);
        alarm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), alarmMainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton record_btn = (ImageButton) findViewById(R.id.main_record_btn);

        record_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), recordMainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton radio_btn = (ImageButton) findViewById(R.id.main_radio_btn);

        radio_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), radioActivity.class);
                startActivity(intent);
            }

        });

        ImageButton care_btn = (ImageButton) findViewById(R.id.main_care_btn);

        care_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), careActivity.class);
                startActivity(intent);
            }

        });

    }

    public void init()
    {
        db = UserDB.getInstance(this);
        String userInfoStr=null;
        UserData userData;
        TextView UserInfotext=(TextView)findViewById(R.id.userInfoTxt);

        if (db.userDao().getAll().size() == 0) {
            Intent intent = new Intent(getApplicationContext(), getUserInfoActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            userData = db.userDao().getAll().get(db.userDao().getAll().size()-1);

            //dDayValue=ddayResult_int(userData.getSurgeryYear(), userData.getSurgeryMonth(), userData.getSurgeryDay());
            userInfoStr=userData.getUserName()+"\n";
            userInfoStr+=userData.getSurgeryYear()+"년 "+ userData.getSurgeryMonth()+"월 "+userData.getSurgeryDay()+"일\n";
            int dday = countdday(userData.getSurgeryYear(), userData.getSurgeryMonth(), userData.getSurgeryDay());
            if(dday == 0||dday>0){
                userInfoStr+="Day+"+countdday(userData.getSurgeryYear(), userData.getSurgeryMonth(), userData.getSurgeryDay());
            }
            else {
                userInfoStr+="Day"+countdday(userData.getSurgeryYear(), userData.getSurgeryMonth(), userData.getSurgeryDay());
            }


            UserInfotext.setText(userInfoStr);
        }
    }

    public int countdday(int myear, int mmonth, int mday) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar todaCal = Calendar.getInstance(); //오늘날자 가져오기
            Calendar ddayCal = Calendar.getInstance(); //오늘날자를 가져와 변경시킴

            mmonth -= 1; // 받아온날자에서 -1을 해줘야함.
            ddayCal.set(myear,mmonth,mday);// D-day의 날짜를 입력

            long today = todaCal.getTimeInMillis()/86400000; //->(24 * 60 * 60 * 1000) 24시간 60분 60초 * (ms초->초 변환 1000)
            long dday = ddayCal.getTimeInMillis()/86400000;
            long count = dday - today; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.
            return (int) count;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
