package com.example.testsystem;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static java.security.AccessController.getContext;

/**
 * @author syl
 * @time 2019/10/19 20:26
 * @detail
 */
public class PersonCenterActivity extends AppCompatActivity {
    private ImageView hBack;
    private ImageView hHead;
    private RelativeLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        layout = (RelativeLayout) findViewById(R.id.title);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        hBack = findViewById(R.id.h_back);
        hHead = findViewById(R.id.h_head);

        //设置背景磨砂效果
        Glide.with(this).load(R.drawable.nav_icon)
                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(hBack);
        //设置圆形图像
        Glide.with(this).load(R.drawable.nav_icon)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(hHead);


    }
}
