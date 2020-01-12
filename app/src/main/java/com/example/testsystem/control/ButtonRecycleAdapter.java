package com.example.testsystem.control;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testsystem.R;
import com.example.testsystem.bean.ButtonItem;
import com.example.testsystem.view.ErrorActivity;
import com.example.testsystem.view.MainActivity;
import com.example.testsystem.view.ScoreActivity;
import com.example.testsystem.view.ViewPageActivity;

import java.util.List;

/**
 * @author syl
 * @time 2020/1/12 19:04
 * @detail
 */
public class ButtonRecycleAdapter extends RecyclerView.Adapter<ButtonRecycleAdapter.ViewHolder> {
    private List<ButtonItem> list;
    private Context context;

    public ButtonRecycleAdapter(Context context, List<ButtonItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.button_recycler_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                try {


                    if (position == 0) {
                        Intent intent = new Intent(context, ViewPageActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(intent);
                    } else if (position == 1) {

                        Intent intent = new Intent(context, ErrorActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(intent);
                    } else if (position == 2) {
                        Intent intent = new Intent(context, ScoreActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(intent);
                    }
                } catch (Exception e) {
                   e.printStackTrace();
                }
//                ButtonItem item = list.get(position);
//                Toast.makeText(view.getContext(),"click view"+position+item.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
//                ButtonItem buttonItem = list.get(position);
//                Toast.makeText(view.getContext(),"click image"+position+buttonItem.getName(),Toast.LENGTH_SHORT).show();
                try {
                    if (position == 0) {
                        Intent intent = new Intent(context, ViewPageActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    } else if (position == 1) {

                        Intent intent = new Intent(context, ErrorActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(intent);
                    } else if (position == 2) {
                        Intent intent = new Intent(context, ScoreActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ButtonItem item = list.get(i);
        viewHolder.item_image.setImageResource(item.getImageId());
        viewHolder.item_name.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        private ImageView item_image;
        private TextView item_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            item_name = (TextView) itemView.findViewById(R.id.item_text);
            item_image = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }
}
