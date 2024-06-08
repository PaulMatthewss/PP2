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
import com.example.pp2.Interfaces.IStudentDao;
import com.example.pp2.Interfaces.ISubjectsDao;
import com.example.pp2.R;
import com.example.pp2.Entities.Student;
import com.example.pp2.UpdateActivities.UpdateSubjectActivity;

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
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                AppDatabase db = Room.databaseBuilder(holder.student_num_txt.getContext(),
                        AppDatabase.class, "app_database").allowMainThreadQueries().build();
                IStudentDao iStudentDao = db.iStudentDao();;
                iStudentDao.deleteStudent(students.get(position));
                students.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                /*
                Intent intent = new Intent(new Intent(holder.updateBtn.getContext(), UpdateSubjectActivity.class));
                intent.putExtra("subjectId", String.valueOf(students.get(position).getSid()));
                intent.putExtra("subjectName", students.get(position).getName());
                holder.updateBtn.getContext().startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setStudents(List<Student> students){
        this.students = students;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView student_id_txt, student_name_txt, student_num_txt;
        ImageButton updateBtn, deleteBtn;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            updateBtn = itemView.findViewById(R.id.updateBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            student_id_txt = itemView.findViewById(R.id.student_id_txt);
            student_name_txt = itemView.findViewById(R.id.student_name_txt);
            student_num_txt = itemView.findViewById(R.id.student_num_txt);
            row_element = itemView.findViewById(R.id.row_element);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(students.get(position));
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
