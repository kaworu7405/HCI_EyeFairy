package com.example.eyefairy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.eyefairy.DB.AlarmData;
import com.example.eyefairy.DB.RecordData;
import com.example.eyefairy.R;

import java.util.ArrayList;

public class RecordAdapter extends BaseAdapter  {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<RecordData> record ;

    public RecordAdapter(Context context, ArrayList<RecordData> data){
        mContext = context;
        record= data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void add(RecordData data){
        record.add(data);
    }

    public void delete(int i){
        record.remove(i);
    }

    public void update(int i, RecordData data)
    {
        record.get(i).setDate(data.getDate());
        record.get(i).setLeftEyeSight(data.getLeftEyeSight());
        record.get(i).setRightEyeSight(data.getRightEyeSight());
    }
    @Override
    public int getCount() {
        return record.size();
    }

    @Override
    public RecordData getItem(int i) {
        return record.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View converView, ViewGroup viewGroup) {
        View view = mLayoutInflater.inflate(R.layout.record_info, null);

        TextView date = (TextView) view.findViewById(R.id.recordDateText);
        TextView left = (TextView) view.findViewById(R.id.recordLeftText);
        TextView right = (TextView) view.findViewById(R.id.recordRightText);

        date.setText(record.get(i).getDate().toString());
        left.setText(Float.toString(record.get(i).getLeftEyeSight()));
        right.setText(Float.toString(record.get(i).getRightEyeSight()));
        return view;

    }
}
