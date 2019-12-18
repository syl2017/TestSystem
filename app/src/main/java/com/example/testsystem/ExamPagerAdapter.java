package com.example.testsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testsystem.bean.QuestionBean;

import org.litepal.crud.DataSupport;
import org.w3c.dom.Text;

import java.util.List;

/**
 * @author syl
 * @time 2019/9/14 16:17
 * @detail
 */
public class ExamPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<?> all;
    private TextView testQuestion;
    private TextView text_a;
    private TextView text_b;
    private TextView text_c;
    private TextView text_d;

    public ExamPagerAdapter(Context context, List<?> list) {
        mContext = context;
        all = list;

    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.examing_viewpager_item, null);

        testQuestion = (TextView) view.findViewById(R.id.activity_prepare_test_question);
        text_a = (TextView) view.findViewById(R.id.vote_submit_select_text_a);
        text_b = (TextView) view.findViewById(R.id.vote_submit_select_text_b);
        text_c = (TextView) view.findViewById(R.id.vote_submit_select_text_c);
        text_d = (TextView) view.findViewById(R.id.vote_submit_select_text_d);
        testQuestion.setText(((QuestionBean)all.get(position)).getLabel()+((QuestionBean)all.get(position)).getQuestion());
        text_a.setText(((QuestionBean) all.get(position)).getOption_a());
        text_b.setText(((QuestionBean) all.get(position)).getOption_b());
        text_c.setText(((QuestionBean) all.get(position)).getOption_c());
        text_d.setText(((QuestionBean) all.get(position)).getOption_d());

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
