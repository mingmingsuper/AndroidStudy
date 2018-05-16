package com.study.news.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"打开程序完成",Toast.LENGTH_SHORT).show();
        Log.i("开机完成","开机完成");
    }
}
