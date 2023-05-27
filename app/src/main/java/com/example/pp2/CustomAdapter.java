package com.example.pp2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private final IRecycleView iRecycleView;
    private Context context;
    private ArrayList group_id, group_name, group_year;

    CustomAdapter(Context context, ArrayList group_id, ArrayList group_name, ArrayList group_year,
                  IRecycleView iRecycleView){
        this.context = context;
        this.group_id = group_id;
        this.group_name = group_name;
        this.group_year = group_year;
        this.iRecycleView = iRecycleView;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_add_group, parent, false);
        return new MyViewHolder(view, iRecycleView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.group_name_txt.setText((String.valueOf(group_name.get(position))));
        holder.group_year_txt.setText((String.valueOf(group_year.get(position))));
    }

    @Override
    public int getItemCount() {
        return group_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView group_name_txt, group_year_txt;
        int position;
        public MyViewHolder(@NonNull View itemView, IRecycleView iRecycleView) {
            super(itemView);
            group_name_txt = itemView.findViewById(R.id.group_name_txt);
            group_year_txt = itemView.findViewById(R.id.group_year_txt);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.d("demo", "onClick: item clicked " + position + "");
                    if (CustomAdapter.this.iRecycleView != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            CustomAdapter.this.iRecycleView.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
