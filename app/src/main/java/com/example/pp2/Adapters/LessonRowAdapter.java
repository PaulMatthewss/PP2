package com.example.pp2.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pp2.Entities.Lesson;
import com.example.pp2.R;

import java.util.ArrayList;
import java.util.List;

public class LessonRowAdapter extends RecyclerView.Adapter<LessonRowAdapter.MyViewHolder> {
    private List<Lesson> lessons = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public LessonRowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_lesson, parent, false);
        return new LessonRowAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonRowAdapter.MyViewHolder holder, int position) {
        Lesson currentLesson = lessons.get(position);
        holder.lesson_date_txt.setText(String.valueOf(currentLesson.getLesson_date()));
        holder.lesson_type_txt.setText(String.valueOf(currentLesson.getLesson_type()));
        holder.lesson_grade.setText(String.valueOf(currentLesson.getGrade()));
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setLessons(List<Lesson> lessons){
        this.lessons = lessons;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lesson_id_txt, lesson_date_txt, lesson_type_txt, lesson_textbox, lesson_grade;
        Button update_button;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lesson_id_txt = itemView.findViewById(R.id.lesson_id_txt);
            lesson_date_txt = itemView.findViewById(R.id.lesson_date_txt);
            lesson_type_txt = itemView.findViewById(R.id.lesson_type_txt);
            lesson_textbox = itemView.findViewById(R.id.lesson_textbox);
            lesson_grade = itemView.findViewById(R.id.lesson_grade);
            update_button = itemView.findViewById(R.id.update_button);
            row_element = itemView.findViewById(R.id.row_element);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(lessons.get(position));
                }
            });
        }
    }
    public interface  OnItemClickListener{
        void onItemClick(Lesson lesson);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}