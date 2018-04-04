package com.hh.mysdk.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.hh.mysdk.R;
import com.hh.mysdk.TestActivity;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate executed");
        Intent intent = new Intent(this, TestActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this, "10001")
                .setContentTitle("This is content title")
                .setContentText("This is Content Text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.hh_chat_button_hangup)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.hh_chat_button_hangup))
                .setContentIntent(pendingIntent).build();
        startForeground(1,notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public class DownloadBinder extends Binder {

        public void startDownLoad() {
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }
}
