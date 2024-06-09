package com.example.pp2.AddActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pp2.Activities.GroupsActivity;
import com.example.pp2.R;

public class AddGroupActivity extends AppCompatActivity {
    public static final String SUB_TO_PARSE =
            "com.example.pp2.SUB_TO_PARSE";

    public static final String EXTRA_NAME =
            "com.example.pp2.EXTRA_NAME";
    public static final String EXTRA_DATE =
            "com.example.pp2.EXTRA_DATE";
    Button Cancel_Button_AddGroup, Submit_Button_AddGroup;
    EditText group_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        Cancel_Button_AddGroup = findViewById(R.id.Cancel_Button_AddGroup);
        Submit_Button_AddGroup = findViewById(R.id.Submit_Button_AddGroup);
        group_name = findViewById(R.id.group_name);
        String subject_name = getIntent().getStringExtra(GroupsActivity.SUB_TO_PARSE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Cancel_Button_AddGroup.setOnClickListener(view ->{
            Intent intent = new Intent(this, GroupsActivity.class);
            intent.putExtra(SUB_TO_PARSE, subject_name);
            startActivity(intent);
        });

        Submit_Button_AddGroup.setOnClickListener(view -> saveGroup());
    }
    private void saveGroup(){
        if(group_name.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Пожалуйста выберите группу", Toast.LENGTH_SHORT).show();
            return;
        }
        String name = group_name.getText().toString().trim();
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        setResult(RESULT_OK, data);
        finish();
    }
}