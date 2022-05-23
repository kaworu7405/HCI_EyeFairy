package com.example.eyefairy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
        TextView times = (TextView) view.findViewById(R.id.HowManyText);
        TextView hour = (TextView) view.findViewById(R.id.hourText);
        TextView min = (TextView) view.findViewById(R.id.minuteText);


        eyeDropName.setText(alarm.get(i).getName());
        interval.setText(Integer.toString(alarm.get(i).getInterval()));
        times.setText(Integer.toString(alarm.get(i).getHowmany()));
            //hour.set(alarm.get(i).getName());
            //min.setText(alarm.get(i).getName());



        return view;

    }
}
