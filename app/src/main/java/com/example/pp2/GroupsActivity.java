package com.example.pp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity {

    Button add_group_button, button_back;
    RecyclerView recyclerView_groups;
    ArrayList<String>  group_id, group_name, group_year;
    GroupRowAdapter groupRowAdapter;

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
                startActivity(intent);
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //app_db = new App_Database(MainActivity.this);
        group_id = new ArrayList<>();
        group_name = new ArrayList<>();
        group_year = new ArrayList<>();

        //storeDataInArrays();

        groupRowAdapter = new GroupRowAdapter(GroupsActivity.this, this, group_id,
                group_name, group_year);
        recyclerView_groups.setAdapter(groupRowAdapter);
        recyclerView_groups.setLayoutManager(new LinearLayoutManager(GroupsActivity.this));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
}