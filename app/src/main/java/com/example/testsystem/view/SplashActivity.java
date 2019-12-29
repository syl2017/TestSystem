package com.example.testsystem.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.testsystem.R;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private Thread thread=new Thread(this);
    private TextView runOut;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        thread.start();
        runOut = findViewById(R.id.run_out);
        runOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    thread.stop();
            }
        });


    }

    @Override
    public void run() {
        try {//使用线程进行休眠操作
            sleep(2000);//休眠5秒
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

