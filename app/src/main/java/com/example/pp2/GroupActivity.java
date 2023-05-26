package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class GroupActivity extends AppCompatActivity {

    Button Cancel_Button, Submit_Button;
    Spinner year;
    EditText group_name;

    private final String[] years = {"2010 г.", "2011 г.", "2012 г.", "20013 г.",
            "2014 г.", "2015 г.", "2016 г.", "2017 г.", "2018 г.", "2019 г.", "2020 г.",
            "2021 г.", "2022 г.", "2023 г.", "2024 г.", "2025 г.", "2026 г.", "2027 г.",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_group);

        Cancel_Button = findViewById(R.id.Cancel_Button);
        Submit_Button = findViewById(R.id.Submit_Button);
        year = findViewById(R.id.year);
        group_name = findViewById(R.id.group_name);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, years);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        year.setAdapter(adapter);

        Cancel_Button.setOnClickListener(view ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App_Database app_db = new App_Database(GroupActivity.this);
                app_db.addGroup(group_name.getText().toString().trim(),
                        year.getSelectedItem().toString().trim());

            }
        });
    }
}
