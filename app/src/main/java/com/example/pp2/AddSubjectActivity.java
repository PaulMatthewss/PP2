package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AddSubjectActivity extends AppCompatActivity {
    public static final String EXTRA_NAME =
            "com.example.pp2.EXTRA_NAME";
    public static final String EXTRA_LANG =
            "com.example.pp2.EXTRA_LANG";
    public static final String EXTRA_IDE =
            "com.example.pp2.EXTRA_IDE";

    Button Cancel_Button, Submit_Button;
    Spinner subject_lang, subject_ide;

    EditText subject_name;
    String[] subject_languages = {"C#", "Java", "JavaScript"};
    String[] subject_ides = {"VS Code", "Android Studio", "Visual Studio"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        Cancel_Button = findViewById(R.id.Cancel_Button);
        Submit_Button = findViewById(R.id.Submit_Button);
        subject_name = findViewById(R.id.name_input);
        subject_lang = findViewById(R.id.lang_input);
        subject_ide = findViewById(R.id.ide_input);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter_lang = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subject_languages);
        // Определяем разметку для использования при выборе элемента
        adapter_lang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        subject_lang.setAdapter(adapter_lang);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter_ide = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subject_ides);
        // Определяем разметку для использования при выборе элемента
        adapter_ide.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        subject_ide.setAdapter(adapter_ide);

        Cancel_Button.setOnClickListener(view ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSubject();
            }
        });
    }
    private void saveSubject(){
        if(subject_name.getText().toString().trim().isEmpty() ||
                subject_lang.getSelectedItem().toString().trim().isEmpty() ||
                subject_ide.getSelectedItem().toString().trim().isEmpty()){
            Toast.makeText(this, "Пожалуйста введите данные", Toast.LENGTH_SHORT).show();
            return;
        }
        String name = subject_name.getText().toString().trim();
        String lang = subject_lang.getSelectedItem().toString().trim();
        String ide = subject_ide.getSelectedItem().toString().trim();
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_LANG, lang);
        data.putExtra(EXTRA_IDE, ide);
        setResult(RESULT_OK, data);
        finish();
    }
}