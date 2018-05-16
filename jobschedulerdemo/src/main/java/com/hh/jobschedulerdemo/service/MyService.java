package com.hh.jobschedulerdemo.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private final String TAG = "MyService";
    private final int id = 1000;
    private Timer mTimer;
    private TimerTask mTask;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"Service onCreate");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"1");
        startForeground(id, builder.build());

        mTask = new TimerTask() {
            @Override
            public void run() {
                toastHandler.sendEmptyMessage(0);
            }
        };

        mTimer = new Timer();
        mTimer.schedule(mTask,0,1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"Service onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private final Handler toastHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
//            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            Log.e(TAG,"Hello");
        }
    };
}
