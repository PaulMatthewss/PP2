package com.example.pp2.AddActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pp2.Activities.StudentsActivity;
import com.example.pp2.R;

public class AddStudentActivity extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";
    public static final String GROUP_TO_PARSE =
            "com.example.pp2.GROUP_TO_PARSE";

    public static final String EXTRA_FIO =
            "com.example.pp2.EXTRA_FIO";
    public static final String EXTRA_STUD_NUM =
            "com.example.pp2.EXTRA_STUD_NUM";
    Button Cancel_Button_add_student, Submit_Button_add_student;

    EditText fio_input, stud_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Cancel_Button_add_student = findViewById(R.id.Cancel_Button_add_student);
        Submit_Button_add_student = findViewById(R.id.Submit_Button_add_student);
        fio_input = findViewById(R.id.fio_input);
        stud_num = findViewById(R.id.stud_num);
        String subject_name = getIntent().getStringExtra(StudentsActivity.SUB_TO_PARSE);
        String group_name = getIntent().getStringExtra(StudentsActivity.GROUP_TO_PARSE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Cancel_Button_add_student.setOnClickListener(view ->{
            Intent intent = new Intent(this, StudentsActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject_name);
            intent.putExtra(GROUP_TO_PARSE, group_name);
            startActivity(intent);
        });

        Submit_Button_add_student.setOnClickListener(view -> saveStudent());
    }
    private void saveStudent(){
        if(fio_input.getText().toString().trim().isEmpty() ||
                stud_num.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Пожалуйста введите данные", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            String fio = fio_input.getText().toString().trim();
            int num = Integer.parseInt(stud_num.getText().toString().trim());
            Intent data = new Intent();
            data.putExtra(EXTRA_FIO, fio);
            data.putExtra(EXTRA_STUD_NUM, num);
            setResult(RESULT_OK, data);
            finish();
        }catch (Exception e){
            Toast.makeText(this, "Ошибка: " + e, Toast.LENGTH_SHORT).show();
        }
    }
}