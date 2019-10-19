package com.example.testsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.testsystem.bean.CountPlan;

import java.util.List;

public class TimeRecycleAdapter extends RecyclerView.Adapter<TimeRecycleAdapter.ViewHolder> {
    private Context mContext;
    private List<CountPlan> mPlan;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_recycler_item,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CountPlan countPlan = mPlan.get(i);
        viewHolder.thing_name.setText(countPlan.getThingContent());
        viewHolder.thing_nextRemindTime.setText(countPlan.getRemindTime());
        viewHolder.count_time.setText(countPlan.getCountTime());
    }

    @Override
    public int getItemCount() {
        return mPlan.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView thing_name;
        TextView thing_nextRemindTime;
        TextView count_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            cardView= (CardView) itemView.findViewById(R.id.);
            thing_name=itemView.findViewById(R.id.thing_name);
            thing_nextRemindTime=itemView.findViewById(R.id.thing_nextRemindTime);
            count_time = itemView.findViewById(R.id.count_time);

        }


    }

    public TimeRecycleAdapter(List<CountPlan> mPlan) {
        this.mPlan= mPlan;
    }
}
