package com.example.testsystem.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testsystem.R;
import com.example.testsystem.bean.ErrorQuestionInfo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author syl
 * @time 2019/12/27 20:55
 * @detail
 */
public class ErrorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        ListView listView = (ListView) findViewById(R.id.listview);
        List<ErrorQuestionInfo> all = DataSupport.findAll(ErrorQuestionInfo.class);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {

            arrayList.add(i+1+"."+all.get(i).getQuestionName());
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);


        listView.setAdapter(adapter);

    }
}
