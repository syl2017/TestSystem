package com.example.testsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.testsystem.bean.QuestionBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;


/**
 * @author syl
 * @time 2019/9/14 16:09
 * @detail
 */
public class ViewPageActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    private ViewPager mViewPager;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        dbHelper =new DBHelper(this);
        QuestionBean first = DataSupport.findFirst(QuestionBean.class);
        Log.d(TAG, "onCreate: "+first);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("第一题" + i);
        }
        mViewPager.setAdapter(new ExamPagerAdapter(this,list));
    }

}
