package com.example.testsystem;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.testsystem.bean.Bean_CardView;
import java.util.ArrayList;
import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private Bean_CardView[] beanCardViews = {
            new Bean_CardView("考研", "2019年12月12日", "109"), new Bean_CardView("软件考试", "2019年11月12日", "25")
    };
    private List<Bean_CardView> beanCardViewList = new ArrayList<>();
    private DrawerLayout mDrwaerlayout;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private NavigationView navView;
    private FloatingActionButton fab;
    private Button start_exam;
    private CircleImageView iconName;
    private TextView navHeadMail;
    private TextView navUserName;
    private SharedPreferences userDataRecord;
    private View headerView;
    private TextView head_mail;
    private TextView head_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        安卓动态申请权限  因为在高版本的安卓手机上跑不申请会闪退  记住同时在Manifest里配置权限
        //检查权限
        if(ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//            申请权限
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

        if(ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)!= PackageManager.PERMISSION_GRANTED){
//            申请权限
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS},1);
        }
        if(ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//            申请权限
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        headerView = navView.inflateHeaderView(R.layout.nav_header);
        head_mail = (TextView) headerView.findViewById(R.id.nav_head_mail);
        head_username = (TextView) headerView.findViewById(R.id.nav_head_username);
        setSupportActionBar(toolbar);
        for (int i = 0; i < beanCardViews.length; i++) {

            beanCardViewList.add(beanCardViews[i]);
        }

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        TimeRecycleAdapter adapter = new TimeRecycleAdapter(beanCardViewList);
        recyclerView.setAdapter(adapter);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
        navView.setCheckedItem(R.id.nav_start_exam);
        userDataRecord = getSharedPreferences("UserDataRecord", MODE_PRIVATE);
//
        head_mail.setText(userDataRecord.getString("email",""));
        head_username.setText(userDataRecord.getString("username",""));
        ClickEvent();
    }

    public void ClickEvent() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_start_exam:
                        break;
                    case R.id.nav_person:
                        Intent intent = new Intent(MainActivity.this, PersonCenterActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_grade:
                        break;
                    case R.id.nav_graph:
                        break;
                    case R.id.nav_sign_out: {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("退出账号？");
                        dialog.setMessage("是否登出该账号");

                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //空
                            }
                        });
                        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences.Editor userDataRecord = getSharedPreferences("UserDataRecord", MODE_PRIVATE).edit();
                                userDataRecord.clear();
                                userDataRecord.apply();
                                finish();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        dialog.show();

                        break;
                    }
                }
                mDrwaerlayout.closeDrawers();
                return true;

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "cliked", Toast.LENGTH_SHORT).show();
            }
        });
        start_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewPageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initView() {
        toolbar = findViewById(R.id.toolbar);
        mDrwaerlayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.recycle_view);
        navView = findViewById(R.id.nav_view);
        fab = findViewById(R.id.fab);
        start_exam = findViewById(R.id.start_exam);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrwaerlayout.openDrawer(GravityCompat.START);
                break;
            case R.id.title_setting:
                break;
        }

        return true;
    }


}
