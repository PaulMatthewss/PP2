package com.example.pp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GroupsActivity extends AppCompatActivity {
    public static final int ADD_GROUP_REQUEST = 1;
    Button add_group_button, button_back;
    RecyclerView recyclerView_groups;
    GroupRowAdapter groupRowAdapter;
    private GroupViewModel groupViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        add_group_button = findViewById(R.id.AddGroup_Button);
        button_back = findViewById(R.id.Button_Back);
        recyclerView_groups = findViewById(R.id.recyclerView_groups);

        add_group_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupsActivity.this, AddGroupActivity.class);
                startActivityForResult(intent, ADD_GROUP_REQUEST);
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView_groups.setLayoutManager(new LinearLayoutManager(GroupsActivity.this));
        recyclerView_groups.setHasFixedSize(true);
        groupRowAdapter = new GroupRowAdapter();
        recyclerView_groups.setAdapter(groupRowAdapter);
        groupViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        groupViewModel.getAllGroups().observe(this, new Observer<List<Group>>() {
            @Override
            public void onChanged(List<Group> groups) {
                groupRowAdapter.setAllGroups(groups);
            }
        });
        groupRowAdapter.setOnItemClickListener(new GroupRowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Group group) {
                Intent intent = new Intent(GroupsActivity.this, StudentsActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_GROUP_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddGroupActivity.EXTRA_NAME);
            int date = data.getIntExtra(AddGroupActivity.EXTRA_DATE, 2020);
            Group group = new Group(name, date);
            groupViewModel.insert(group);
            recreate();
            Toast.makeText(this, "Запись добавлена", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ошибка записи", Toast.LENGTH_SHORT).show();

        }
    }
}