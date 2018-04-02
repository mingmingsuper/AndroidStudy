package com.hh.mysdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestActivity extends AppCompatActivity {

    private String TAG = "MySDK";
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
    }
}
