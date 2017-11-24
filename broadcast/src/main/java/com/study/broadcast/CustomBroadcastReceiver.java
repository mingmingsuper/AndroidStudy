package com.study.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("自定义通知","自定义通知接收");
        Toast.makeText(context,"自定义发通知",Toast.LENGTH_SHORT).show();
    }
}
