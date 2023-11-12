package com.example.pp2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pp2.Adapters.GroupRowAdapter;
import com.example.pp2.AddActivities.AddGroupActivity;
import com.example.pp2.Entities.Group;
import com.example.pp2.R;
import com.example.pp2.ViewModels.GroupViewModel;

public class GroupsActivity extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";
    public static final String GROUP_TO_PARSE =
            "com.example.pp2.GROUP_TO_PARSE";
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
        String subject_name = getIntent().getStringExtra(SubjectActivity.SUB_TO_PARSE);
        setTitle("Журнал: " + subject_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add_group_button.setOnClickListener(view -> {
            Intent intent = new Intent(GroupsActivity.this, AddGroupActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject_name);
            startActivityForResult(intent, ADD_GROUP_REQUEST);
        });

        button_back.setOnClickListener(view -> {
            Intent intent = new Intent(GroupsActivity.this, SubjectActivity.class);
            startActivity(intent);
        });

        recyclerView_groups.setLayoutManager(new LinearLayoutManager(GroupsActivity.this));
        recyclerView_groups.setHasFixedSize(true);
        groupRowAdapter = new GroupRowAdapter();
        recyclerView_groups.setAdapter(groupRowAdapter);
        groupViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        groupViewModel.getAllGroups().observe(this, groups -> groupRowAdapter.setAllGroups(groups));
        groupRowAdapter.setOnItemClickListener(group -> {
            Intent intent = new Intent(GroupsActivity.this, StudentsActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject_name);
            intent.putExtra(GROUP_TO_PARSE, group.getGroup_name());
            startActivity(intent);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_GROUP_REQUEST && resultCode == RESULT_OK && data != null){
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