package com.example.pp2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pp2.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_enter = findViewById(R.id.btn_enter);

        btn_enter.setOnClickListener(view -> {
            Intent intent = new Intent(this, SubjectActivity.class);
            startActivity(intent);
        });
    }
}