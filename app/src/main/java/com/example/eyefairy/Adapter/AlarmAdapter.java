package com.example.eyefairy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.eyefairy.R;
import com.example.eyefairy.DB.AlarmData;

import java.util.ArrayList;

public class AlarmAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<AlarmData> alarm ;

    public AlarmAdapter(Context context, ArrayList<AlarmData> data){
        mContext = context;
        alarm = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    public void add(AlarmData data){
        alarm.add(data);
    }

    public void delete(int i){
        alarm.remove(i);
    }

    public void update(int i, AlarmData data)
    {
        alarm.get(i).setAmPm(data.getAmPm());
        alarm.get(i).setHour(data.getHour());
        alarm.get(i).setHowManyTimes(data.getHowManyTimes());
        alarm.get(i).setHowManyDays(data.getHowManyDays());
        alarm.get(i).setIntervalH(data.getIntervalH());
        alarm.get(i).setIntervalM(data.getIntervalM());
        alarm.get(i).setMin(data.getMin());
        alarm.get(i).setName(data.getName());
        alarm.get(i).setKind(data.getKind());
    }
    @Override
    public int getCount() {
        return alarm.size();
    }

    @Override
    public AlarmData getItem(int i) {
        return alarm.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View converView, ViewGroup viewGroup) {
        View view = mLayoutInflater.inflate(R.layout.alarm_info, null);

        TextView eyeDropName = (TextView) view.findViewById(R.id.eyeDropNameText);
        TextView interval = (TextView) view.findViewById(R.id.intervalText);
        TextView times = (TextView) view.findViewById(R.id.repeatText);
        TextView start = (TextView) view.findViewById(R.id.startTimeText);
        Switch sw =(Switch) view.findViewById(R.id.eyedropKindSwitch) ;


        eyeDropName.setText(alarm.get(i).getName());
        interval.setText(Integer.toString(alarm.get(i).getIntervalH())+"h "+alarm.get(i).getIntervalM()+"m");
        times.setText(Integer.toString(alarm.get(i).getHowManyTimes())+" times for "+ Integer.toString(alarm.get(i).getHowManyDays()) +" days" );
        String str=Integer.toString(alarm.get(i).getMin());
        if(alarm.get(i).getMin()==0 || alarm.get(i).getMin()==5){
            str="0"+str;
        }
        start.setText(Integer.toString(alarm.get(i).getHour())+" : "+ str +" "+alarm.get(i).getAmPm());

        if(alarm.get(i).getKind().equals("antibiotic")){
            sw.setText("antibiotic");
            sw.setChecked(false);
        }else{
            sw.setText("artificial tears");
            sw.setChecked(true);
        }
        return view;

    }
}
