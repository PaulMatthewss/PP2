package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

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

        Cancel_Button_add_student.setOnClickListener(view ->{
            Intent intent = new Intent(this, StudentsActivity.class);
            startActivity(intent);
        });

        Submit_Button_add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSubject();
            }
        });
    }
    private void saveSubject(){
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