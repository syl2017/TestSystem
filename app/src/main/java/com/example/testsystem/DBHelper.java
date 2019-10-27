package com.example.testsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.testsystem.bean.QuestionBean;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * @author syl
 * @time 2019/10/24 21:31
 * @detail
 */
public class DBHelper {
    Context context;
    private String DB_PATH = "kaoshi.db";

    private String DB_NAME = "/data/user/0/com.example.testsystem/databases/";
    private SQLiteDatabase db;
    public DBHelper(Context context) {
        this.context = context;
        initFile();
        db = SQLiteDatabase.openDatabase(MyFinally.FILE_PATH,
                null, SQLiteDatabase.OPEN_READWRITE);
        DataSupport.saveAll(getQuestion());//把查到的数据保存到LitePal中，方便使用查询

    }

    public List<QuestionBean> getQuestion() {
        List<QuestionBean> list = new ArrayList<>();
        //执行sql语句
        Cursor cursor = db.rawQuery("select * from t_question", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            //遍历
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                QuestionBean pdBean = new QuestionBean();
//                Log.d(TAG, "getQuestion: "+cursor.getInt(cursor.getColumnIndex("question_id")));

                pdBean.setQuestion_id(cursor.getInt(cursor.getColumnIndex("question_id")));//题目内容
                pdBean.setMedia_type(cursor.getInt(cursor.getColumnIndex("media_type")));
                pdBean.setLabel(cursor.getString(cursor.getColumnIndex("label")));//题目内容
                pdBean.setQuestion(cursor.getString(cursor.getColumnIndex("question")));//题目内容
                pdBean.setMedia_content(cursor.getBlob(cursor.getColumnIndex("media_content")));//题目内容
                pdBean.setAnswer(cursor.getString(cursor.getColumnIndex("answer")));
                pdBean.setOption_a(cursor.getString(cursor.getColumnIndex("option_a")));//B答案
                pdBean.setOption_b(cursor.getString(cursor.getColumnIndex("option_b")));//正确答案
                pdBean.setOption_c(cursor.getString(cursor.getColumnIndex("option_c")));//正确答案
                pdBean.setOption_d(cursor.getString(cursor.getColumnIndex("option_d")));//正确答案
                pdBean.setExplain(cursor.getString(cursor.getColumnIndex("explain")));//题目的长度
                list.add(pdBean);
            }
        }
        return list;

    }

    private void initFile() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File dir = new File(MyFinally.FILE_PAPER_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }
            try {
                InputStream is = context.getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
