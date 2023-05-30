package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentsActivity extends AppCompatActivity {

    Button add_student_button, button_back;
    RecyclerView recyclerView_students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        recyclerView_students = findViewById(R.id.recyclerView_students);
        add_student_button = findViewById(R.id.AddGroup_Button);
        button_back = findViewById(R.id.Button_Back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsActivity.this, GroupsActivity.class);
                startActivity(intent);
            }
        });
        add_student_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });
    }
}