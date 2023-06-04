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
import java.util.List;

public class StudentRowAdapter extends RecyclerView.Adapter<StudentRowAdapter.MyViewHolder>{

    private List<Student> students = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public StudentRowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_student, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRowAdapter.MyViewHolder holder, int position) {
        Student currentStudent = students.get(position);
        holder.student_name_txt.setText(String.valueOf(currentStudent.getFio()));
        holder.student_num_txt.setText(String.valueOf(currentStudent.getStud_num()));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
    public void setStudents(List<Student> students){
        this.students = students;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView student_id_txt, student_name_txt, student_num_txt;
        Button update_button;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id_txt = itemView.findViewById(R.id.student_id_txt);
            student_name_txt = itemView.findViewById(R.id.student_name_txt);
            student_num_txt = itemView.findViewById(R.id.student_num_txt);
            update_button = itemView.findViewById(R.id.update_button);
            row_element = itemView.findViewById(R.id.row_element);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(students.get(position));
                    }
                }
            });
        }
    }
    public interface  OnItemClickListener{
        void onItemClick(Student student);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
