package com.example.eyefairy.CareFunction;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eyefairy.Adapter.AlarmAdapter;
import com.example.eyefairy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class careActivity extends AppCompatActivity {
    ListView list ;
    ListViewAdapter adapter = null;
    TextView test_text;
    List<ListItem> jsonlist = new ArrayList<>();
    ViewGroup.LayoutParams params;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_main);
//
//        test_text = findViewById(R.id.test_text);
        adapter = new ListViewAdapter(this);

        params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        list = new ListView(this);
        adapter = new ListViewAdapter(this);
        setData();
        setList();
        setOnClickListner();



    }

    public void clickBtn(View view) {
        Log.i("버튼 클릭", "clickBtn 진입");
        //json 파일 읽어와서 분석하기

        //assets폴더의 파일을 가져오기 위해
        //창고관리자(AssetManager) 얻어오기
//        try {
//            InputStream is= assetManager.open("jsons/test.json");
//            Log.i("try", "json 읽음1");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //assets/ test.json 파일 읽기 위한 InputStream


    }
    private void setList() {
        try {

            AssetManager assetManager= getAssets();

            InputStream is= assetManager.open("jsons/test_space.json");
            Log.i("try", "1");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader(isr);
            Log.i("try", "2");

            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            String jsonData= "";
            Log.i("try", "3");
            while (line!=null) {
                Log.i("try", "4");
                buffer = new StringBuffer();
                buffer.append(line + "\n");
                line = reader.readLine();
                jsonData = buffer.toString();
                Log.i("try", Integer.toString(jsonData.replace(" ", "").length()));

                if (jsonData.replace(" ", "").length() <= 2){
                    Log.i("try", "4" + jsonData);
                    if (jsonData.replace(" ", "").contains("{")) {
                        Log.i("try", "5" + jsonData);
                        //                    JSONObject tempjsoj= new JSONObject();
                        for (int i = 0; i < 5; i++) {
                            buffer.append(line + "\n");
                            line = reader.readLine();
                        }
                        jsonData = buffer.toString();
                        Log.i("json 읽음", "json 읽음" + jsonData);
                        JSONObject jsonoj = new JSONObject(jsonData);
                        Log.i("json 읽음", "json oj 만듦");
                        Iterator it = jsonoj.keys();

                        ListItem tempItem = new ListItem();
                        while (it.hasNext()) {
                            String key = (String) it.next();
                            tempItem.setSurgery(jsonoj.getString(key));
                            key = (String) it.next();
                            tempItem.setSubgroup(jsonoj.getString(key));
                            key = (String) it.next();
                            tempItem.setName(jsonoj.getString(key));
                            key = (String) it.next();
                            tempItem.setContent(jsonoj.getString(key));
                            System.out.println("key: " + key + ", value: " + jsonoj.getString(key));
                        }
                        jsonlist.add(tempItem);
                        adapter.addItem(tempItem);
                        Log.i("json 읽음", jsonoj.toString());

                        list.setAdapter(adapter);
                        list.setSelector(new PaintDrawable(0x0000000)) ;

                        list.setDivider(new PaintDrawable(0x00000000));
                        list.setDividerHeight(10);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                ListItem curItem = (ListItem) adapter.getItem(i);
//                                adapter.addItem(i+1, curItem);
//                                adapter.
                                Log.d("뭔가 눌림" , getApplicationContext() + "Selected :" + curItem.getName() + " ");
                                Log.d("tag", "뭔가선택됨");
                                whenClicked();
                            }
                        });



                        setContentView(list,params);
                    }
                }
            }

            jsonData= buffer.toString();



        } catch (IOException | JSONException e) {
            Log.i("try", "1");

        }
    }

    private void setData() {
    }

    private void whenClicked() {
        adapter.notifyDataSetChanged();
    }

    private void setOnClickListner() {
    }
}

//}
