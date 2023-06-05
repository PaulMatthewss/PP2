package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddLessonActivity extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";
    public static final String GROUP_TO_PARSE =
            "com.example.pp2.GROUP_TO_PARSE";
    public static final String STUD_TO_PARSE =
            "com.example.pp2.STUD_TO_PARSE";
    Button Cancel_Button, Submit_Button;
    EditText grade;
    TextView lesson_num, lesson_type, lesson_date, lesson_subject, lesson_group, lesson_stud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);
        lesson_num = findViewById(R.id.lesson_num);
        lesson_type = findViewById(R.id.lesson_type);
        lesson_date = findViewById(R.id.lesson_date);
        lesson_subject = findViewById(R.id.lesson_subject);
        lesson_group = findViewById(R.id.lesson_group);
        lesson_stud = findViewById(R.id.lesson_stud);
        grade = findViewById(R.id.grade);
        Cancel_Button = findViewById(R.id.Cancel_Button);
        Submit_Button = findViewById(R.id.Submit_Button);
        String subject_name = getIntent().getStringExtra(StudentsActivity.SUB_TO_PARSE);
        String group_name = getIntent().getStringExtra(StudentsActivity.GROUP_TO_PARSE);
        String student_number = getIntent().getStringExtra(StudentsActivity.STUD_TO_PARSE);
        lesson_subject.setText(subject_name);
        lesson_group.setText(group_name);
        lesson_stud.setText(student_number);
        Cancel_Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, LessonsActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject_name);
            intent.putExtra(GROUP_TO_PARSE, group_name);
            intent.putExtra(STUD_TO_PARSE, student_number);
            startActivity(intent);
        });
    }
}