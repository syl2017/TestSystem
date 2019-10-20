package com.example.testsystem;

import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author syl
 * @time 2019/10/20 10:12
 * @detail
 */
public class ItemPersonCenterActivity extends LinearLayout {

    private  TextView title_text;
    private  TextView main_text;
    private ImageView image_view;
    private  boolean isBottom;


    public ItemPersonCenterActivity(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.activity_person_center_item, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        title_text =  findViewById(R.id.title_text);
        main_text = findViewById(R.id.main_text);
        image_view =  findViewById(R.id.image_sign);
        isBottom = typedArray.getBoolean(R.styleable.ItemView_show_bottom_line, true);
        title_text.setText(typedArray.getString(R.styleable.ItemView_left_text));
        main_text.setText(typedArray.getString(R.styleable.ItemView_right_text));
        image_view.setBackgroundResource(typedArray.getResourceId(R.styleable.ItemView_left_icon,0));

    }

    public void setLeftIcon(int value) {
        Drawable drawable=getResources().getDrawable(value);
        title_text.setBackground(drawable);
    }

    //设置左侧标题文字
    public void setLeftTitle(String value) {
        title_text.setText(value);
    }

    //设置右侧描述文字
    public void setRightDesc(String value) {
        main_text.setText(value);
    }






}
