package com.example.eyefairy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.eyefairy.DB.AlarmData;
import com.example.eyefairy.DB.UserDB;
import com.example.eyefairy.DB.UserData;

import java.util.ArrayList;

public class getEyedropInfoActivity extends AppCompatActivity {
    ListView listview = null;
    AlarmAdapter adapter = null;
    ArrayList<AlarmData> alarmDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_eyedrop_info);
        ImageView userProfile = (ImageView)findViewById(R.id.userProfile);
        UserDB db = UserDB.getInstance(this);

        UserData userData = db.userDao().getAll().get(db.userDao().getAll().size()-1);
        userProfile.setImageURI(userData.getURI());
        TextView greetText = (TextView)findViewById(R.id.instructionText);
        greetText.setText(userData.getUserName() +", Nice to meet you. \n Please add the info of your eye drops!");
        listview=(ListView)findViewById(R.id.eyeDropListView);
        alarmDataList=new ArrayList<AlarmData>();
        adapter = new AlarmAdapter(this, alarmDataList);
        listview.setAdapter(adapter);

        Button nextBtn = (Button)findViewById(R.id.finishBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            //called when Finish Button is Clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainActivity.class);
                startActivity(intent);
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


        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> a_parent, View a_view, final int a_position, long a_id) {
                Toast.makeText(getApplicationContext(), "long clicked!", Toast.LENGTH_LONG).show();

                // Popup menu 생성
                PopupMenu popup = new PopupMenu(getEyedropInfoActivity.this, a_view);
                getMenuInflater().inflate(R.menu.eyedrop_menu, popup.getMenu());

                // Popup menu click event 처리
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem a_item) {
                        final AlarmData item = (AlarmData) adapter.getItem(a_position);
                        switch (a_item.getItemId()) {
                            case R.id.delete:
                                adapter.delete(a_position);
                                adapter.notifyDataSetChanged();
                                break;

                            default:
                                break;
                        }
                        return false;
                    }
                });

                // Popup 보이기
                popup.show();

                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234 && resultCode == RESULT_OK){
            AlarmData alarmData=(AlarmData) data.getSerializableExtra("newAlarm");

            adapter.add(alarmData);
            adapter.notifyDataSetChanged();
        }else{

        }
    }
}
