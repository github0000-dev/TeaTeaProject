package com.example.teateadelivery;

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

import com.example.teateadelivery.ui.signupInst;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.Deflater;

import static com.example.teateadelivery.loginActivity.login_activity;

public class verifActivity extends AppCompatActivity {

    public static int code;
    signupInst si = new signupInst();
    TeaTeaFirebase db1 = new TeaTeaFirebase();
    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Customers");


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


        submitBtn.setOnClickListener(new View.OnClickListener() {
            private Deflater Activity;

            @Override
            public void onClick(View v) {
                String codeGet = codeField.getText().toString().trim();
                if (codeGet.equals(String.valueOf(code))) {
                    db1.registerDeliverers(si.fullname,si.address,si.email,si.contact,si.user,si.pass);
//                    db.child(si.user).setValue(new Customer(si.fullname,si.address,si.email,si.contact,si.pass));
                    Toast.makeText(com.example.teateadelivery.verifActivity.this,"Signing Up Successfully.",Toast.LENGTH_SHORT).show();
                    Intent finish_signup = new Intent(com.example.teateadelivery.verifActivity.this,loginActivity.class);
                    login_activity.finish();
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

        NotificationCompat.Builder mBuilder =  new NotificationCompat.Builder(com.example.teateadelivery.verifActivity.this)
                .setSmallIcon(R.drawable.ic_baseline_mail_outline_24)
                .setContentTitle("Verification Code")
                .setContentText("The Code is " + String.valueOf(code));
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,mBuilder.build());
        return code;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view) {
        code = codeSendFunction();
        Toast.makeText(com.example.teateadelivery.verifActivity.this,"Resending Code Completed.",Toast.LENGTH_SHORT).show();
    }
}