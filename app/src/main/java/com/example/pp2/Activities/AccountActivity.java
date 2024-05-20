package com.example.pp2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pp2.R;

public class AccountActivity extends AppCompatActivity {

    ImageButton btn_journal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btn_journal = findViewById(R.id.journalButton);
        btn_journal.setOnClickListener(view -> {
            Intent intent = new Intent(AccountActivity.this, SubjectActivity.class);
            startActivity(intent);
        });
    }
    public void goHome (View view){
        Intent intent = new Intent(AccountActivity.this, SubjectActivity.class);
        startActivity(intent);
    }
}