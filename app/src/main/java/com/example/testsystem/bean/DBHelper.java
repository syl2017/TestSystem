package com.example.testsystem.bean;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.testsystem.R;
import com.example.testsystem.model.MyFinally;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author syl
 * @time 2019/10/24 21:31
 * @detail
 */
public class DBHelper {
    Context context;
    private String DB_PATH = "kaoshi.db";
    private static final String TAG = "DataBaseReady";
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

    private void initFile() {// 把raw下的sq写进sd卡
        // 如果你的储存卡存在并且可用
        // 只有在SD卡状态为MEDIA_MOUNTED时/mnt/sdcard目录才是可读可写，并且可以创建目录及文件
        // 判断sd卡是否挂载
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 建立sd卡对象
            File dir = new File(MyFinally.FILE_PAPER_PATH);
            //如果没有此文件夹就创建
            if (!dir.exists()) {
                //创建
                dir.mkdirs();

            } else {
                Log.d(TAG, "sd已有");
            }
            // 实例化文件名对象
            File filePath = new File(MyFinally.FILE_PATH);// 建立sd卡下 的db文件
            // 如果文件不存在
            if (!filePath.exists()) {
//                不存在就把raw目录下的kaoshi文件通过流读出来，读出来是为了等会好写进去
                InputStream in = this.context.getResources().openRawResource(
                        R.raw.kaoshi);
//                对程序异常奔溃的捕捉 try catch
                try {
//                  FileOutputStream是文件输出流，是用于将数据写入File或 FileDescriptor的输出流
//                  实例化文件输入流对象（文件名）
                    FileOutputStream fileOutputStream = new FileOutputStream(MyFinally.FILE_PATH);// 得到输出流 文件夹下文件路径
//                    定义了一个byte类型的数组，数组长度为8192
                    byte[] buffer = new byte[8192];
                    int t = 0;
                    while ((t = in.read(buffer)) != -1) {// 半读边写
                        fileOutputStream.write(buffer, 0, t);
                    }
//                    开了流就一定要记得关  因为会占用系统资源
//                   文件读写完 关闭流
                    in.close();
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
