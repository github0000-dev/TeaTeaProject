package com.example.teatea;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teatea.emailSender.GMailSender;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.Deflater;

import static com.example.teatea.MainMenu.menu_act;
import static com.example.teatea.loginActivity.login_activity;
import static com.example.teatea.signupActivity.signupActivity;

public class verifActivity extends AppCompatActivity {

    public static int code;
    public signupInst si = new signupInst();
    public TeaTeaFirebase db1 = new TeaTeaFirebase();
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Customers");
    private SmsManager smsManager = SmsManager.getDefault();




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
        TextView emailVerif = findViewById(R.id.emailShowVerifText);

        emailVerif.setText("The Code has been sent to "+si.email+ ".\nPlease Enter the Verification code to proceed Finishing Registration.");


        submitBtn.setOnClickListener(new View.OnClickListener() {
            private Deflater Activity;

            @Override
            public void onClick(View v) {
                String codeGet = codeField.getText().toString().trim();
                if (codeGet.equals(String.valueOf(code))) {
                    db1.registerCustomer(si.fullname,si.address,si.email,si.contact,si.user,si.pass);
                    Toast.makeText(verifActivity.this,"Signing Up Successfully.",Toast.LENGTH_SHORT).show();
//                    db.child(si.user).setValue(new Customer(si.fullname,si.address,si.email,si.contact,si.pass));
                    signupActivity.finish();
                    login_activity.finish();
                    Intent finish_signup = new Intent(verifActivity.this,loginActivity.class);
                    startActivity(finish_signup);
                    finish();
                    finishAffinity();
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

//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.cancel(0);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                NotificationCompat.Builder mBuilder =  new NotificationCompat.Builder(verifActivity.this)
//                        .setSmallIcon(R.drawable.ic_baseline_mail_outline_24)
//                        .setContentTitle("Verification Code")
//                        .setContentText("The Code is " + String.valueOf(code));
//                notificationManager.notify(0,mBuilder.build());
//            }
//        },1500);
//
//        smsManager.sendTextMessage(si.contact,null,"The code is "+ String.valueOf(code) +".",null,null);


        sendEmail("mejares.dominic_dave@hnu.edu.ph",
                "mejares06802892",
                si.email,
                "TeaTea Verification",
                "The Code is "+code+".");

        return code;
    }


    private void sendEmail(final String Sender,final String Password,final String Receiver,final String Title,final String Message)
    {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(Sender,Password);
                    sender.sendMail(Title, "<b>"+Message+"</b>", Sender, Receiver);
                    makeAlert(Receiver);

                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
    }
    private void makeAlert(String email){
        this.runOnUiThread(new Runnable() {
            public void run() {
                System.out.println("Mail Sent to "+email+".");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view) {
        code = codeSendFunction();
        Toast.makeText(verifActivity.this,"Resending Code Completed.",Toast.LENGTH_SHORT).show();
    }
}