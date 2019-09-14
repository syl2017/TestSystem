package com.example.testsystem;





import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testsystem.bean.CountPlan;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CountPlan[] countPlans = {
            new CountPlan("考研", "2019年12月12日", "109")
    };
    private List<CountPlan> countPlanList = new ArrayList<>();
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
        countPlanList.add(countPlans[0]);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        TimeRecycleAdapter adapter = new TimeRecycleAdapter(countPlanList);
        recyclerView.setAdapter(adapter);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
        navView.setCheckedItem(R.id.nav_call);
        ClickEvent();
    }

    public void ClickEvent() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
