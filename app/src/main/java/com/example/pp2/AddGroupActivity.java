package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddGroupActivity extends AppCompatActivity {

    Button Cancel_Button_AddGroup, Submit_Button_AddGroup;
    Spinner group_name;
    EditText year;

    private final String[] groups = {"1415-ИСО", "1425-ИСО", "1435-ИСО"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        Cancel_Button_AddGroup = findViewById(R.id.Cancel_Button_AddGroup);
        Submit_Button_AddGroup = findViewById(R.id.Submit_Button_AddGroup);
        group_name = findViewById(R.id.group_name);
        year = findViewById(R.id.year);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, groups);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        group_name.setAdapter(adapter);

        Cancel_Button_AddGroup.setOnClickListener(view ->{
            Intent intent = new Intent(this, GroupsActivity.class);
            startActivity(intent);
        });

        Submit_Button_AddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App_Database app_db = new App_Database(AddGroupActivity.this);
                app_db.addGroup(group_name.getSelectedItem().toString().trim(),
                        year.getText().toString().trim());

            }
        });
    }
}