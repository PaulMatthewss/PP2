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

public class StudentsActivity extends AppCompatActivity {

    Button add_student_button, button_back;
    RecyclerView recyclerView_students;
    ArrayList<String> student_id, student_name, student_gender;
    StudentRowAdapter studentRowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        recyclerView_students = findViewById(R.id.recyclerView_students);
        add_student_button = findViewById(R.id.AddStudent_Button);
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

        //app_db = new App_Database(MainActivity.this);
        student_id = new ArrayList<>();
        student_name = new ArrayList<>();
        student_gender = new ArrayList<>();

        //storeDataInArrays();

        studentRowAdapter = new StudentRowAdapter(StudentsActivity.this, this, student_id,
                student_name, student_gender);
        recyclerView_students.setAdapter(studentRowAdapter);
        recyclerView_students.setLayoutManager(new LinearLayoutManager(StudentsActivity.this));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
}