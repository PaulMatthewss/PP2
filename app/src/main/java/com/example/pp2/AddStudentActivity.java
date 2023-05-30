package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddStudentActivity extends AppCompatActivity {

    Button Cancel_Button_add_student, Submit_Button_add_student;
    Spinner gender;

    EditText fio_input;
    String[] genders = {"М", "Ж"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Cancel_Button_add_student = findViewById(R.id.Cancel_Button_add_student);
        Submit_Button_add_student = findViewById(R.id.Submit_Button_add_student);
        fio_input = findViewById(R.id.fio_input);
        gender = findViewById(R.id.gender);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genders);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        gender.setAdapter(adapter);

        Cancel_Button_add_student.setOnClickListener(view ->{
            Intent intent = new Intent(this, StudentsActivity.class);
            startActivity(intent);
        });

        Submit_Button_add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}