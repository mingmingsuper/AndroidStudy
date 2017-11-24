package com.study.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra("newsTitle");
        String content = intent.getStringExtra("newsContent");
        NewsContentFragment newsContentFragment = (NewsContentFragment)getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(title,content);
    }

    public static void actionStart(Context context, String title, String content) {
        Intent intent = new Intent(context,NewsContentActivity.class);
        intent.putExtra("newsTitle",title);
        intent.putExtra("newsContent",content);
        context.startActivity(intent);
    }
}
