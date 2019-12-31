package com.example.testsystem.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.testsystem.R;
import com.example.testsystem.control.TeacherViewPagerAdapter;
import com.example.testsystem.bean.GradeBean;
import com.example.testsystem.bean.QuestionBean;
import com.example.testsystem.bean.UserBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author syl
 * @time 2019/12/30 15:53
 * @detail
 */
public class TeacherActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TeacherViewPagerAdapter adapter;
    private List<QuestionBean> questionBeanList;
    private List<GradeBean> gradeBeanList;
    private List<UserBean> userBeanList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        InitData();//加载数据库数据
        init();//初始视图

    }

    private void InitData() {
        questionBeanList = DataSupport.findAll(QuestionBean.class);
        gradeBeanList = DataSupport.findAll(GradeBean.class);
        userBeanList = DataSupport.findAll(UserBean.class);


    }

    private void init() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TeacherViewPagerAdapter(this, userBeanList, gradeBeanList,questionBeanList);
        mViewPager.setAdapter(adapter);


    }
}
