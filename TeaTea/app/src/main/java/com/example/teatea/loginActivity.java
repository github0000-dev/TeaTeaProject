package com.example.teatea;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.Query;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static android.content.ContentValues.TAG;

public class loginActivity extends AppCompatActivity {


    private long backPressedTime;
    private Toast backToast;
    private Boolean userChecker = false;
    private String user1,pass1;
    private static int i;
    public static Boolean access;

    public SessionClass session = new SessionClass();

    public static Activity login_activity;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);


        login_activity = loginActivity.this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
            }
        }

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        EditText user = findViewById(R.id.usernameLogin);
        EditText pass = findViewById(R.id.passwordLogin);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister2);
        CheckBox showPass = findViewById(R.id.showPass);
        TextView statusCreds = findViewById(R.id.textWelcome);


        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Intent menuInt = new Intent(loginActivity.this, MainMenu.class);
//                startActivity(menuInt);
//                finish();



                String userGet = user.getText().toString().trim();
                String passGet = pass.getText().toString().trim();

                if (userGet.length()==0 || passGet.length()==0) {
                    statusCreds.setText("Please Fill the Form.");
                } else {
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (!(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
                        Toast.makeText(getBaseContext(), "Please Connect to the Internet", Toast.LENGTH_SHORT).show();
                    } else {
                        db.child("Customers").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (i=0;i<snapshot.getChildrenCount()+1;i++) {
                                    String user = String.valueOf(snapshot.child(String.valueOf(i)).child("username").getValue());
                                    String pass = String.valueOf(snapshot.child(String.valueOf(i)).child("password").getValue());
                                    if (user.equals(userGet) && pass.equals(passGet)) {
                                        session.username = userGet;
                                        Intent menuInt = new Intent(loginActivity.this, MainMenu.class);
                                        Toast.makeText(getBaseContext(), "Welcome " + snapshot.child(String.valueOf(i)).child("name").getValue() + ".", Toast.LENGTH_SHORT).show();
                                        menuInt.putExtra("username",user);
                                        menuInt.putExtra("sessionnum",String.valueOf(i));
                                        startActivity(menuInt);
                                        finish();
                                        return;
                                    }
                                }
                                statusCreds.setText("Invalid Username or Password.");
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.w(TAG, "loadPost:onCancelled",error.toException());
                                Toast.makeText(getBaseContext(), "Please Connect to the internet",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        statusCreds.setText("");
                    }
                },3000);


            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerInt = new Intent(loginActivity.this,signupActivity.class);
                startActivity(registerInt);
                return;
            }





        });

    }
    public void onBackPressed () {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
        } else {
            backToast = Toast.makeText(getBaseContext(),"Press Back Again to Exit.",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}