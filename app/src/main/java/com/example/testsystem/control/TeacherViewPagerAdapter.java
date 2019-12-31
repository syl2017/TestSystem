package com.example.testsystem.control;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testsystem.R;
import com.example.testsystem.bean.GradeBean;
import com.example.testsystem.bean.QuestionBean;
import com.example.testsystem.bean.UserBean;
import com.example.testsystem.view.TeacherActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author syl
 * @time 2019/12/30 16:34
 * @detail
 */
public class TeacherViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    public List<UserBean> userBeanList;
    public List<GradeBean> gradeBeanList;
    public List<QuestionBean> questionBeanList;
    private ViewHolder viewHolder;


    public TeacherViewPagerAdapter(Context mContext, List<UserBean> userBeanList, List<GradeBean> gradeBeanList, List<QuestionBean> questionBeanList) {
        this.mContext = mContext;
        this.userBeanList = userBeanList;
        this.gradeBeanList = gradeBeanList;
        this.questionBeanList = questionBeanList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.teacher_viewpager_item, null);
        viewHolder = new ViewHolder();
        viewHolder.textContent = view.findViewById(R.id.text_content);
        viewHolder.listView = view.findViewById(R.id.teacher_listview);

        LoadData(position);//加载数据
        container.addView(view);
        return view;

    }

    private void LoadData(int position) {
        if (position == 0) {
            viewHolder.textContent.setText("所有用户信息(用户名、注册时间、密码)");

            UserListAdapter adapter = new UserListAdapter(mContext, R.layout.activity_recycler_item, userBeanList);
            viewHolder.listView.setAdapter(adapter);
        }
        if (position == 1) {
            viewHolder.textContent.setText("题库中所有题");
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i = 0; i < questionBeanList.size(); i++) {

                arrayList.add(i+1+"."+questionBeanList.get(i).getQuestion());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,arrayList);
            viewHolder.listView.setAdapter(adapter);

        }
        if (position == 2) {
            viewHolder.textContent.setText("所有用户答题情况");
            GradeListAdapter adapter = new GradeListAdapter(mContext, R.layout.activity_recycler_item, gradeBeanList);
            viewHolder.listView.setAdapter(adapter);
        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    public class ViewHolder {
        public TextView textContent;
        public ListView listView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    class UserListAdapter extends ArrayAdapter<UserBean> {

        private  int resourceId;

        public UserListAdapter(@NonNull Context context, int resource, @NonNull List<UserBean> objects) {
            super(context, resource, objects);
            resourceId=resource;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            UserBean userBean=getItem(position);
            View view;
            if (convertView == null) {

                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            } else {
                view=convertView;
            }
            TextView userName =view.findViewById(R.id.thing_name);
            userName.setText(userBean.getUserName());
            TextView sginTime=view.findViewById(R.id.thing_nextRemindTime);
            sginTime.setText(userBean.getUserSignTime());
            TextView password_Text = view.findViewById(R.id.count_type_label);
            password_Text.setText("密码");
            TextView password = view.findViewById(R.id.count_time);
            password.setText(userBean.getUserPassword());
            TextView hidenText = view.findViewById(R.id.count_time_label);
            hidenText.setVisibility(View.INVISIBLE);
            return view;

        }
    }
    class GradeListAdapter extends ArrayAdapter<GradeBean> {

        private  int resourceId;

        public GradeListAdapter(@NonNull Context context, int resource, @NonNull List<GradeBean> objects) {
            super(context, resource, objects);
            resourceId=resource;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            GradeBean gradeBean=getItem(position);
            View view;
            if (convertView == null) {

                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            } else {
                view=convertView;
            }
            TextView userName =view.findViewById(R.id.thing_name);
            userName.setText(gradeBean.getUsername());
            TextView sginTime=view.findViewById(R.id.thing_nextRemindTime);
            sginTime.setText(gradeBean.getAnswerTime());
            TextView password_Text = view.findViewById(R.id.count_type_label);
            password_Text.setText("分数");
            TextView password = view.findViewById(R.id.count_time);
            password.setText(gradeBean.getGrade()+"");
            TextView hidenText = view.findViewById(R.id.count_time_label);
            hidenText.setVisibility(View.INVISIBLE);
            return view;

        }
    }

}

