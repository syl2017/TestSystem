package com.example.testsystem;

import android.os.Environment;

import java.io.File;

/**
 * Created by syl on 2017/12/21.
 */

/**
 * 定义一些常量
 * 一些表名和字段名
 * 不可修改字段名，否则会得不到数据
 * 在res目录下面有一个raw文件夹，raw里面有一个数据表
 * 如果要android studio查看此表，就去下载一个插件 database
 */

public class MyFinally {
    public static final String TABLE_NAME = "t_question";//表名
    public static final String TABLE_SOUCANG_NAME = "t_soucang";//表名
    //    字段名
    public static final String T01_COLUMN_ID = "question_id";
    public static final String T01_COLUMN_OPTION_TYPE = "option_type";
    public static final String T01_COLUMN_SOU_TYPE = "sou_type";
    public static final String T01_COLUMN_ERROR_TYPE = "error_type";
    //Environment.getExternalStorageDirectory()  获得根路径
//File.separator是用来分隔同一个路径字符串中的目录的，例如：
//    C:\Program Files\Common Files
//    就是指“\”
    public static final String FILE_PAPER_PATH = Environment.getExternalStorageDirectory() + File.separator + "kaoshi";
    //  文件的详细地址，找到此文件
//    FILE_PAPER_PATH 是第31行的地址，拼起来就是详细的文件地址和文件名
    public static final String FILE_PATH = FILE_PAPER_PATH + File.separator + "kaoshi.db";
}