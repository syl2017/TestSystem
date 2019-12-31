package com.example.testsystem.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.testsystem.R;
import com.example.testsystem.control.TimeRecycleAdapter;
import com.example.testsystem.bean.GradeBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author syl
 * @time 2019/12/29 19:41
 * @detail
 */
public class ScoreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TimeRecycleAdapter adapter;
    private List<GradeBean> beans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        recyclerView = findViewById(R.id.recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        beans = DataSupport.findAll(GradeBean.class);
        adapter = new TimeRecycleAdapter(beans);
        recyclerView.setAdapter(adapter);
    }
}
