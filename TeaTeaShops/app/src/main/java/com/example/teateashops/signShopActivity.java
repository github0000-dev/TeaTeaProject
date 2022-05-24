package com.example.teateashops;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signShopActivity extends AppCompatActivity {

    public static int val;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Shops");
    private static int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Create Shop Branch");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_sign_shop);

        signupInst si = new signupInst();
        TeaTeaFirebase tea = new TeaTeaFirebase();


//        TextView warningOwner = findViewById(R.id.warningOwner);
        TextView warningDesc = findViewById(R.id.warningDesc);
        TextView warningShopName = findViewById(R.id.warningShop);
        TextView warningShopAdd = findViewById(R.id.warningShopAdd);

//        EditText ownerName = findViewById(R.id.ownerName);
        EditText shopName = findViewById(R.id.shopName);
        EditText shopAdd = findViewById(R.id.shopAdd);
        EditText shopDesc = findViewById(R.id.shopDesc);

        Spinner spinner_position = (Spinner) findViewById(R.id.spinner_position_shop);
        ArrayAdapter<CharSequence> adapter_position = ArrayAdapter.createFromResource(this,
                R.array.position_shop, android.R.layout.simple_spinner_item);
        adapter_position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_position.setAdapter(adapter_position);
        spinner_position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //nothing
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing
            }
        });

        Button btnFinish = findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        val = 0;
//                        String ownerNameGet = ownerName.getText().toString().trim();
                        String shopNameGet = shopName.getText().toString().trim();
                        String shopAddGet = shopAdd.getText().toString().trim();
                        String shopDescGet = shopDesc.getText().toString().trim();
                        String shopPosGet = spinner_position.getSelectedItem().toString().trim();

                        for (i=0;i<snapshot.getChildrenCount()+1;i++) {
                            if (shopNameGet.equals(snapshot.child(String.valueOf(i)).child("shop_name").getValue())) {
                                warningShopName.setText("This Shop has EXISTED");
                                // Toast.makeText(getBaseContext(),"This Shop name Has Existed. Cannot Proceed.",Toast.LENGTH_SHORT).show();
                                val = 1;
                            }
                        }

//                        if (ownerName.length() == 0) {
//                            warningOwner.setText("This Field is REQUIRED");
//                            val = 1;
//                        } else {
//                            warningOwner.setText("");
//                        }

                        if (shopAdd.length() == 0) {
                            warningShopAdd.setText("This Field is REQUIRED");
                            val = 1;
                        } else {
                            warningShopAdd.setText("");
                        }
                        if (shopName.length() == 0) {
                            warningShopName.setText("This Field is REQUIRED");
                            val = 1;
                        } else {
                            warningShopName.setText("");
                        }


                        if (val == 0) {
//                            si.ownername = ownerNameGet;
                            si.shopname = shopNameGet;
                            si.shopadd = shopAddGet;
                            si.shopdesc = shopDescGet;
                            si.shoppos = shopPosGet;
//                    Toast.makeText(signShopActivity.this,"Shop Created Successfully!",Toast.LENGTH_SHORT).show();
//                            tea.registerVendor();
                            Intent intent_verif = new Intent(signShopActivity.this,verifActivity.class);
                            startActivity(intent_verif);
//                            finish();
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
//                                    warningOwner.setText("");
                                    warningDesc.setText("");
                                    warningShopAdd.setText("");
                                    warningShopName.setText("");
                                }
                            },3000);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}