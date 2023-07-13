package com.example.pp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
    LessonRowAdapter lessonRowAdapter;
    TextView accepted_works, all_grades;
    private LessonViewModel lessonViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        Add_Button = findViewById(R.id.Add_Button);
        Back_Button = findViewById(R.id.Button_Back);
        recyclerView = findViewById(R.id.recyclerView);
        accepted_works = findViewById(R.id.accepted_works);
        all_grades = findViewById(R.id.all_grades);
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
                String[] web_data = get_data_from_web_service(group_name);
                for (int i = 0; i < web_data.length; i++){ //for loop to print the array
                    Log.d("Element" + i, web_data[i]);
                }
                Intent intent = new Intent(LessonsActivity.this, AddLessonActivity.class);
                intent.putExtra(SUB_TO_PARSE, subject_name);
                intent.putExtra(GROUP_TO_PARSE, group_name);
                intent.putExtra(STUD_TO_PARSE, student_number);
                startActivityForResult(intent, ADD_LESSON_REQUEST);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(LessonsActivity.this));
        recyclerView.setHasFixedSize(true);
        lessonRowAdapter = new LessonRowAdapter();
        recyclerView.setAdapter(lessonRowAdapter);
        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        lessonViewModel.getAllLessons().observe(this, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(List<Lesson> lessons) {
                lessonRowAdapter.setLessons(lessons);
            }
        });
    }

    private String[] get_data_from_web_service(String group_name) {
        String url = "https://digitalacademy.syktsu.ru/common.asmx?op=getScheduleGroup?group_name=" + group_name + "&week_offset=0";
        String[] arr = {"дефолтное данное"};
        return arr;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_LESSON_REQUEST && resultCode == RESULT_OK){
            String lesson_type = data.getStringExtra(AddLessonActivity.EXTRA_LESSON_TYPE);
            String lesson_date = data.getStringExtra(AddLessonActivity.EXTRA_LESSON_DATE);
            String lesson_subject = data.getStringExtra(AddLessonActivity.EXTRA_LESSON_SUBJECT);
            String lesson_group = data.getStringExtra(AddLessonActivity.EXTRA_LESSON_GROUP);
            String grade = data.getStringExtra(AddLessonActivity.EXTRA_LESSON_GRADE);
            int lesson_num = data.getIntExtra(AddLessonActivity.EXTRA_LESSON_NUM, 0);
            int lesson_stud = data.getIntExtra(AddLessonActivity.EXTRA_LESSON_STUDENT, 0);
            Lesson lesson = new Lesson(lesson_num, lesson_type, lesson_date, lesson_subject, lesson_group, lesson_stud, grade);
            lessonViewModel.insert(lesson);
            recreate();
            Toast.makeText(this, "Запись добавлена", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ошибка записи", Toast.LENGTH_SHORT).show();

        }
    }
}