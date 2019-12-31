package com.example.testsystem.bean;

import android.content.Context;
import android.os.Environment;
import android.util.Log;


import com.example.testsystem.model.MyFinally;
import com.example.testsystem.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by zhouweixiong on 2017/12/22.
 */

public class DBWriteSDCard {
    private static final String TAG = "ReleaseDataBase";
    private Context context;

    public DBWriteSDCard(Context context) {
        super();
        this.context = context;
    }


    public void whetherToWriteFile() {// 把raw下的sq写进sd卡
        //        如果你的储存卡存在并且可用
//        只有在SD卡状态为MEDIA_MOUNTED时/mnt/sdcard目录才是可读可写，并且可以创建目录及文件
        // 判断sd卡是否挂载
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {

            // 建立sd卡对象
            File file = new File(MyFinally.FILE_PAPER_PATH);
//            如果没有此文件夹就创建
            if (!file.exists()) {
//                创建
                file.mkdirs();
            } else {
                Log.d(TAG,"sd已有");
            }
//            实例化文件名对象
            File filePath = new File(MyFinally.FILE_PATH);// 建立sd卡下 的db文件
//             如果文件不存在
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
