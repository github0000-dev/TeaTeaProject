package com.example.teateashops;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class DeactivatedPage extends AppCompatActivity {

    public static Activity deactivatePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_deactivated_page);

        deactivatePage = DeactivatedPage.this;

        Button logout_btn = findViewById(R.id.logOutDeactivatePage);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_logout = new Intent(DeactivatedPage.this,loginActivity.class);
                startActivity(activity_logout);
                finish();
                finishAffinity();
            }
        });

    }
}