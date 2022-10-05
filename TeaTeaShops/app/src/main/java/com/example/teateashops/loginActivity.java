package com.example.teateashops;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.io.IOException;

import kotlinx.coroutines.MainCoroutineDispatcher;

import static android.content.ContentValues.TAG;
import static com.example.teateashops.DeactivatedPage.deactivatePage;
import static com.example.teateashops.MainMenu.main_menu_act;

public class loginActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    private String user1,pass1;


    private static int i;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        EditText user = findViewById(R.id.usernameLogin);
        EditText pass = findViewById(R.id.passwordLogin);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerInt = new Intent(loginActivity.this,signupActivity.class);
                startActivity(registerInt);
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
                        db.child("Staffs").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
<<<<<<< HEAD
                                int val=0;
=======
                                Intent menuInt = new Intent(loginActivity.this, MainMenu.class);
                                Intent deactInt = new Intent(loginActivity.this,DeactivatedPage.class);
>>>>>>> master
                                for (i=0;i<snapshot.getChildrenCount()+1;i++) {
                                    String user = String.valueOf(snapshot.child(String.valueOf(i)).child("username").getValue());
                                    String pass = String.valueOf(snapshot.child(String.valueOf(i)).child("password").getValue());
//                                    if (user.equals(userGet) && pass.equals(passGet)) {
//                                        // session.username = userGet;
//                                        Intent menuInt = new Intent(loginActivity.this, MainMenu.class);
//                                        Toast.makeText(getBaseContext(), "Welcome " + snapshot.child(String.valueOf(i)).child("name").getValue() + ".", Toast.LENGTH_SHORT).show();
//                                        menuInt.putExtra("username",user);
//                                        menuInt.putExtra("sessionnum",String.valueOf(i));
//                                        startActivity(menuInt);
//                                        finish();
//                                        return;
//                                    }

                                    if (user.equals(userGet) && pass.equals(passGet)) {
                                        // session.username = userGet;
<<<<<<< HEAD
                                        System.out.println(snapshot.child(String.valueOf(i)).child("accepted").getValue());
                                        String boolacc = String.valueOf(snapshot.child(String.valueOf(i)).child("accepted").getValue());
                                        if (boolacc.equals("true")) {
                                            val = 1;
                                            Intent menuInt = new Intent(loginActivity.this, MainMenu.class);
=======
//
                                        if (String.valueOf(snapshot.child(String.valueOf(i)).child("accepted").getValue()).equals("true")) {
>>>>>>> master
                                            Toast.makeText(getBaseContext(), "Welcome " + snapshot.child(String.valueOf(i)).child("name").getValue() + ".", Toast.LENGTH_SHORT).show();
                                            menuInt.putExtra("username",user);
                                            menuInt.putExtra("sessionnum",String.valueOf(i));
                                            startActivity(menuInt);
                                            finish();
<<<<<<< HEAD
                                            break;
                                        } else {
                                            val = 1;
                                            Intent menuInt = new Intent(loginActivity.this, DeactivatedPage.class);
                                            Toast.makeText(getBaseContext(), "Welcome " + snapshot.child(String.valueOf(i)).child("name").getValue() + ".", Toast.LENGTH_SHORT).show();
                                            startActivity(menuInt);
                                            finish();
                                            break;
                                        }
=======
                                            try {
                                                deactivatePage.finish();
                                            } catch (Exception e) {

                                            }
                                        } else {
                                            startActivity(deactInt);
                                            finish();

                                        }
                                        return;
>>>>>>> master
                                    }

                                }
                                if (val == 0) {
                                    statusCreds.setText("Invalid Username or Password.");
                                }
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