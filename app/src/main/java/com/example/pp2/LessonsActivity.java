package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LessonsActivity extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";
    public static final String GROUP_TO_PARSE =
            "com.example.pp2.GROUP_TO_PARSE";
    public static final String STUD_TO_PARSE =
            "com.example.pp2.STUD_TO_PARSE";
    public static final int ADD_LESSON_REQUEST = 1;
    Button Add_Button, Back_Button;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        Add_Button = findViewById(R.id.Add_Button);
        Back_Button = findViewById(R.id.Button_Back);
        recyclerView = findViewById(R.id.recyclerView);
        String subject_name = getIntent().getStringExtra(StudentsActivity.SUB_TO_PARSE);
        String group_name = getIntent().getStringExtra(StudentsActivity.GROUP_TO_PARSE);
        String student_number = getIntent().getStringExtra(StudentsActivity.STUD_TO_PARSE);
        setTitle(subject_name + ": " + group_name + ", " + student_number);
        Back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonsActivity.this, StudentsActivity.class);
                intent.putExtra(SUB_TO_PARSE, subject_name);
                intent.putExtra(GROUP_TO_PARSE, group_name);
                startActivity(intent);
            }
        });
        Add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessonsActivity.this, AddLessonActivity.class);
                intent.putExtra(SUB_TO_PARSE, subject_name);
                intent.putExtra(GROUP_TO_PARSE, group_name);
                intent.putExtra(STUD_TO_PARSE, student_number);
                startActivityForResult(intent, ADD_LESSON_REQUEST);
            }
        });
    }
}