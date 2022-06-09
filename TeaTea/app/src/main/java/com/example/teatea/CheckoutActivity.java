package com.example.teatea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.teatea.ui.cart.CartAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Checking Out");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_checkout);

//        CheckoutAdapter checkouter = new CheckoutAdapter();
//        ImageButton quest_recycler = checkouter.getImageButton();
//
//        quest_recycler.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);
//                builder.setMessage("This feature can allow users to input an existed order reciept to prevent duplicating of orders.");
//                builder.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // no actions specified by Domz.
//                    }
//                });
//                builder.create();
//                builder.show();
//            }
//        });


        RecyclerView listView = findViewById(R.id.listCheckout);

        Intent intent_cart = getIntent();
        String cust_id = intent_cart.getStringExtra("cust_id");


        List<Cart> items = new ArrayList<>();


        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

//        items.add(new MilkteaItem("Plain","M","2","140.00"));
//        items.add(new MilkteaItem("Soft Gellatio","M","1","70.00"));
//        items.add(new MilkteaItem("Chocolat","S","1","65.00"));
//        items.add(new MilkteaItem("Wacko Mole","L","3","225.00"));

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                int i;
                for (i=0;i<snapshot.child("Cart").child(cust_id).getChildrenCount()+1;i++) {
                    if (snapshot.child("Cart").child(cust_id).child(String.valueOf(i)).hasChildren()) {
                        String name = String.valueOf(snapshot.child("Cart").child(cust_id).child(String.valueOf(i)).child("name").getValue());
                        String size = String.valueOf(snapshot.child("Cart").child(cust_id).child(String.valueOf(i)).child("teasize").getValue());
                        int quantity  = Integer.parseInt(String.valueOf(snapshot.child("Cart").child(cust_id).child(String.valueOf(i)).child("quantity").getValue()));
                        double price  = Double.parseDouble(String.valueOf(snapshot.child("Cart").child(cust_id).child(String.valueOf(i)).child("price").getValue()));
                        double totalprice = Double.parseDouble(String.valueOf(snapshot.child("Cart").child(cust_id).child(String.valueOf(i)).child("totalprice").getValue()));
                        String shop = String.valueOf(snapshot.child("Shops").child(String.valueOf(snapshot.child("Products").child(String.valueOf(i)).child("shop_id").getValue())).child("shop_name").getValue());
                        items.add(new Cart(i,quantity,size,price,totalprice,name,shop));
                    }
                }
                CartAdapter.RecycleViewDeleteListener listener = new CartAdapter.RecycleViewDeleteListener() {
                    @Override
                    public void onItemRemove(View v, int position) {
                        Toast.makeText(CheckoutActivity.this,items.get(position).name +" deleted.",Toast.LENGTH_SHORT).show();
                        db.child("Cart").child(cust_id).child(String.valueOf(position)).removeValue();
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                        builder.setMessage("Are you sure to delete "+ items.get(position).name+ "?");
//                        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//                        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                db.child(String.valueOf(position)).removeValue();
//                            }
//                        });
//                        builder.create();
//                        builder.show();
                    }
                };

                CartAdapter adapter = new CartAdapter(items,listener);
                listView.setAdapter(adapter);
                listView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                
            }
        });

        CartAdapter adapter = new CartAdapter(items);

        listView.setAdapter(adapter);

        listView.setLayoutManager(new LinearLayoutManager(CheckoutActivity.this));

        LinearLayout checkoutCardField = findViewById(R.id.cardFieldCheckout);
        RadioButton electronic_cash = findViewById(R.id.electronic_pay);

        checkoutCardField.setVisibility(View.INVISIBLE);

        electronic_cash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkoutCardField.setVisibility(View.VISIBLE);
                } else {
                    checkoutCardField.setVisibility(View.INVISIBLE);
                }
            }
        });

        Button btn_checkout = findViewById(R.id.btn_checkout);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}