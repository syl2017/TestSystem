package com.example.testsystem.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testsystem.bean.DBHelper;
import com.example.testsystem.control.ExamPagerAdapter;
import com.example.testsystem.R;
import com.example.testsystem.bean.QuestionBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


/**
 * @author syl
 * @time 2019/9/14 16:09
 * @detail
 */
public class ViewPageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

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
    private boolean flag  ;
    private ExamPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        username = getSharedPreferences("UserDataRecord", MODE_PRIVATE).getString("username", "");



        if (DataSupport.count(QuestionBean.class) == 0) {
            dbHelper = new DBHelper(this);
        }

        all = DataSupport.findAll(QuestionBean.class);

        viewpagerList = new ArrayList<>();

        init();
        mViewPager.setOnPageChangeListener(this);

}

    public void setCurrentView(int index) {
        mViewPager.setCurrentItem(index);
    }

    private void init() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new ExamPagerAdapter(this, all, username);
        mViewPager.setAdapter(adapter);


    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {
        switch (i) {

            case ViewPager.SCROLL_STATE_DRAGGING:

                flag = false;
                break;

            case ViewPager.SCROLL_STATE_SETTLING:
                flag = true;
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                if (mViewPager.getCurrentItem() == mViewPager.getAdapter()
                        .getCount() - 1 && !flag) {
                    Log.e("onPagerScrollState", "" + adapter.getCount() + adapter.errortopicNum);


                    Toast.makeText(ViewPageActivity.this, "已经是最后一页",
                            Toast.LENGTH_LONG).show();
                }
                flag = true;
                break;
        }


    }


}
