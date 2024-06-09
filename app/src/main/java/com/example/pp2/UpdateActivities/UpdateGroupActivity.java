package com.example.pp2.UpdateActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.pp2.Activities.GroupsActivity;
import com.example.pp2.Activities.SubjectActivity;
import com.example.pp2.AppDatabase;
import com.example.pp2.Interfaces.ISubjectsDao;
import com.example.pp2.R;

public class UpdateGroupActivity  extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";

    public static final String EXTRA_NAME =
            "com.example.pp2.EXTRA_NAME";
    public static final String EXTRA_DATE =
            "com.example.pp2.EXTRA_DATE";
    Button Cancel_Button_AddGroup, Update_Button;
    EditText group_name;
    int groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_group);

        Cancel_Button_AddGroup = findViewById(R.id.Cancel_Button_update);
        Update_Button = findViewById(R.id.Update_Button);
        group_name = findViewById(R.id.group_name);
        String subject_name = getIntent().getStringExtra(GroupsActivity.SUB_TO_PARSE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        groupId = Integer.parseInt(getIntent().getStringExtra("groupId"));
        group_name.setText(getIntent().getStringExtra("groupName"));

        Cancel_Button_AddGroup.setOnClickListener(view ->{
            Intent intent = new Intent(this, GroupsActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject_name);
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
