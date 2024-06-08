package com.example.pp2.Adapters;

import android.annotation.SuppressLint;
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
import com.example.pp2.Entities.Group;
import com.example.pp2.Interfaces.IGroupDao;
import com.example.pp2.Interfaces.ISubjectsDao;
import com.example.pp2.R;

import java.util.ArrayList;
import java.util.List;

public class GroupRowAdapter extends RecyclerView.Adapter<GroupRowAdapter.MyViewHolder>{

    private List<Group> groups = new ArrayList<>();

    private OnItemClickListener listener;

    @NonNull
    @Override
    public GroupRowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_group, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupRowAdapter.MyViewHolder holder, int position) {
        Group currentGroup = groups.get(position);
        holder.group_name_txt.setText(currentGroup.getGroup_name());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                AppDatabase db = Room.databaseBuilder(holder.group_name_txt.getContext(),
                        AppDatabase.class, "app_database").allowMainThreadQueries().build();
                IGroupDao iGroupDao = db.iGroupDao();;
                iGroupDao.deleteGroup(groups.get(position));
                groups.remove(position);
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
        return groups.size();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setAllGroups(List<Group> groups){
        this.groups = groups;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView group_id_txt, group_name_txt;
        ImageButton updateBtn, deleteBtn;
        LinearLayout row_element;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            group_id_txt = itemView.findViewById(R.id.group_id_txt);
            group_name_txt = itemView.findViewById(R.id.group_name_txt);
            updateBtn = itemView.findViewById(R.id.updateBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            row_element = itemView.findViewById(R.id.row_element);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(groups.get(position));
                }
            });
        }
    }
    public interface  OnItemClickListener{
        void onItemClick(Group group);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
