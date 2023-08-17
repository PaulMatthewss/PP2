package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateLessonActivity extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";
    public static final String GROUP_TO_PARSE =
            "com.example.pp2.GROUP_TO_PARSE";
    public static final String STUD_TO_PARSE =
            "com.example.pp2.STUD_TO_PARSE";
    public static final String EXTRA_DATE =
            "com.example.pp2.EXTRA_DATE";
    public static final String EXTRA_TYPE =
            "com.example.pp2.EXTRA_TYPE";
    public static final String EXTRA_NUM =
            "com.example.pp2.EXTRA_NUM";
    public static final String EXTRA_GRADE =
            "com.example.pp2.EXTRA_GRADE";
    public static final String EXTRA_GROUP =
            "com.example.pp2.EXTRA_GROUP";
    public static final String EXTRA_STUD_NUM =
            "com.example.pp2.EXTRA_STUD_NUM";
    public static final String EXTRA_SUBJ =
            "com.example.pp2.EXTRA_SUBJ";
    public static final String EXTRA_CHECK =
            "com.example.pp2.EXTRA_CHECK";
    public static final String EXTRA_ID =
            "com.example.pp2.EXTRA_ID";
    Button Cancel_Button_update, Update_Button;
    EditText grade;
    CheckBox lesson_checkBox;
    TextView lesson_num, lesson_type, lesson_date, lesson_subject, lesson_group, lesson_stud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lesson);
        lesson_num = findViewById(R.id.lesson_num);
        lesson_type = findViewById(R.id.lesson_type);
        lesson_date = findViewById(R.id.lesson_date);
        lesson_subject = findViewById(R.id.lesson_subject);
        lesson_group = findViewById(R.id.lesson_group);
        lesson_stud = findViewById(R.id.lesson_stud);
        grade = findViewById(R.id.grade);
        lesson_checkBox = findViewById(R.id.lesson_checkBox);
        Update_Button = findViewById(R.id.Update_Button);
        Cancel_Button_update = findViewById(R.id.Cancel_Button_update);
        Intent intent = getIntent();
        String subject_name = intent.getStringExtra(LessonsActivity.SUB_TO_PARSE);
        String group_name = intent.getStringExtra(LessonsActivity.GROUP_TO_PARSE);
        String student_number = intent.getStringExtra(LessonsActivity.STUD_TO_PARSE);
        if(intent.hasExtra(EXTRA_ID)){
            String extra_date = intent.getStringExtra(LessonsActivity.EXTRA_DATE);
            String extra_type = intent.getStringExtra(LessonsActivity.EXTRA_TYPE);
            Integer extra_num = intent.getIntExtra(LessonsActivity.EXTRA_NUM, 0);
            Integer extra_grade = intent.getIntExtra(LessonsActivity.EXTRA_GRADE, 0);
            String extra_group = intent.getStringExtra(LessonsActivity.EXTRA_GROUP);
            Integer extra_stud_num = intent.getIntExtra(LessonsActivity.EXTRA_STUD_NUM, 0);
            String extra_subj = intent.getStringExtra(LessonsActivity.EXTRA_SUBJ);
            boolean extra_check = intent.getBooleanExtra(LessonsActivity.EXTRA_CHECK, false);
            lesson_subject.setText(extra_subj);
            lesson_group.setText(extra_group);
            lesson_stud.setText(extra_stud_num);
            lesson_num.setText(extra_num);
            lesson_type.setText(extra_type);
            lesson_date.setText(extra_date);
            grade.setText(extra_grade);
            if (extra_check){
                lesson_checkBox.setChecked(true);
            }else{
                lesson_checkBox.setChecked(false);
            }
        }else{
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
        setTitle("Редактирование");
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
        Cancel_Button_update.setOnClickListener(view -> {
            Intent previous_intent = new Intent(this, LessonsActivity.class);
            previous_intent.putExtra(SUB_TO_PARSE, subject_name);
            previous_intent.putExtra(GROUP_TO_PARSE, group_name);
            previous_intent.putExtra(STUD_TO_PARSE, student_number);
            startActivity(previous_intent);
        });
        Update_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(grade.getText().toString().trim().isEmpty() && lesson_checkBox.isChecked()){
                    Toast.makeText(UpdateLessonActivity.this, "Пожалуйста поставьте оценку", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }
}