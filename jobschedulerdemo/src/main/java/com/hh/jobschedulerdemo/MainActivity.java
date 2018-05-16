package com.hh.jobschedulerdemo;

import android.app.job.JobScheduler;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hh.jobschedulerdemo.service.MyService;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech = null;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        registerService();
        textToSpeech = new TextToSpeech(this,this);
        editText = findViewById(R.id.text_input);
    }

//    public void registerService() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            startService(new Intent(this, MyService.class));
////            startForegroundService(new Intent(this, MyService.class));
//        }
//    }

    public void readTextToVoice(View view) {
        String text = editText.getText().toString();
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,Bundle.EMPTY,"");
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.CHINESE);
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.ERROR) {
                Toast.makeText(this,"数据丢失或语言不支持",Toast.LENGTH_SHORT);
            }
            if(result == TextToSpeech.LANG_AVAILABLE) {
                Toast.makeText(this,"支持语言",Toast.LENGTH_SHORT);
            }
            Toast.makeText(this, "初始化成功", Toast.LENGTH_SHORT).show();
        }
    }
}
