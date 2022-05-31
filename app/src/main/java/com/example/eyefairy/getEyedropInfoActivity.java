package com.example.eyefairy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eyefairy.Adapter.AlarmAdapter;
import com.example.eyefairy.AlarmFunction.addAlarmActivity;
import com.example.eyefairy.AlarmFunction.modifyAlarmActivity;
import com.example.eyefairy.DB.AlarmDB;
import com.example.eyefairy.DB.AlarmData;
import com.example.eyefairy.DB.UserDB;
import com.example.eyefairy.DB.UserData;

import java.util.ArrayList;

public class getEyedropInfoActivity extends AppCompatActivity {
    ListView listview = null;
    AlarmAdapter adapter = null;
    ArrayList<AlarmData> alarmDataList;
    AlarmDB alarmDB=null;
    int itemPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_eyedrop_info);
        alarmDB = AlarmDB.getInstance(this);
        UserDB db = UserDB.getInstance(this);

        UserData userData = db.userDao().getAll().get(db.userDao().getAll().size()-1);

        TextView greetText = (TextView)findViewById(R.id.instructionText);
        greetText.setText(userData.getUserName() +", Nice to meet you. \n Please add the info of your eye drops!");
        listview=(ListView)findViewById(R.id.eyeDropListView);
        alarmDataList=new ArrayList<AlarmData>();
        adapter = new AlarmAdapter(this, alarmDataList);
        listview.setAdapter(adapter);
        this.registerForContextMenu(listview);
        Button nextBtn = (Button)findViewById(R.id.finishBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            //called when Finish Button is Clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainActivity.class);
                startActivity(intent);
                finish();
                }
            }
        );

        ImageButton addEyeDropBtn = (ImageButton)findViewById(R.id.addEyedropBtn);
        addEyeDropBtn.setOnClickListener(new View.OnClickListener() {
            //called when Finish Button is Clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getEyedropInfoActivity.this, addAlarmActivity.class);
                startActivityForResult(intent, 1234);
                }
            }
        );


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
            AlarmData alarmData=(AlarmData) data.getSerializableExtra("newAlarm");

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
                Intent intent = new Intent(getEyedropInfoActivity.this, modifyAlarmActivity.class);
                intent.putExtra("data", adapter.getItem(itemPosition));
                startActivityForResult(intent, 5678);
                break;

        }
        return super.onContextItemSelected(item);

    }
}
