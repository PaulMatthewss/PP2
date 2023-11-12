package com.example.pp2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pp2.Adapters.SubjectRowAdapter;
import com.example.pp2.AddActivities.AddSubjectActivity;
import com.example.pp2.Entities.Subject;
import com.example.pp2.R;
import com.example.pp2.ViewModels.SubjectViewModel;

public class SubjectActivity extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";

    public static final int ADD_SUBJECT_REQUEST = 1;

    Button Add_Button;
    RecyclerView recyclerView;
    SubjectRowAdapter subjectRowAdapter;
    private SubjectViewModel subjectViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        Add_Button = findViewById(R.id.Add_Button);
        recyclerView = findViewById(R.id.recyclerView);

        Add_Button.setOnClickListener(view -> {
            Intent intent = new Intent(SubjectActivity.this, AddSubjectActivity.class);
            startActivityForResult(intent, ADD_SUBJECT_REQUEST);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(SubjectActivity.this));
        recyclerView.setHasFixedSize(true);
        subjectRowAdapter = new SubjectRowAdapter();
        recyclerView.setAdapter(subjectRowAdapter);
        subjectViewModel = new ViewModelProvider(this).get(SubjectViewModel.class);
        subjectViewModel.getAllSubjects().observe(this, subjects -> subjectRowAdapter.setSubjects(subjects));
        subjectRowAdapter.setOnItemClickListener(subject -> {
            Intent intent = new Intent(SubjectActivity.this, GroupsActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject.getName());
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_SUBJECT_REQUEST && resultCode == RESULT_OK && data != null){
            String name = data.getStringExtra(AddSubjectActivity.EXTRA_NAME);
            String lang = data.getStringExtra(AddSubjectActivity.EXTRA_LANG);
            String ide = data.getStringExtra(AddSubjectActivity.EXTRA_IDE);
            Subject subject = new Subject(name, lang, ide);
            subjectViewModel.insert(subject);
            recreate();
            Toast.makeText(this, "Запись добавлена", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ошибка записи", Toast.LENGTH_SHORT).show();

        }
    }
}