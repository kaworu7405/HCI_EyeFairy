package com.example.eyefairy.CareFunction;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.example.eyefairy.R;


public class ListViewLayout extends LinearLayout {

    private TextView btn;
    private TextView contentView;
    public ListViewLayout(Context context, ListItem item){
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listlayout,this,true);

        btn = (TextView)findViewById(R.id.topicbtn);
//        btn.setText(item.getSurgery());
//        btn.setText(item.getSubgroup());
        btn.setText(item.getName());
//        btn.setText(item.getContent());

        contentView = (TextView)findViewById(R.id.contentText);
        contentView.setText(item.getContent());

    }

    public void setText(String text){
        btn.setText(text);
    }
}