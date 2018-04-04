package com.hh.mysdk;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hh.mysdk.service.MyService;

public class TestActivity extends AppCompatActivity {

    private String TAG = "MySDK";
    private MyService.DownloadBinder mDownloadBinder;
    private boolean mHasStartService = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        Log.i(TAG,"SDK 被调用了");
        TestPrguard test = new TestPrguard();
        test.readMsg();
    }

    public void onButtonClick(View view) {

        Log.d(TAG, "mySDK Test");
        if (!mHasStartService) {
            Intent intent = new Intent(this,MyService.class);
            bindService(intent,connection,BIND_AUTO_CREATE);
        } else {
            unbindService(connection);
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (MyService.DownloadBinder)service;
            mDownloadBinder.startDownLoad();
            mDownloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
}
