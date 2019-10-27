package com.example.testsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LitePal.getDatabase();
        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        loginButton = findViewById(R.id.btn_login);
        signupLink = findViewById(R.id.link_signup);
        remeberCheckBox = findViewById(R.id.remeberCheckBox);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String sharePreEmail = pref.getString("email", "");
            String sharePrePassword = pref.getString("password", "");
            emailText.setText(sharePreEmail);
            passwordText.setText(sharePrePassword);
            remeberCheckBox.setChecked(true);
        }


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "登录");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_Dialog);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("加载中");
        progressDialog.show();

        email = emailText.getText().toString();
        password = passwordText.getText().toString();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
//                        onLoginSuccess();
//                         onLoginFailed();
                        List<UserBean> userBeanList = DataSupport.findAll(UserBean.class);
                        if (userBeanList != null) {
                            for (UserBean bean : userBeanList) {
                                Log.d("Sign.Activity", bean.getUserEmail());

                                if (bean.getUserEmail().equals(email) && bean.getUserPassword().equals(password)) {
                                    name = bean.getUserName();
                                    flag = true;
                                    break;
                                }
                            }
                            Log.d("Sign", flag + "");

                        }
                        if (flag == true) {

                            onLoginSuccess();
                        } else {
                            onLoginFailed();

                        }
                        progressDialog.dismiss();
                    }
                }, 1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {


                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        editor = pref.edit();
        if (remeberCheckBox.isChecked()) {

            editor.putBoolean("remember_password", true);
            editor.putString("email", email);
            editor.putString("password", password);
        } else {
            editor.clear();
        }
        editor.apply();
        userDataRecord = getSharedPreferences("UserDataRecord", MODE_PRIVATE).edit();
        userDataRecord.putString("username", name);
        userDataRecord.putString("password", password);
        userDataRecord.putString("email", email);
        userDataRecord.apply();



        loginButton.setEnabled(true);
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "账号或者密码错误,登录失败", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("确认有效的邮箱格式");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("请输入4个到10个之之间的字符");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}

