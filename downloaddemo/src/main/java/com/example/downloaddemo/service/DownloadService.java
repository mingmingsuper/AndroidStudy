package com.example.downloaddemo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.downloaddemo.DownloadListener;
import com.example.downloaddemo.DownloadTask;
import com.example.downloaddemo.MainActivity;
import com.example.downloaddemo.R;

import java.io.File;

public class DownloadService extends Service {

    private DownloadTask mDownloadTask;
    private String mDownloadUrl;

    public DownloadService() {
    }

    private DownloadListener mDownloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("Downloading...",progress));
        }

        @Override
        public void onSuccess() {
            mDownloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",-1));
            Toast.makeText(DownloadService.this,"Download Success",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            mDownloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            mDownloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this,"Download Canceled",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            mDownloadTask = null;
            Toast.makeText(DownloadService.this,"Download Paused",Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return  mBinder;
    }


    public class DownloadBinder extends Binder {

        public void startDownload(String url) {
            if (mDownloadTask == null) {
                mDownloadUrl = url;
                mDownloadTask = new DownloadTask(mDownloadListener);
                mDownloadTask.execute(mDownloadUrl);
                startForeground(1,getNotification("Downloading...",0));
                Toast.makeText(DownloadService.this, "DownLoading...", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload() {
            if (mDownloadTask != null) {
                mDownloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (mDownloadTask != null) {
                mDownloadTask.cancelDownload();
            } else {
                if (mDownloadUrl != null) {
                    String fileName = mDownloadUrl.substring(mDownloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title,int progress) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"10001");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }
}
