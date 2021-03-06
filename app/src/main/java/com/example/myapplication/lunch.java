package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class lunch extends AppCompatActivity {

    TextView lunch_text;
    Button good_lunch;
    Button bad_lunch;
    TextToSpeech tts;

    communication communication = new communication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);

        lunch_text = (TextView) findViewById(R.id.lunch_text);
        good_lunch = findViewById(R.id.good_lunch);
        bad_lunch = findViewById(R.id.bad_lunch);

        tts= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tts.speak(lunch_text.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        }, 100);

        good_lunch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                communication.up_A();
                communication.get_up("1");

                //점심 잘 먹었으니깐 상태 up?
                finish();
            }
        });

        bad_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), lunch_problem.class);
                startActivity(intent);
                finish();
            }
        });
    }
}