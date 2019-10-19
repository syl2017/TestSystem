package com.example.testsystem;


import android.app.Person;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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
import android.widget.Toast;

import com.example.testsystem.bean.Bean_CardView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Bean_CardView[] beanCardViews = {
            new Bean_CardView("考研", "2019年12月12日", "109"),new Bean_CardView("软件考试","2019年11月12日","25")
    };
    private List<Bean_CardView> beanCardViewList = new ArrayList<>();
    private TimeRecycleAdapter timeRecycleAdapter;
    private DrawerLayout mDrwaerlayout;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private NavigationView navView;
    private FloatingActionButton fab;
    private Button start_exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
        navView.setCheckedItem(R.id.nav_person);
        ClickEvent();
    }

    public void ClickEvent() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrwaerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        navView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        start_exam = (Button) findViewById(R.id.start_exam);
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
