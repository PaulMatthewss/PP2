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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList subject_id, subject_name, subject_lang, subject_ide;

    Activity activity;

    CustomAdapter(Activity activity, Context context, ArrayList subject_id, ArrayList subject_name,
                  ArrayList subject_lang, ArrayList subject_ide){
        this.activity = activity;
        this.context = context;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.subject_lang = subject_lang;
        this.subject_ide = subject_ide;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.subject_id_txt.setText((String.valueOf(subject_id.get(position))));
        holder.subject_name_txt.setText((String.valueOf(subject_name.get(position))));
        holder.subject_land_txt.setText((String.valueOf(subject_lang.get(position))));
        holder.subject_ide_txt.setText((String.valueOf(subject_ide.get(position))));
        holder.update_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, UpdateSubjectActivity.class);
                intent.putExtra("id", String.valueOf(subject_id.get(position)));
                intent.putExtra("name", String.valueOf(subject_name.get(position)));
                intent.putExtra("lang", String.valueOf(subject_lang.get(position)));
                intent.putExtra("ide", String.valueOf(subject_ide.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
        holder.row_element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GroupsActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subject_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subject_id_txt, subject_name_txt, subject_land_txt, subject_ide_txt;
        Button update_button;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            subject_id_txt = itemView.findViewById(R.id.subject_id_txt);
            subject_name_txt = itemView.findViewById(R.id.subject_name_txt);
            subject_land_txt = itemView.findViewById(R.id.subject_land_txt);
            subject_ide_txt = itemView.findViewById(R.id.subject_ide_txt);
            update_button = itemView.findViewById(R.id.update_button);
            row_element = itemView.findViewById(R.id.row_element);
        }
    }
}
