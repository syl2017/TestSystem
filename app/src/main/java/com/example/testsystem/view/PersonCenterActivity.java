package com.example.testsystem.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.testsystem.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * @author syl
 * @time 2019/10/19 20:26
 * @detail
 */
public class PersonCenterActivity extends AppCompatActivity {
    private ImageView hBack;
    private ImageView hHead;
    private RelativeLayout layout;
    private ItemPersonCenterActivity personName;
    private ItemPersonCenterActivity personEmail;
    private ItemPersonCenterActivity personPassword;
    private SharedPreferences userDataRecord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        layout = findViewById(R.id.activity_title_include);
        personName = findViewById(R.id.person_name);
        personEmail = findViewById(R.id.person_email);
        personPassword = findViewById(R.id.person_password);
        hBack = findViewById(R.id.h_back);
        hHead = findViewById(R.id.h_head);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //设置背景磨砂效果
        Glide.with(this).load(R.drawable.nav_icon)
                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(hBack);
        //设置圆形图像
        Glide.with(this).load(R.drawable.nav_icon)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(hHead);

        userDataRecord = getSharedPreferences("UserDataRecord", MODE_PRIVATE);
        personEmail.setRightDesc(userDataRecord.getString("email",""));


        personName.setRightDesc(userDataRecord.getString("username",""));
        personPassword.setRightDesc(userDataRecord.getString("password",""));
    }
}
