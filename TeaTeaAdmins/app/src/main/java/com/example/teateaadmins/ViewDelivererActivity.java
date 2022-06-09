package com.example.teateaadmins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewDelivererActivity extends AppCompatActivity {


    private DatabaseReference db;
    private DatabaseReference db1 = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Delivery Guy Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_deliverer);

        Intent viewCustAct = getIntent();
        String custId = viewCustAct.getStringExtra("delivererid");

        TextView viewName = findViewById(R.id.viewName_viewDeliverer);
        TextView viewAdd = findViewById(R.id.viewAdd_viewDeliverer);
        TextView viewContact = findViewById(R.id.viewContact_viewDeliverer);
        TextView viewEmail = findViewById(R.id.viewEmail_viewDeliverer);


        TextView textAccept = findViewById(R.id.textAccept_deliverer);
        Button btn_accept = findViewById(R.id.btn_accept_deliverer);

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db1.child("Deliverers").child(String.valueOf(custId)).child("accepted").setValue(true);
                btn_accept.setVisibility(LinearLayout.GONE);
                textAccept.setVisibility(LinearLayout.GONE);
                Toast.makeText(getBaseContext(),"User "+viewName+" Accepted.",Toast.LENGTH_SHORT).show();
            }
        });



        db = FirebaseDatabase.getInstance().getReference().child("Deliverers").child(custId);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nameGetter = String.valueOf(snapshot.child("name").getValue());
                String addGetter = String.valueOf(snapshot.child("address").getValue());
                String contactGetter = String.valueOf(snapshot.child("number").getValue());
                String emailGetter = String.valueOf(snapshot.child("email").getValue());
                viewName.setText(nameGetter);
                viewAdd.setText(addGetter);
                viewContact.setText(contactGetter);
                viewEmail.setText(emailGetter);


                if (Boolean.parseBoolean(String.valueOf(snapshot.child(custId).child("accepted").getValue())) == true) {
                    btn_accept.setVisibility(LinearLayout.GONE);
                    textAccept.setVisibility(LinearLayout.GONE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}