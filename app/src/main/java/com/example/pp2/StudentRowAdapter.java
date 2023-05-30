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

public class StudentRowAdapter extends RecyclerView.Adapter<StudentRowAdapter.MyViewHolder>{

    Activity activity;
    private Context context;
    private ArrayList student_id, student_name, student_gender;

    StudentRowAdapter(Activity activity, Context context, ArrayList student_id,
                    ArrayList student_name, ArrayList student_gender){
        this.activity = activity;
        this.context = context;
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_gender = student_gender;
    }


    @NonNull
    @Override
    public StudentRowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout_student, parent, false);
        return new StudentRowAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRowAdapter.MyViewHolder holder, int position) {
        holder.student_id_txt.setText((String.valueOf(student_id.get(position))));
        holder.student_name_txt.setText((String.valueOf(student_name.get(position))));
        holder.student_gender_txt.setText((String.valueOf(student_gender.get(position))));
        holder.row_element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return student_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView student_id_txt, student_name_txt, student_gender_txt;
        Button update_button;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id_txt = itemView.findViewById(R.id.student_id_txt);
            student_name_txt = itemView.findViewById(R.id.student_name_txt);
            student_gender_txt = itemView.findViewById(R.id.student_gender_txt);
            update_button = itemView.findViewById(R.id.update_button);
            row_element = itemView.findViewById(R.id.row_element);
        }
    }
}
