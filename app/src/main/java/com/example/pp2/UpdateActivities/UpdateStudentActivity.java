package com.example.pp2.UpdateActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.pp2.Activities.StudentsActivity;
import com.example.pp2.Activities.SubjectActivity;
import com.example.pp2.AppDatabase;
import com.example.pp2.Interfaces.ISubjectsDao;
import com.example.pp2.R;

public class UpdateStudentActivity  extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";
    public static final String GROUP_TO_PARSE =
            "com.example.pp2.GROUP_TO_PARSE";

    public static final String EXTRA_FIO =
            "com.example.pp2.EXTRA_FIO";
    public static final String EXTRA_STUD_NUM =
            "com.example.pp2.EXTRA_STUD_NUM";
    Button Cancel_Button_add_student, Update_Button;

    EditText fio_input, stud_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        Cancel_Button_add_student = findViewById(R.id.Cancel_Button_update);
        Update_Button = findViewById(R.id.Update_Button);
        fio_input = findViewById(R.id.fio_input);
        stud_num = findViewById(R.id.stud_num);
        String subject_name = getIntent().getStringExtra(StudentsActivity.SUB_TO_PARSE);
        String group_name = getIntent().getStringExtra(StudentsActivity.GROUP_TO_PARSE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        stud_num.setText(getIntent().getStringExtra("studentNum"));
        fio_input.setText(getIntent().getStringExtra("studentFio"));

        Cancel_Button_add_student.setOnClickListener(view ->{
            Intent intent = new Intent(this, StudentsActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject_name);
            intent.putExtra(GROUP_TO_PARSE, group_name);
            startActivity(intent);
        });

        Update_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "app_database").allowMainThreadQueries().build();
                ISubjectsDao iSubjectsDao = db.iSubjectsDao();
                iSubjectsDao.updateSubject(subjectId, subject_name.getText().toString().trim(),
                        subject_lang.getSelectedItem().toString().trim(),
                        subject_ide.getSelectedItem().toString().trim());
                startActivity(new Intent(getApplicationContext(),SubjectActivity.class));
                finish();*/
            }
        });
    }
}
