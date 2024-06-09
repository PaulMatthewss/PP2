package com.example.pp2.UpdateActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.pp2.Activities.SubjectActivity;
import com.example.pp2.AppDatabase;
import com.example.pp2.Interfaces.ISubjectsDao;
import com.example.pp2.R;

public class UpdateSubjectActivity  extends AppCompatActivity {
    public static final String EXTRA_NAME =
            "com.example.pp2.EXTRA_NAME";
    public static final String EXTRA_LANG =
            "com.example.pp2.EXTRA_LANG";
    public static final String EXTRA_IDE =
            "com.example.pp2.EXTRA_IDE";
    Button Cancel_Button, Update_Button;
    Spinner subject_lang, subject_ide;
    int subjectId;

    EditText subject_name;
    String[] subject_languages = {"C#", "Java", "JavaScript"};
    String[] subject_ides = {"VS Code", "Android Studio", "Visual Studio"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        Cancel_Button = findViewById(R.id.Cancel_Button_update);
        Update_Button = findViewById(R.id.Update_Button);
        subject_name = findViewById(R.id.name_input_update);
        subject_lang = findViewById(R.id.lang_input_update);
        subject_ide = findViewById(R.id.ide_input_update);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        subjectId = Integer.parseInt(getIntent().getStringExtra("subjectId"));
        subject_name.setText(getIntent().getStringExtra("subjectName"));

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
            Intent intent = new Intent(this, SubjectActivity.class);
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
