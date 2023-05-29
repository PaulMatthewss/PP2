package com.example.pp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button Add_Button;
    RecyclerView recyclerView;
    App_Database app_db;
    ArrayList<String> subject_id, subject_name, subject_lang, subject_ide;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Add_Button = findViewById(R.id.Add_Button);
        recyclerView = findViewById(R.id.recyclerView);

        Add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddGroupActivity.class);
                startActivity(intent);
            }
        });

        app_db = new App_Database(MainActivity.this);
        subject_id = new ArrayList<>();
        subject_name = new ArrayList<>();
        subject_lang = new ArrayList<>();
        subject_ide = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, subject_id, subject_name, subject_lang, subject_ide);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeDataInArrays(){
        Cursor cursor = app_db.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                subject_id.add(cursor.getString(0));
                subject_name.add(cursor.getString(1));
                subject_lang.add(cursor.getString(2));
                subject_ide.add(cursor.getString(3));
            }
        }
    }
}