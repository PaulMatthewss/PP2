package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GroupsActivity extends AppCompatActivity {

    Button add_group_button, button_back;
    RecyclerView recyclerView_groups;

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

    }
}