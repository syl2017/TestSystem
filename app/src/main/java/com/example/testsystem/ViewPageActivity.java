package com.example.testsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testsystem.bean.QuestionBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


/**
 * @author syl
 * @time 2019/9/14 16:09
 * @detail
 */
public class ViewPageActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    private ViewPager mViewPager;
    private DBHelper dbHelper;
    private ArrayList<String> list;
    private TextView activityPrepareTestQuestion;
    private int viewpagerIndex = 0;
    private ArrayList<View> viewpagerList;
    private List<QuestionBean> all;
    private View test_upLayout;
    private View test_totalLayout;
    private View test_errorLayout;
    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        username = getSharedPreferences("UserDataRecord", MODE_PRIVATE).getString("username", "");


//
        Log.e("count", DataSupport.count(QuestionBean.class) + "");
        if (DataSupport.count(QuestionBean.class) == 0) {
            dbHelper = new DBHelper(this);
        }
        Log.e("count", DataSupport.count(QuestionBean.class) + "");
        all = DataSupport.findAll(QuestionBean.class);
//        QuestionBean first = DataSupport.findFirst(QuestionBean.class);
//        Log.d(TAG, "onCreate: " + first);
//        list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list.add("第一题" + i);
//        }
        viewpagerList = new ArrayList<>();
        init();

    }

    public void setCurrentView(int index) {
        mViewPager.setCurrentItem(index);
    }

    private void init() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new ExamPagerAdapter(this, all, username));


    }


}
