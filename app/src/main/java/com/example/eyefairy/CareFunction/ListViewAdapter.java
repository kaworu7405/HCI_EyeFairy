package com.example.eyefairy.CareFunction;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ListItem> listItem = new ArrayList<ListItem>();

    public ListViewAdapter(Context context){

        mContext = context;
//        listItem = li;
    }
//    public onCreateListViewAdapter(ViewGroup parent, int viewType){
//        Context context = parent.getContext();
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
//        View view = inflater.inflate(R.layout.recyclerview_layout, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//
//        return viewHolder;
//        mContext = context;
//    }

    public void addItem(ListItem item){
        listItem.add(item);
    }

    public int getCount() {
        return listItem.size();
    }

    public Object getItem(int i) {
        return listItem.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ListViewLayout itemView;
        if(view ==null){
            itemView = new ListViewLayout(mContext,listItem.get(i));
        }else{
            itemView = (ListViewLayout) view;
            itemView.setText(listItem.get(i).getName());
        }

        return itemView;
    }
}
