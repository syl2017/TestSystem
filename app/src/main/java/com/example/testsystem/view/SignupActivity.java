package com.example.testsystem.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testsystem.R;
import com.example.testsystem.bean.UserBean;

import org.litepal.crud.DataSupport;

import java.util.Calendar;
import java.util.List;

/**
 * @author syl
 * @time 2019/10/16 22:46
 * @detail
 */
public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";


    private EditText nameText;
    private EditText emailText;
    private EditText passwordText;
    private Button signupButton;
    private TextView loginLink;
    private String name;
    private String email;
    private String password;
    private boolean flag = false;
    private SharedPreferences.Editor userDataRecord;
    private Button teacherButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nameText = findViewById(R.id.input_name);
        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        signupButton = findViewById(R.id.btn_signup);
        loginLink = findViewById(R.id.link_login);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });

    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.Theme_AppCompat_Dialog);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        name = nameText.getText().toString();
        email = emailText.getText().toString();
        password = passwordText.getText().toString();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        List<UserBean> userBeanList = DataSupport.findAll(UserBean.class);
                        if (userBeanList != null) {
                            for (UserBean bean : userBeanList) {
                                Log.d("Sign.Activity", bean.getUserEmail());

                                if ((bean.getUserEmail().equals(email))) {
//
                                    flag = true;
                                    break;
                                }
                            }
                            Log.d("Sign", flag + "");

                        }
                        if (flag == true) {
                            onSignupFailed();
                        } else {

                            onSignupSuccess();
                        }


//


                        progressDialog.dismiss();
                    }
                }, 1000);
    }


    public void onSignupSuccess() {
        Calendar c = Calendar.getInstance();
        UserBean userBean = new UserBean();
        userBean.setUserName(name);
        userBean.setUserEmail(email);
        userBean.setUserPassword(password);
        userBean.setUserSignTime(c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DATE));
        userBean.setUserTpye("学生");
        userBean.save();
        userDataRecord = getSharedPreferences("UserDataRecord", MODE_PRIVATE).edit();
        userDataRecord.putString("username", name);
        userDataRecord.putString("password", password);
        userDataRecord.putString("email", email);
        userDataRecord.apply();

        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 2) {
            nameText.setError("至少三位字符!");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("请确认为有效的邮箱地址!");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("注意,在4到10位字符之间!");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

}
