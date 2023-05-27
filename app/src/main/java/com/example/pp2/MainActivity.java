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

public class MainActivity extends AppCompatActivity implements IRecycleView {

    Button Add_Button;
    RecyclerView recyclerView;
    App_Database app_db;
    CustomAdapter customAdapter;
    ArrayList<String> group_id, group_name, group_year;
    IRecycleView iRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Add_Button = findViewById(R.id.Add_Button);
        recyclerView = findViewById(R.id.recyclerView);

        Add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GroupActivity.class);
                startActivity(intent);
            }
        });

        app_db = new App_Database(MainActivity.this);
        group_id = new ArrayList<>();
        group_name = new ArrayList<>();
        group_year = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, group_id, group_name, group_year, iRecycleView);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
    void storeDataInArrays(){
        Cursor cursor = app_db.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                group_id.add(cursor.getString(0));
                group_name.add(cursor.getString(1));
                group_year.add(cursor.getString(2));
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, GroupActivity.class);
        startActivity(intent);
    }
}