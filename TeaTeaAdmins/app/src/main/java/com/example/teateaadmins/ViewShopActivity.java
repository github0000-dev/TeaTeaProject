package com.example.teateaadmins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class ViewShopActivity extends AppCompatActivity {

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference db1 = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Shop Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_shop);

        Intent viewCustAct = getIntent();
        String custId = viewCustAct.getStringExtra("shopid");

        Log.d("shopid",custId);

        TextView viewName = findViewById(R.id.viewName_viewStaff);
        TextView viewAdd = findViewById(R.id.viewAdd_viewStaff);
        TextView viewContact = findViewById(R.id.viewContact_viewStaff);
        TextView viewEmail = findViewById(R.id.viewEmail_viewStaff);

        TextView viewShopName = findViewById(R.id.viewName_viewShop);
        TextView viewShopAdd = findViewById(R.id.viewAdd_viewShop);
        TextView viewShopDesc = findViewById(R.id.viewDesc_viewShop);

        TextView textAccept = findViewById(R.id.textAccept_shop);
        Button btn_accept = findViewById(R.id.btn_accept_shop);


        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db1.child("Staffs").child(String.valueOf(custId)).child("accepted").setValue(true);
                btn_accept.setVisibility(LinearLayout.GONE);
                textAccept.setVisibility(LinearLayout.GONE);
                Toast.makeText(getBaseContext(),"User "+viewName+" Accepted.",Toast.LENGTH_SHORT).show();
            }
        });


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {
                DataSnapshot snapshot = snap.child("Staffs").child(custId);
                DataSnapshot snapshop = snap.child("Shops").child(custId);
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
                } else {

                }

                viewShopAdd.setText(String.valueOf(snapshop.child("shop_address").getValue()));
                viewShopName.setText(String.valueOf(snapshop.child("shop_name").getValue()));
                viewShopDesc.setText(String.valueOf(snapshop.child("shop_desc").getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}