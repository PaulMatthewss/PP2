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

public class UpdateSubjectActivity extends AppCompatActivity {

    Button Cancel_Button_update, Update_Button;
    Spinner subject_lang_update, subject_ide_update;

    EditText subject_name_update;
    String[] subject_languages = {"C#", "Java", "JavaScript"};
    String[] subject_ides = {"VS Code", "Android Studio", "Visual Studio"};
    String id, name, lang, ide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        Cancel_Button_update = findViewById(R.id.Cancel_Button_update);
        Update_Button = findViewById(R.id.Update_Button);
        subject_name_update = findViewById(R.id.name_input_update);
        subject_lang_update = findViewById(R.id.lang_input_update);
        subject_ide_update = findViewById(R.id.ide_input_update);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter_lang = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subject_languages);
        // Определяем разметку для использования при выборе элемента
        adapter_lang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        subject_lang_update.setAdapter(adapter_lang);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter_ide = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subject_ides);
        // Определяем разметку для использования при выборе элемента
        adapter_ide.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        subject_ide_update.setAdapter(adapter_ide);

        getAndSetIntentData();

        Cancel_Button_update.setOnClickListener(view ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Update_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("lang") &&
                getIntent().hasExtra("ide")){

            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("subject_name");
            lang = getIntent().getStringExtra("subject_lang");
            ide = getIntent().getStringExtra("subject_ide");

            subject_name_update.setText(name);
        }else{
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
    }
}