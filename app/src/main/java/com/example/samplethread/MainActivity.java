package com.example.samplethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//쓰레드는 핸들러 객체 안에 있는 Message객체를 이용해 메세지를 전달한다.
public class MainActivity extends AppCompatActivity {
    TextView textView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            BackgroundThread t = new BackgroundThread();
            t.start();
        });
    }

    class BackgroundThread extends Thread {
        int value = 0;
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { }
                value++;
                Log.d("Thread", "value: " + value);

                runOnUiThread(()-> {
                    textView.setText("value값: "+value);
                });
            }
        }
    }
}