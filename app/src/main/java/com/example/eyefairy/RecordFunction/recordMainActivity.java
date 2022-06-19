
package com.example.eyefairy.RecordFunction;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.eyefairy.Adapter.RecordAdapter;
import com.example.eyefairy.DB.RecordDB;
import com.example.eyefairy.DB.RecordData;
import com.example.eyefairy.DB.UserDB;
import com.example.eyefairy.DB.UserData;
import com.example.eyefairy.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class recordMainActivity extends AppCompatActivity {
    private LineChart lineChart;

    ListView listview = null;

    int itemPosition;
    List<RecordData> recordDataList;
    RecordDB recordDB=null;
    RecordAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_main);

        recordDB=recordDB.getInstance(this);
        this.InitializeRecordData();
        this.registerForContextMenu(listview);

        lineChart = (LineChart) findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<>();

        entries.add(new Entry(0, 0));
        entries.add(new Entry(0, 100));
        entries.add(new Entry(3, 3));
        entries.add(new Entry(4, 4));
        entries.add(new Entry(5, 5));

        LineDataSet lineDataSet = new LineDataSet(entries, "속성명1");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleHoleColor(23);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);


        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EaseInCubic);
        lineChart.invalidate();

        Button record_add = (Button) findViewById(R.id.record_add_btn);

        record_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), recordAddActivity.class);
                startActivityForResult(intent, 1234);
            }
        });
    }

    public void InitializeRecordData()
    {
        recordDataList = new ArrayList<RecordData>();
        recordDataList = recordDB.recordDao().getAll();
        ArrayList<RecordData> arrayList = new ArrayList<RecordData>();
        arrayList.addAll(recordDataList);

        recordDB = RecordDB.getInstance(this);
        listview = (ListView)findViewById(R.id.record_list);
        adapter = new RecordAdapter(this, arrayList);
        listview.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234 && resultCode == RESULT_OK){
            RecordData recordData=(RecordData) data.getSerializableExtra("newRecord");

            recordDB.recordDao().insert(recordData);

            recordDataList = recordDB.recordDao().getAll();
            ArrayList<RecordData> arrayList = new ArrayList<RecordData>();
            arrayList.addAll(recordDataList);
            adapter = new RecordAdapter(this, arrayList);
            listview.setAdapter(adapter);

        }else if(requestCode == 5678 && resultCode == RESULT_OK){
            RecordData recordData=(RecordData) data.getSerializableExtra("modifiedData");

            recordData.setKey(recordDB.recordDao().getAll().get(itemPosition).getKey());
            recordDB.recordDao().update(recordData);

            recordDataList = recordDB.recordDao().getAll();
            ArrayList<RecordData> arrayList = new ArrayList<RecordData>();
            arrayList.addAll(recordDataList);
            adapter = new RecordAdapter(this, arrayList);
            listview.setAdapter(adapter);
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
        Intent intent;
        switch (item.getItemId()) {

            case 0: //delete
                recordDB.recordDao().delete(recordDB.recordDao().getAll().get(itemPosition));

                adapter.delete(itemPosition);
                adapter.notifyDataSetChanged();
                break;

            case 1: //modify
                intent = new Intent(recordMainActivity.this, recordModifyActivity.class);
                intent.putExtra("data", adapter.getItem(itemPosition));
                startActivityForResult(intent, 5678);
                break;

        }
        return super.onContextItemSelected(item);

    }
}

