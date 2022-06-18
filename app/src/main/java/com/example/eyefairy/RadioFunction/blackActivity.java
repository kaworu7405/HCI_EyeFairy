package com.example.eyefairy.RadioFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.eyefairy.R;

public class blackActivity extends AppCompatActivity {
    ConstraintLayout layout;
    GestureDetector gd;
    GestureDetector.OnDoubleTapListener listener;
    final int CLICK_DELAY = 200;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //전달받은 메세지안의 번들속 String값을 가져옵니다.
            String tab = msg.getData().getString("click");
            //Textview에 메세지를 띄워줍니다.

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black);

        layout = findViewById(R.id.layout);


        gd = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("click","클릭");
                message.setData(bundle);

                //CLICK_DELAY에 지정해준 시간만큼 시간이 딜레이된 후에 핸들러로 메세지를 전달
                handler.sendMessageDelayed(message,CLICK_DELAY);
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        listener = new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                //더블클릭이 감지되면 한번 클릭시 핸들러에 보냈던 메세지를 삭제합니다.
                handler.removeCallbacksAndMessages(null);

                //더블클릭에 대한 메세지를 전달합니다.
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.setData(bundle);
                handler.sendMessage(message);

                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        };

        gd.setOnDoubleTapListener(listener);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(gd != null){
                    gd.onTouchEvent(event);
                    return true;
                }
                return false;
            }
        });

    }
}