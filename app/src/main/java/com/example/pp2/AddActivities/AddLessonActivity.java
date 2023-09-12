package com.example.pp2.AddActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pp2.Activities.LessonsActivity;
import com.example.pp2.R;

public class AddLessonActivity extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";
    public static final String GROUP_TO_PARSE =
            "com.example.pp2.GROUP_TO_PARSE";
    public static final String STUD_TO_PARSE =
            "com.example.pp2.STUD_TO_PARSE";
    public static final String EXTRA_LESSON_NUM =
            "com.example.pp2.EXTRA_LESSON_NUM";
    public static final String EXTRA_LESSON_TYPE =
            "com.example.pp2.EXTRA_LESSON_TYPE";
    public static final String EXTRA_LESSON_DATE =
            "com.example.pp2.EXTRA_LESSON_DATE";
    public static final String EXTRA_LESSON_SUBJECT =
            "com.example.pp2.EXTRA_LESSON_SUBJECT";
    public static final String EXTRA_LESSON_GROUP =
            "com.example.pp2.EXTRA_LESSON_GROUP";
    public static final String EXTRA_LESSON_STUDENT =
            "com.example.pp2.EXTRA_LESSON_STUDENT";
    public static final String EXTRA_LESSON_GRADE =
            "com.example.pp2.EXTRA_LESSON_GRADE";
    public static final String EXTRA_LESSON_CHECK =
            "com.example.pp2.EXTRA_LESSON_CHECK";
    Button Cancel_Button, Submit_Button;
    EditText grade;
    CheckBox lesson_checkBox;
    TextView lesson_num, lesson_type, lesson_date, lesson_subject, lesson_group, lesson_stud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);
        //connecting the elements
        lesson_num = findViewById(R.id.lesson_num);
        lesson_type = findViewById(R.id.lesson_type);
        lesson_date = findViewById(R.id.lesson_date);
        lesson_subject = findViewById(R.id.lesson_subject);
        lesson_group = findViewById(R.id.lesson_group);
        lesson_stud = findViewById(R.id.lesson_stud);
        grade = findViewById(R.id.grade);
        lesson_checkBox = findViewById(R.id.lesson_checkBox);
        Cancel_Button = findViewById(R.id.Cancel_Button);
        Submit_Button = findViewById(R.id.Submit_Button);
        //parsing stuff
        String subject_name = getIntent().getStringExtra(LessonsActivity.SUB_TO_PARSE);
        String group_name = getIntent().getStringExtra(LessonsActivity.GROUP_TO_PARSE);
        String student_number = getIntent().getStringExtra(LessonsActivity.STUD_TO_PARSE);
        lesson_subject.setText(subject_name);
        lesson_group.setText(group_name);
        lesson_stud.setText(student_number);
        //buttons
        lesson_checkBox.setOnClickListener(view -> {
            if(lesson_checkBox.isChecked()){
                grade.setHint("Оцените");
                grade.setEnabled(true);
            }
            if(!lesson_checkBox.isChecked()){
                grade.setText("");
                grade.setHint("н");
                grade.setEnabled(false);
            }
        });
        Cancel_Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, LessonsActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject_name);
            intent.putExtra(GROUP_TO_PARSE, group_name);
            intent.putExtra(STUD_TO_PARSE, student_number);
            startActivity(intent);
        });
        Submit_Button.setOnClickListener(view -> {
            if(grade.getText().toString().trim().isEmpty() && lesson_checkBox.isChecked()){
                Toast.makeText(AddLessonActivity.this, "Пожалуйста поставьте оценку", Toast.LENGTH_SHORT).show();
                return;
            }
            try{
                Integer g;
                if(grade.getText().toString().trim().isEmpty()){
                    g = null;
                }
                else {
                    g = Integer.parseInt(grade.getText().toString().trim());
                }
                Integer lesson_n = Integer.parseInt(lesson_num.getText().toString().trim());
                String lesson_t = lesson_type.getText().toString().trim();
                String lesson_d = lesson_date.getText().toString().trim();
                String lesson_s = lesson_subject.getText().toString().trim();
                String lesson_g = lesson_group.getText().toString().trim();
                Integer lesson_st = Integer.parseInt(lesson_stud.getText().toString().trim());
                boolean lesson_check = lesson_checkBox.isChecked();
                Intent data = new Intent();
                data.putExtra(SUB_TO_PARSE, subject_name);
                data.putExtra(GROUP_TO_PARSE, group_name);
                data.putExtra(STUD_TO_PARSE, student_number);
                data.putExtra(EXTRA_LESSON_NUM, lesson_n);
                data.putExtra(EXTRA_LESSON_TYPE, lesson_t);
                data.putExtra(EXTRA_LESSON_DATE, lesson_d);
                data.putExtra(EXTRA_LESSON_SUBJECT, lesson_s);
                data.putExtra(EXTRA_LESSON_GROUP, lesson_g);
                data.putExtra(EXTRA_LESSON_STUDENT, lesson_st);
                data.putExtra(EXTRA_LESSON_GRADE, g);
                data.putExtra(EXTRA_LESSON_CHECK, lesson_check);
                setResult(RESULT_OK, data);
                finish();
            }catch (Exception e){
                Toast.makeText(AddLessonActivity.this, "Ошибка: " + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}