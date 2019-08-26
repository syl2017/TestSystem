package com.example.testsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.testsystem.ui.CountPlan;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CountPlan[] countPlans = {
            new CountPlan("考研", "2019年12月12日", "109")
    };
    private List<CountPlan> countPlanList=new ArrayList<>();
    private TimeRecycleAdapter timeRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        countPlanList.add(countPlans[0]);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        TimeRecycleAdapter adapter = new TimeRecycleAdapter(countPlanList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.title_setting) {

        }

        return true;
    }
}
