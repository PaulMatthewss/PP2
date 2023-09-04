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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class LessonsActivity extends AppCompatActivity {
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
    public static final int ADD_LESSON_REQUEST = 1;
    public static final int EDIT_LESSON_REQUEST = 2;
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
                /*String[] web_data = get_data_from_web_service(group_name);
                for (int i = 0; i < web_data.length; i++){ //for loop to print the array
                    Log.d("Element" + i, web_data[i]);
                }*/
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
        /*lessonViewModel.getAccepted_Works().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });*/
        //String acc_result = lessonViewModel.getAccepted_Works().toString();
        //accepted_works.setText(acc_result);
        //String grade_result = String.valueOf(lessonViewModel.getSum().getValue());
        //accepted_works.setText(grade_result);
        lessonRowAdapter.setOnItemClickListener(new LessonRowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Lesson lesson) {
                try {

                } catch (Exception e) {
                    Toast.makeText(LessonsActivity.this, "Ошибка: " + e, Toast.LENGTH_SHORT).show();
                    //throw new RuntimeException(e);
                }
            }
        });
    }

    private String[] get_data_from_web_service(String group_name) {
        String url = "https://digitalacademy.syktsu.ru/common.asmx?op=getScheduleGroup?group_name=" + group_name + "&week_offset=0";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://digitalacademy.syktsu.ru/")
                .build();

        WebService service = retrofit.create(WebService.class);
        Call<List<Lesson>> lessons = service.listLessons(group_name);
        Log.d("Output", String.valueOf(lessons));
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
            Integer grade = data.getIntExtra(AddLessonActivity.EXTRA_LESSON_GRADE, 0);
            Integer lesson_num = data.getIntExtra(AddLessonActivity.EXTRA_LESSON_NUM, 0);
            Integer lesson_stud = data.getIntExtra(AddLessonActivity.EXTRA_LESSON_STUDENT, 0);
            boolean lesson_check = data.getBooleanExtra(AddLessonActivity.EXTRA_LESSON_CHECK, false);
            Lesson lesson = new Lesson(lesson_num, lesson_type, lesson_date, lesson_subject, lesson_group, lesson_stud, grade, lesson_check);
            lessonViewModel.insert(lesson);
            recreate();
            Toast.makeText(this, "Запись добавлена", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ошибка записи", Toast.LENGTH_SHORT).show();

        }
    }
}