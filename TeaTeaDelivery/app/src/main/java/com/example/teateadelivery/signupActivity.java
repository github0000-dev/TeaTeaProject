package com.example.teateadelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teateadelivery.ui.signupInst;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class signupActivity extends AppCompatActivity {

    private static int val, i;
    private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";
    private static final String NAME_PATTERN = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
    private static final String CONTACT_PATTERN = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
    private static final String ADDRESS_PATTERN = "^[#.0-9a-zA-Z\\s,-]+$";

    public DatabaseReference dbread = FirebaseDatabase.getInstance().getReference().child("Deliverers");

    public TeaTeaFirebase data_b = new TeaTeaFirebase();

    private static final Pattern pattern_email = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern pattern_name = Pattern.compile(NAME_PATTERN);
    private static final Pattern pattern_contact = Pattern.compile(CONTACT_PATTERN);
    private static final Pattern pattern_address = Pattern.compile(ADDRESS_PATTERN);

    public static boolean isValidEmail(final String email) {
        Matcher matcher = pattern_email.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidContact(final String contact) {
        Matcher matcher = pattern_contact.matcher(contact);
        return matcher.matches();
    }

    public static boolean isValidName(final String name) {
        Matcher matcher = pattern_name.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidAddress(final String address) {
        Matcher matcher = pattern_name.matcher(address);
        return matcher.matches();
    }

    public static int thereIs;
    public static Activity signupActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Signing Up with TeaTea");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_signup);

        // TeaTeaFirebase db = new TeaTeaFirebase();
        signupInst si = new signupInst();
        signupActivity = signupActivity.this;


        TextView warningName = (TextView) findViewById(R.id.warningName);
        TextView warningAdd = (TextView) findViewById(R.id.warningAdd);
        TextView warningEmail = (TextView) findViewById(R.id.warningEmail);
        TextView warningContact = (TextView) findViewById(R.id.warningContact);
        TextView warningUser = (TextView) findViewById(R.id.warningUser);
        TextView warningPass = (TextView) findViewById(R.id.warningPass);
        TextView warningRepass = (TextView) findViewById(R.id.warningRepass);
        EditText name = (EditText) findViewById(R.id.fullName);
        EditText address = (EditText) findViewById(R.id.address);
        EditText email = (EditText) findViewById(R.id.email);
        EditText contact = (EditText) findViewById(R.id.contact);
        EditText user = (EditText) findViewById(R.id.username);
        EditText pass = (EditText) findViewById(R.id.password);
        EditText repass = (EditText) findViewById(R.id.repassword);
        @SuppressLint("WrongViewCast") final AppCompatCheckBox passbox = (AppCompatCheckBox) findViewById(R.id.passbox);
        @SuppressLint("WrongViewCast") final AppCompatCheckBox repassbox = (AppCompatCheckBox) findViewById(R.id.repassbox);
        Button register_btn = findViewById(R.id.btnRegister3);


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = 0;
                thereIs = 0;
                String nameGet = name.getText().toString().trim();
                String addressGet = address.getText().toString().trim();
                String emailGet = email.getText().toString().trim();
                String userGet = user.getText().toString().trim();
                String contactGet = contact.getText().toString().trim();
                String passGet = pass.getText().toString().trim();
                String repassGet = repass.getText().toString().trim();


                if (name.length() == 0) {
                    warningName.setText("This Field is REQUIRED.");
                    val = 1;
                } else if (!isValidName(nameGet)) {
                    warningName.setText("This Name is INVALID.");
                    val = 1;
                } else {
                    warningName.setText("");
                }
                if (address.length() == 0) {
                    warningAdd.setText("This Field is REQUIRED.");
                    val = 1;
                } else if (isValidAddress(addressGet)) {
                    warningAdd.setText("This Address is INVALID.");
                    val = 1;
                } else {
                    warningAdd.setText("");
                }
                if (email.length() == 0) {
                    warningEmail.setText("This Field is REQUIRED.");
                    val = 1;
                } else if (!isValidEmail(emailGet)) {
                    warningEmail.setText("This Email is INVALID.");
                    val = 1;
                } else {
                    warningEmail.setText("");
                }
                if (contact.length() == 0) {
                    warningContact.setText("This Field is REQUIRED.");
                    val = 1;
                } else if (!isValidContact(contactGet)) {
                    warningContact.setText("This Contact is INVALID.");
                    val = 1;
                } else {
                    warningContact.setText("");
                }
                if (user.length() == 0) {
                    warningUser.setText("This Field is REQUIRED.");
                    val = 1;
                } else if (user.length() < 4) {
                    warningUser.setText("This Username is too SHORT.");
                    val = 1;
                } else {
                    warningUser.setText("");
                }
                if (pass.length() == 0) {
                    warningPass.setText("This Field is REQUIRED.");
                    val = 1;
                } else if (pass.length() < 6) {
                    warningPass.setText("Password must be at least 6 characters.");
                    val = 1;
                } else {
                    warningPass.setText("");
                }
                if (repass.length() == 0) {
                    warningRepass.setText("This Field is REQUIRED.");
                    val = 1;
                } else {
                    warningRepass.setText("");
                    if (!passGet.equals(repassGet)) {
                        warningPass.setText("These Passwords MUST MATCH.");
                        warningRepass.setText("These Passwords MUST MATCH.");
                        val = 1;
                    } else {
                        warningRepass.setText("");
                    }
                }


                dbread.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (i = 0; i < snapshot.getChildrenCount() + 1; i++) {
                            String nameGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("name").getValue());
                            if (nameGetter.equals(nameGet)) {
                                warningName.setText("This Name has EXISTED.");
                                val = 1;
                            }
                            String userGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("username").getValue());
                            if (userGetter.equals(userGet)) {
                                warningUser.setText("This Username has EXISTED.");
                                val = 1;
                            }
                            Log.d("Name Index " + String.valueOf(i), userGetter);
                        }


                        if (val == 0) {
                            warningName.setText("");
                            warningAdd.setText("");
                            warningEmail.setText("");
                            warningContact.setText("");
                            warningUser.setText("");
                            warningPass.setText("");
                            warningRepass.setText("");
                            si.fullname = nameGet;
                            si.address = addressGet;
                            si.email = emailGet;
                            si.contact = contactGet;
                            si.user = userGet;
                            si.pass = passGet;
                            // data_b.registerDeliverers(si.fullname, si.address, si.email, si.contact, si.user, si.pass);
                            Intent intent_ver = new Intent(signupActivity.this, verifActivity.class);
                            startActivity(intent_ver);
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    warningName.setText("");
                                    warningAdd.setText("");
                                    warningEmail.setText("");
                                    warningContact.setText("");
                                    warningUser.setText("");
                                    warningPass.setText("");
                                    warningRepass.setText("");
                                }
                            }, 3000);
                        }
                        return;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w(TAG, "loadPost:onCancelled", error.toException());
                    }
                });
            }
        });

        passbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        repassbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    repass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    repass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


    }
}