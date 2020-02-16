package com.example.testsystem.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testsystem.R;
import com.example.testsystem.bean.UserBean;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author syl
 * @time 2019/10/16 22:25
 * @detail
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private TextView signupLink;
    private boolean flag = false;
    private String email;
    private String password;
    private CheckBox remeberCheckBox;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    private SharedPreferences.Editor userDataRecord;
    private String name;
    private Button teahch_login_button;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LitePal.getDatabase();//LitePal的数据库创建
        emailText = findViewById(R.id.input_email);//邮箱文本控件
        passwordText = findViewById(R.id.input_password);//密码文本控件
        loginButton = findViewById(R.id.btn_login);//登录按钮
        signupLink = findViewById(R.id.link_signup);//注册按钮
        remeberCheckBox = findViewById(R.id.remeberCheckBox);//记住密码复选框
        pref = PreferenceManager.getDefaultSharedPreferences(this);//SharePreference的对象
        teahch_login_button = findViewById(R.id.btn_teacher_login);//教师登录按钮

        boolean isRemember = pref.getBoolean("remember_password", false);//从SharePreference中拿到键的值
        if (isRemember) {//判断是否为真，是否记住密码
            String sharePreEmail = pref.getString("email", "");//拿到存在SharePreference文件的中邮箱值
            String sharePrePassword = pref.getString("password", "");//拿到文件中密码值
            emailText.setText(sharePreEmail);
            passwordText.setText(sharePrePassword);
            remeberCheckBox.setChecked(true);
        }


        loginButton.setOnClickListener(new View.OnClickListener() {//登录按钮的监听事件

            @Override
            public void onClick(View v) {

                login();//验证登录的方法
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {//注册按钮的监听事件

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);//跳转入注册界面
                startActivityForResult(intent, REQUEST_SIGNUP);//带值返回
            }
        });
        teahch_login_button.setOnClickListener(new View.OnClickListener() {//教师登录按钮
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TeacherActivity.class);//跳转教师页面
                startActivity(intent);
            }
        });
    }

    public void login() {
        Log.d(TAG, "登录");

        if (!validate()) { //登录是否合法 不合法跳转
            onLoginFailed();//不合法处理方法
            return;
        }

        loginButton.setEnabled(false);//设置按钮为不可点击模式

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_Dialog);//启用进度条动画
        progressDialog.setCancelable(false);//dialog弹出后会点击屏幕或物理返回键 dialog不消失
        progressDialog.setIndeterminate(true);//进度条采用不明确显示进度的‘模糊模式’，
        progressDialog.setMessage("加载中");//设置信息
        progressDialog.show();

        email = emailText.getText().toString();
        password = passwordText.getText().toString();


        new android.os.Handler().postDelayed(//使用Handler子线程
                new Runnable() {
                    public void run() {

                        List<UserBean> userBeanList = DataSupport.findAll(UserBean.class);//获得UserBean数据表中List的集合
                        if (userBeanList != null) { //判断是否为空
                            for (UserBean bean : userBeanList) { //遍历所有
//                                Log.d("Sign.Activity", bean.getUserEmail());

                                if (bean.getUserEmail().equals(email) && bean.getUserPassword().equals(password)) {//判断获取的文本是否符合数据库中已存数据
                                    name = bean.getUserName();//得到用户名
                                    flag = true;//标记置为真
                                    break;
                                }
                            }
//                            Log.d("Sign", flag + "");

                        }
                        if (flag == true) {

                            onLoginSuccess();
                        } else {
                            onLoginFailed();

                        }
                        progressDialog.dismiss();//进度条消失
                    }
                }, 1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//回调函数
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {


                // 结束当前活动自动登录
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // 禁用返回MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        editor = pref.edit();//调用Sharepreference的编辑模式
        if (remeberCheckBox.isChecked()) {//判断登录按钮是否被点击

            editor.putBoolean("remember_password", true);
            editor.putString("email", email);
            editor.putString("password", password);
        } else {
            editor.clear();//否则编译器清零
        }
        editor.apply();//应用结果变更
        userDataRecord = getSharedPreferences("UserDataRecord", MODE_PRIVATE).edit();//在开一个用户数据的临时存储Sharepreference
        userDataRecord.putString("username", name);
        userDataRecord.putString("password", password);
        userDataRecord.putString("email", email);
        userDataRecord.apply();//应用变更



        loginButton.setEnabled(true);//
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onLoginFailed() { //Toast提示
        Toast.makeText(getBaseContext(), "账号或者密码错误,登录失败", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);//登录按钮的点击复原
    }

    public boolean validate() {//输入文本是否符合格式判断
        boolean valid = true;//默认标志合法

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {//判断邮箱格式，正则
            emailText.setError("确认有效的邮箱格式");//设置提示信息
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {//密码长度判断
            passwordText.setError("请输入4个到10个之之间的字符");//设置提示信息
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}

