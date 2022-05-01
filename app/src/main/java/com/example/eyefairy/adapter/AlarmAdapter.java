package com.example.eyefairy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.eyefairy.R;
import com.example.eyefairy.data.alarmData;

import java.util.ArrayList;

public class AlarmAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<alarmData> alarm ;

    public AlarmAdapter(Context context, ArrayList<alarmData> data){
        mContext = context;
        alarm = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return alarm.size();
    }

    @Override
    public alarmData getItem(int i) {
        return alarm.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View converView, ViewGroup viewGroup) {
        View view = mLayoutInflater.inflate(R.layout.alarm_info, null);

        EditText personName = (EditText) view.findViewById(R.id.editTextTextPersonName);
        EditText interval = (EditText) view.findViewById(R.id.editTextNumber);
        EditText times = (EditText) view.findViewById(R.id.editTextNumber2);
        Spinner hour = (Spinner) view.findViewById(R.id.spinner);
        Spinner min = (Spinner) view.findViewById(R.id.spinner2);

        personName.setText(alarm.get(i).getName());
        interval.setText(alarm.get(i).getInterval());
        times.setText(alarm.get(i).getDay());
        //hour.set(alarm.get(i).getName());
        //min.setText(alarm.get(i).getName());

        return view;

    }
}
