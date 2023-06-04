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

public class MainRowAdapter extends RecyclerView.Adapter<MainRowAdapter.MyViewHolder> {
    private List<Subject> subjects = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public MainRowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_main, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRowAdapter.MyViewHolder holder, int position) {
        Subject currentSubject = subjects.get(position);
        holder.subject_id_txt.setText(String.valueOf(currentSubject.getSid()));
        holder.subject_name_txt.setText(currentSubject.getName());
        holder.subject_land_txt.setText(currentSubject.getLang());
        holder.subject_ide_txt.setText(currentSubject.getIde());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setSubjects(List<Subject> subjects){
        this.subjects = subjects;
        notifyDataSetChanged();
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(subjects.get(position));
                    }
                }
            });
        }
    }
    public interface  OnItemClickListener{
        void onItemClick(Subject subject);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
