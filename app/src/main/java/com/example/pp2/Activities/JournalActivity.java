package com.example.pp2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.pp2.AddActivities.AddLessonActivity;
import com.example.pp2.AddActivities.AddSubjectActivity;
import com.example.pp2.AppDatabase;
import com.example.pp2.Entities.Lesson;
import com.example.pp2.Interfaces.ILessonDao;
import com.example.pp2.R;

public class JournalActivity extends AppCompatActivity {
    ImageButton studentBtn, groupBtn, subjectBtn;
    Spinner lesson_group, lesson_subject, lesson_stud;
    Button Submit_Button;
    EditText grade;
    CheckBox lesson_checkBox;
    String[] groupsArray = {"1415-ИСо", "1425-ИСо", "1435-ИСо"};
    String[] subjectsArray = {"Android dev", "Desktop dev", "Web dev"};
    String[] studentsArray = {"20204444", "20201234", "20204321"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        subjectBtn=findViewById(R.id.subjectsButton);
        groupBtn=findViewById(R.id.groupsButton);
        studentBtn=findViewById(R.id.studentsButton);
        lesson_group=findViewById(R.id.lesson_group);
        lesson_subject=findViewById(R.id.lesson_subject);
        lesson_stud=findViewById(R.id.lesson_stud);
        lesson_checkBox=findViewById(R.id.lesson_checkBox);
        Submit_Button=findViewById(R.id.Submit_Button);
        grade=findViewById(R.id.grade);
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

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter_group = new ArrayAdapter(this, android.R.layout.simple_spinner_item, groupsArray);
        // Определяем разметку для использования при выборе элемента
        adapter_group.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        lesson_group.setAdapter(adapter_group);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter_subject = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subjectsArray);
        // Определяем разметку для использования при выборе элемента
        adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        lesson_subject.setAdapter(adapter_subject);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter_student = new ArrayAdapter(this, android.R.layout.simple_spinner_item, studentsArray);
        // Определяем разметку для использования при выборе элемента
        adapter_student.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        lesson_stud.setAdapter(adapter_student);

        lesson_checkBox.setOnClickListener(view -> {
            if(lesson_checkBox.isChecked()){
                grade.setHint("Оцените");
                grade.setEnabled(true);
            }
            if(!lesson_checkBox.isChecked()){
                grade.setText("");
                grade.setHint("н");
                grade.setEnabled(false);
            }
        });
        Submit_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*if(grade.getText().toString().trim().isEmpty() && lesson_checkBox.isChecked()){
                    Toast.makeText(JournalActivity.this, "Пожалуйста поставьте оценку", Toast.LENGTH_SHORT).show();
                    return;
                }
                try{
                    Integer g;
                    if(grade.getText().toString().trim().isEmpty()){
                        g = null;
                    }
                    else {
                        g = Integer.parseInt(grade.getText().toString().trim());
                    }*/
                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                            AppDatabase.class, "app_database").allowMainThreadQueries().build();
                    ILessonDao iLessonDao = db.iLessonDao();
                    iLessonDao.insertLesson(new Lesson(4, "Лаб.",
                            "Калькулятор", "15.04.2024", lesson_subject.toString(), lesson_group.toString(), 20204444, 3, true));
                    Toast.makeText(JournalActivity.this, "Запись добавлена", Toast.LENGTH_SHORT).show();;
               /* }catch (Exception e){
                    Toast.makeText(JournalActivity.this, "Ошибка: " + e, Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}
