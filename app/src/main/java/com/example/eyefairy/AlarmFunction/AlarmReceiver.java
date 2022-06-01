package com.example.eyefairy.AlarmFunction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;
import java.util.StringTokenizer;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

import android.content.BroadcastReceiver;

import com.example.eyefairy.DB.AlarmData;
import com.example.eyefairy.R;
import com.example.eyefairy.getEyedropInfoActivity;

public class AlarmReceiver extends BroadcastReceiver {
    private Context context;
    private String channelId="alarm_channel";
    SharedPreferences pref;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        //pref = context.getSharedPreferences("pref", Activity.MODE_PRIVATE);

        String eyedropName = intent.getStringExtra("eyedropName");
        int requestCode = intent.getIntExtra("requestCode", 0);
        //Log.e("RequestCode", Integer.toString(requestCode));

        Intent busRouteIntent = new Intent(context, alarmMainActivity.class);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(busRouteIntent);
        PendingIntent busRoutePendingIntent =
                stackBuilder.getPendingIntent(requestCode, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.mipmap.ic_launcher).setDefaults(Notification.DEFAULT_ALL)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setDefaults(Notification.DEFAULT_SOUND)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentTitle(eyedropName)
                .setContentText("Time to put in " + eyedropName)
                .setContentIntent(busRoutePendingIntent);




        final NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();

            @SuppressLint("WrongConstant") NotificationChannel channel=new NotificationChannel(channelId,"Channel human readable title",NotificationManager.IMPORTANCE_HIGH);
            channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), audioAttributes);
            notificationManager.createNotificationChannel(channel);

        }

        int id=(int)System.currentTimeMillis();
        //Log.e("id", Integer.toString(id));

        notificationManager.notify(id,notificationBuilder.build());
    }
}
