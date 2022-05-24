package com.example.teateashops;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.concurrent.ThreadLocalRandom;

public class verifActivity extends AppCompatActivity {

    public static int code;
    signupInst si = new signupInst();
    TeaTeaFirebase db = new TeaTeaFirebase();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Verification Check");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_verif);

        code = codeSendFunction();

        Button submitBtn = findViewById(R.id.submitBtn);
        TextView warningCode = findViewById(R.id.warningCode);
        TextView resendCode = findViewById(R.id.resendCode);
        EditText codeField = findViewById(R.id.codeField);

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = codeSendFunction();
                Toast.makeText(verifActivity.this,"Code Resend Complete.",Toast.LENGTH_SHORT).show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeGet = codeField.getText().toString().trim();
                if (codeGet.equals(String.valueOf(code))) {
                    db.registerVendor();
                    Intent finish_signup = new Intent(verifActivity.this,loginActivity.class);
                    Toast.makeText(verifActivity.this,"Shop Created Successfully!",Toast.LENGTH_SHORT).show();
                    startActivity(finish_signup);
                    finish();
                } else {
                    warningCode.setText("Invalid Code. Try Again...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            warningCode.setText("");
                        }
                    },5000);
                }
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public int codeSendFunction() {
        int code = ThreadLocalRandom.current().nextInt(1000, 9999);

        NotificationCompat.Builder mBuilder =  new NotificationCompat.Builder(verifActivity.this)
                .setSmallIcon(R.drawable.ic_baseline_mail_24)
                .setContentTitle("Verification Code")
                .setContentText("The Code is " + String.valueOf(code));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,mBuilder.build());
        return code;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick (View view) {
        code = codeSendFunction();
        Toast.makeText(verifActivity.this,"Code Resend Complete.",Toast.LENGTH_SHORT);
    }


}