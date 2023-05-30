package com.example.pp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupRowAdapter extends RecyclerView.Adapter<GroupRowAdapter.MyViewHolder>{

    Activity activity;
    private Context context;
    private ArrayList group_id, group_name, group_year;

    GroupRowAdapter(Activity activity, Context context, ArrayList group_id,
                    ArrayList group_name, ArrayList group_year){
        this.activity = activity;
        this.context = context;
        this.group_id = group_id;
        this.group_name = group_name;
        this.group_year = group_year;
    }


    @NonNull
    @Override
    public GroupRowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout_group, parent, false);
        return new GroupRowAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupRowAdapter.MyViewHolder holder, int position) {
        holder.group_id_txt.setText((String.valueOf(group_id.get(position))));
        holder.group_name_txt.setText((String.valueOf(group_name.get(position))));
        holder.group_year_txt.setText((String.valueOf(group_year.get(position))));
        holder.row_element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentsActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return group_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView group_id_txt, group_name_txt, group_year_txt;
        Button update_button;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            group_id_txt = itemView.findViewById(R.id.group_id_txt);
            group_name_txt = itemView.findViewById(R.id.group_name_txt);
            group_year_txt = itemView.findViewById(R.id.group_year_txt);
            update_button = itemView.findViewById(R.id.update_button);
            row_element = itemView.findViewById(R.id.row_element);
        }
    }
}
