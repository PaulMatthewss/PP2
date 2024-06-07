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

import com.example.pp2.Entities.Group;
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
