package com.example.testsystem;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {
    private ImageView titleBack;
    private ImageView titleSetting;

    public TitleLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.activity_title, this);
        titleBack = findViewById(R.id.title_back);
        titleSetting = findViewById(R.id.title_setting);
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();

            }
        });
        titleSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Setting",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
