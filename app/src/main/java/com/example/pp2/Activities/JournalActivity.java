package com.example.pp2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pp2.AddActivities.AddSubjectActivity;
import com.example.pp2.R;

public class JournalActivity extends AppCompatActivity {
    ImageButton studentBtn, groupBtn, subjectBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        subjectBtn=findViewById(R.id.subjectsButton);
        groupBtn=findViewById(R.id.groupsButton);
        studentBtn=findViewById(R.id.studentsButton);
        subjectBtn.setOnClickListener(view -> {
            Intent intent = new Intent(JournalActivity.this, SubjectActivity.class);
            startActivity(intent);
        });
        groupBtn.setOnClickListener(view -> {
            Intent intent = new Intent(JournalActivity.this, GroupsActivity.class);
            startActivity(intent);
        });
        studentBtn.setOnClickListener(view -> {
            Intent intent = new Intent(JournalActivity.this, StudentsActivity.class);
            startActivity(intent);
        });
    }
}
