package com.example.teatea;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.teatea.ui.cart.CartAdapter;

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


        List<MilkteaItem> items = new ArrayList<>();

//        MilkteaItem item1 = new MilkteaItem("Plain","20 PHP");
//        MilkteaItem item2 = new MilkteaItem("Capp","25 PHP");
//        MilkteaItem item3 = new MilkteaItem("Simp","10 PHP");
//        MilkteaItem item4 = new MilkteaItem("yeye","10 PHP");
//        MilkteaItem item5 = new MilkteaItem("daskdjais","10 PHP");
//
//
//        items.add(item1);
//        items.add(item2);
//        items.add(item3);
//        items.add(item4);
//        items.add(item5);

        items.add(new MilkteaItem("Plain","M","2","140.00"));
        items.add(new MilkteaItem("Soft Gellatio","M","1","70.00"));
        items.add(new MilkteaItem("Chocolat","S","1","65.00"));
        items.add(new MilkteaItem("Wacko Mole","L","3","225.00"));

        CheckoutAdapter adapter = new CheckoutAdapter(items);

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