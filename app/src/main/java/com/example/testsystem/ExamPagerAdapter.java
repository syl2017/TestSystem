package com.example.testsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author syl
 * @time 2019/9/14 16:17
 * @detail
 */
public class ExamPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mData;
    private TextView testQuestion;

    public ExamPagerAdapter(Context context, List<String> list) {
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.examing_viewpager_item, null);
        testQuestion = (TextView) view.findViewById(R.id.activity_prepare_test_question);
        testQuestion.setText(mData.get(position));
        container.addView(view);
        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
