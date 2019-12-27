package com.example.testsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testsystem.bean.ErrorQuestionInfo;
import com.example.testsystem.bean.QuestionBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * @author syl
 * @time 2019/9/14 16:17
 * @detail
 */
public class ExamPagerAdapter extends PagerAdapter {

    private ViewPageActivity mContext;
    private List<?> all;
    String isCorrect = MyFinally.isCorrect;//1对，0错
    public int errortopicNum = 0;//错题计数器
    private ErrorQuestionInfo errorQuestionInfo;
    private String username;//用户名
    private ViewHolder holder;


    private int count = 1;


    public ExamPagerAdapter(ViewPageActivity context, List<?> list, String username) {
        mContext = context;
        all = list;
        this.username = username;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = View.inflate(mContext, R.layout.examing_viewpager_item, null);

        InitView(view);  //绑定视图
        LoadData(position);//对数据数据加载
        ListenerMethod(position);//视图事件监听

        //是否最后一页，若是，则提交结果，判断是否有未答题
        if (position == getCount() - 1) {
            holder.nextText.setText("提交");

        }
        container.addView(view);
        return view;


    }

    private void ListenerMethod(final int position) {
        holder.layoutA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckOptionAndSave(position, "1");//通过点击选项，判断是否正确，将结果存入数据库


            }
        });
        holder.layoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CheckOptionAndSave(position, "2");


            }
        });
        holder.layoutC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CheckOptionAndSave(position, "3");

            }
        });
        holder.layoutD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CheckOptionAndSave(position, "4");

            }
        });
        holder.previousBtn.setOnClickListener(new LinearOnClickListener(position - 1, false, position, holder));
        holder.nextBtn.setOnClickListener(new LinearOnClickListener(position + 1, true, position, holder));

    }


    private void LoadData(int position) {
        holder.testQuestion.setText(((QuestionBean) all.get(position)).getLabel() + ((QuestionBean) all.get(position)).getQuestion());
        holder.text_a.setText(((QuestionBean) all.get(position)).getOption_a());
        holder.text_b.setText(((QuestionBean) all.get(position)).getOption_b());
        holder.text_c.setText(((QuestionBean) all.get(position)).getOption_c());
        holder.text_d.setText(((QuestionBean) all.get(position)).getOption_d());
        holder.totalText.setText(position + 1 + "/" + getCount());

    }

    private void InitView(View view) {
        holder = new ViewHolder();
        holder.testQuestion = (TextView) view.findViewById(R.id.activity_prepare_test_question);
        holder.text_a = (TextView) view.findViewById(R.id.vote_submit_select_text_a);
        holder.text_b = (TextView) view.findViewById(R.id.vote_submit_select_text_b);
        holder.text_c = (TextView) view.findViewById(R.id.vote_submit_select_text_c);
        holder.text_d = (TextView) view.findViewById(R.id.vote_submit_select_text_d);
        holder.nextImage = (ImageView) view.findViewById(R.id.menu_bottom_nextIV);
        holder.nextText = (TextView) view.findViewById(R.id.menu_bottom_nextTV);
        holder.previousBtn = (LinearLayout) view.findViewById(R.id.activity_prepare_test_upLayout);
        holder.nextBtn = (LinearLayout) view.findViewById(R.id.activity_prepare_test_nextLayout);
        holder.totalText = (TextView) view.findViewById(R.id.activity_prepare_test_totalTv);
        holder.layoutA = (LinearLayout) view.findViewById(R.id.activity_prepare_test_layout_a);
        holder.layoutB = (LinearLayout) view.findViewById(R.id.activity_prepare_test_layout_b);
        holder.layoutC = (LinearLayout) view.findViewById(R.id.activity_prepare_test_layout_c);
        holder.layoutD = (LinearLayout) view.findViewById(R.id.activity_prepare_test_layout_d);
        holder.ivA = (ImageView) view.findViewById(R.id.vote_submit_select_image_a);
        holder.ivB = (ImageView) view.findViewById(R.id.vote_submit_select_image_b);
        holder.ivC = (ImageView) view.findViewById(R.id.vote_submit_select_image_c);
        holder.ivD = (ImageView) view.findViewById(R.id.vote_submit_select_image_d);
    }

    private void CheckOptionAndSave(int position, String checkNumber) {
        errorQuestionInfo = new ErrorQuestionInfo();
        List<ErrorQuestionInfo> errorlist = DataSupport.findAll(ErrorQuestionInfo.class);

//        Log.e("quetion_id",question_id+"");
//        ErrorList = DataSupport.where("questionid = ?", question_id + "").find(ErrorQuestionInfo.class);
//        Log.e("ErrorList",ErrorList.toString());
        if (((QuestionBean) this.all.get(position)).getAnswer().contains(checkNumber)) {

            mContext.setCurrentView(position + 1);
            isCorrect = MyFinally.isCorrect;
        } else if (DataSupport.where("username = ? and questionid = ?", username, ((QuestionBean) this.all.get(position)).getQuestion_id() + "").count(ErrorQuestionInfo.class) > 0) {

//            Log.e("ErrorTime", count + "");
            isCorrect = MyFinally.isError;
            errortopicNum += 1;
            errorQuestionInfo.setCountError("" +2);
            mContext.setCurrentView(position + 1);
            errorQuestionInfo.updateAll("username = ? and questionid = ?", username, ((QuestionBean) this.all.get(position)).getQuestion_id() + "");
        } else {
            Log.e("username= ", username);
            Log.e("question_id", ((QuestionBean) this.all.get(position)).getQuestion_id() + "");
            Log.e("countData", DataSupport.where("username = ? and questionid = ?", username, ((QuestionBean) this.all.get(position)).getQuestion_id() + "").count(ErrorQuestionInfo.class) + "");
            isCorrect = MyFinally.isError;
            errortopicNum += 1;
            mContext.setCurrentView(position + 1);
            Log.e(TAG, "CheckOptionAndSave: " + username);
            errorQuestionInfo.setUserName(username);
            errorQuestionInfo.setQuestionName(((QuestionBean) this.all.get(position)).getQuestion());
            errorQuestionInfo.setOptionA(((QuestionBean) this.all.get(position)).getOption_a());
            errorQuestionInfo.setOptionB(((QuestionBean) this.all.get(position)).getOption_b());
            errorQuestionInfo.setOptionC(((QuestionBean) this.all.get(position)).getOption_c());
            errorQuestionInfo.setOptionD(((QuestionBean) this.all.get(position)).getOption_d());
            errorQuestionInfo.setQuestionAnswer((((QuestionBean) this.all.get(position)).getAnswer()));
            errorQuestionInfo.setQuestionId(((QuestionBean) this.all.get(position)).getQuestion_id());

            errorQuestionInfo.setCountError("" + 1);


            errorQuestionInfo.save();

        }
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


    public class ViewHolder {
        TextView questionType;
        TextView question;
        LinearLayout previousBtn, nextBtn, errorBtn;
        TextView nextText;
        TextView totalText;
        ImageView nextImage;
        LinearLayout wrongLayout;
        TextView explaindetailTv;
        LinearLayout layoutA;
        LinearLayout layoutB;
        LinearLayout layoutC;
        LinearLayout layoutD;
        LinearLayout layoutE;
        ImageView ivA;
        ImageView ivB;
        ImageView ivC;
        ImageView ivD;
        ImageView ivE;
        TextView tvA;
        TextView tvB;
        TextView tvC;
        TextView tvD;
        TextView tvE;
        ImageView ivA_;
        ImageView ivB_;
        ImageView ivC_;
        ImageView ivD_;
        ImageView ivE_;
        private TextView testQuestion;
        private TextView text_a;
        private TextView text_b;
        private TextView text_c;
        private TextView text_d;
    }

    private class LinearOnClickListener implements View.OnClickListener {
        private int mPosition;
        private int mPosition1;
        private boolean mIsNext;
        private ViewHolder viewHolder;

        public LinearOnClickListener(int position, boolean mIsNext, int position1, ViewHolder viewHolder) {
            mPosition = position;
            mPosition1 = position1;
            this.viewHolder = viewHolder;
            this.mIsNext = mIsNext;
        }

        @Override
        public void onClick(View view) {
            mContext.setCurrentView(mPosition);
            if (mPosition == getCount()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(false);
                builder.setMessage("总共题目为 " + getCount() + "\n正确为 " + (getCount() - errortopicNum) + "\n错误为 " + errortopicNum);

                builder.setPositiveButton("好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mContext.finish();
                    }
                });
                builder.show();


            }
        }
    }
}
