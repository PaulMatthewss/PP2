package com.example.pp2.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.pp2.AppDatabase;
import com.example.pp2.Interfaces.ISubjectsDao;
import com.example.pp2.R;
import com.example.pp2.Entities.Subject;
import com.example.pp2.UpdateActivities.UpdateSubjectActivity;

import java.util.ArrayList;
import java.util.List;

public class SubjectRowAdapter extends RecyclerView.Adapter<SubjectRowAdapter.MyViewHolder> {
    private List<Subject> subjects = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public SubjectRowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_subject, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectRowAdapter.MyViewHolder holder, int position) {
        Subject currentSubject = subjects.get(position);
        holder.subject_id_txt.setText(String.valueOf(currentSubject.getSid()));
        holder.subject_name_txt.setText(currentSubject.getName());
        holder.subject_land_txt.setText(currentSubject.getLangId());
        holder.subject_ide_txt.setText(currentSubject.getIdeId());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                AppDatabase db = Room.databaseBuilder(holder.subject_id_txt.getContext(),
                        AppDatabase.class, "app_database").allowMainThreadQueries().build();
                ISubjectsDao iSubjectsDao = db.iSubjectsDao();;
                iSubjectsDao.deleteSubject(subjects.get(position));
                subjects.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(new Intent(holder.updateBtn.getContext(), UpdateSubjectActivity.class));
                intent.putExtra("subjectId", String.valueOf(subjects.get(position).getSid()));
                intent.putExtra("subjectName", subjects.get(position).getName());
                holder.updateBtn.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSubjects(List<Subject> subjects){
        this.subjects = subjects;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subject_id_txt, subject_name_txt, subject_land_txt, subject_ide_txt;
        ImageButton updateBtn, deleteBtn;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            updateBtn = itemView.findViewById(R.id.updateBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            subject_id_txt = itemView.findViewById(R.id.subject_id_txt);
            subject_name_txt = itemView.findViewById(R.id.subject_name_txt);
            subject_land_txt = itemView.findViewById(R.id.subject_land_txt);
            subject_ide_txt = itemView.findViewById(R.id.subject_ide_txt);
            row_element = itemView.findViewById(R.id.row_element);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(subjects.get(position));
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
