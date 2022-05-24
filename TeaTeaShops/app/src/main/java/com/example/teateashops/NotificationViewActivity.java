package com.example.teateashops;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NotificationViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_notification_view);




    }
}