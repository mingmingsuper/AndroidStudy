package com.study.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.stream.Stream;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.send_button);
        button.setOnClickListener(view -> {
//                Intent intent = new Intent("com.study.news.broadcast.CUSTOM_BROADCAST");
            Intent intent = new Intent("com.study.news.broadcast.forceOffline");
            sendBroadcast(intent);
        });

        int value = Stream.of(1,2,3,4,5).reduce(100, (sum,item) -> sum + item);
        value = Stream.of(1,2,3,4,5).reduce(100, Integer::sum);
    }
}
