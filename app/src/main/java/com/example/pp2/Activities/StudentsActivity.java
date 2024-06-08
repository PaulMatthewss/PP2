package com.example.pp2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pp2.Adapters.StudentRowAdapter;
import com.example.pp2.AddActivities.AddStudentActivity;
import com.example.pp2.Entities.Student;
import com.example.pp2.R;
import com.example.pp2.ViewModels.StudentViewModel;

public class StudentsActivity extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";
    public static final String GROUP_TO_PARSE =
            "com.example.pp2.GROUP_TO_PARSE";
    public static final String STUD_TO_PARSE =
            "com.example.pp2.STUD_TO_PARSE";

    public static final int ADD_STUDENT_REQUEST = 1;
    Button add_student_button;
    RecyclerView recyclerView_students;
    StudentRowAdapter studentRowAdapter;
    ImageButton journalBtn, groupBtn, subjectBtn;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        subjectBtn=findViewById(R.id.subjectsButton);
        groupBtn=findViewById(R.id.groupsButton);
        journalBtn=findViewById(R.id.journalButton);
        subjectBtn.setOnClickListener(view -> {
            Intent intent = new Intent(StudentsActivity.this, SubjectActivity.class);
            startActivity(intent);
        });
        groupBtn.setOnClickListener(view -> {
            Intent intent = new Intent(StudentsActivity.this, GroupsActivity.class);
            startActivity(intent);
        });
        journalBtn.setOnClickListener(view -> {
            Intent intent = new Intent(StudentsActivity.this, JournalActivity.class);
            startActivity(intent);
        });

        recyclerView_students = findViewById(R.id.recyclerView_students);
        add_student_button = findViewById(R.id.AddStudent_Button);
        String subject_name = getIntent().getStringExtra(GroupsActivity.SUB_TO_PARSE);
        String group_name = getIntent().getStringExtra(GroupsActivity.GROUP_TO_PARSE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        add_student_button.setOnClickListener(view -> {
            Intent intent = new Intent(StudentsActivity.this, AddStudentActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject_name);
            intent.putExtra(GROUP_TO_PARSE, group_name);
            startActivityForResult(intent, ADD_STUDENT_REQUEST);
        });
        recyclerView_students.setLayoutManager(new LinearLayoutManager(StudentsActivity.this));
        recyclerView_students.setHasFixedSize(true);
        studentRowAdapter = new StudentRowAdapter();
        recyclerView_students.setAdapter(studentRowAdapter);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe(this, students -> studentRowAdapter.setStudents(students));
        /*
        studentRowAdapter.setOnItemClickListener(student -> {
            Intent intent = new Intent(StudentsActivity.this, LessonsActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject_name);
            intent.putExtra(GROUP_TO_PARSE, group_name);
            intent.putExtra(STUD_TO_PARSE, String.valueOf(student.getStud_num()));
            startActivity(intent);
        });*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_STUDENT_REQUEST && resultCode == RESULT_OK && data != null){
            String fio = data.getStringExtra(AddStudentActivity.EXTRA_FIO);
            int student_number = data.getIntExtra(AddStudentActivity.EXTRA_STUD_NUM, 0);
            Student student = new Student(student_number, fio);
            studentViewModel.insert(student);
            recreate();
            Toast.makeText(this, "Запись добавлена", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ошибка записи", Toast.LENGTH_SHORT).show();

        }
    }
}