package com.example.teatea.ui.home.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.teatea.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopActivity extends AppCompatActivity {

    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Shops");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }
}