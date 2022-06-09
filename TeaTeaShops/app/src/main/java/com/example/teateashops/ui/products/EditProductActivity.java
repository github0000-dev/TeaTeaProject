package com.example.teateashops.ui.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teateashops.MilkteaItem;
import com.example.teateashops.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProductActivity extends AppCompatActivity {

    private static final String PRICE_PATTERN = "(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$";
    private static final Pattern pattern_price = Pattern.compile(PRICE_PATTERN);
    public static boolean isValidPrice(final String price) {
        Matcher matcher = pattern_price.matcher(price);
        return matcher.matches();
    }

    private int i,val;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        Log.d("Date","DATE : " + strDate);

        EditText prodname = (EditText) findViewById(R.id.prodNameEdit);
        EditText prodpriceS = (EditText) findViewById(R.id.prodPriceEdit_S);
        EditText prodpriceM = (EditText) findViewById(R.id.prodPriceEdit_M);
        EditText prodpriceL = (EditText) findViewById(R.id.prodPriceEdit_L);
        EditText proddesc = (EditText) findViewById(R.id.prodDescEdit);


        TextView warningName = findViewById(R.id.warningName_editProduct);
        TextView warningPrice = findViewById(R.id.warningPrice_editProduct);

        Button btn_add = (Button) findViewById(R.id.saveProductButton);

        Intent get_extra = getIntent();
        String shop_id = get_extra.getStringExtra("shop_id");
        String prod_id = get_extra.getStringExtra("prod_id");


        System.out.println("Shop Id: "+shop_id);
        System.out.println("Prod Id: "+prod_id);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Products").child(prod_id);
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Products").child(prod_id);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prodname.setText(String.valueOf(snapshot.child("name").getValue()));
                proddesc.setText(String.valueOf(snapshot.child("description").getValue()));
                prodpriceS.setText(String.valueOf(snapshot.child("price_s").getValue()));
                prodpriceM.setText(String.valueOf(snapshot.child("price_m").getValue()));
                prodpriceL.setText(String.valueOf(snapshot.child("price_l").getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                finish();
            }
        });




        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = 0;
                String prodNameGet = prodname.getText().toString().trim();
                String prodPriceSGet = prodpriceS.getText().toString().trim();
                String prodPriceMGet = prodpriceM.getText().toString().trim();
                String prodPriceLGet = prodpriceL.getText().toString().trim();
                String prodDescGet = proddesc.getText().toString().trim();
                String prodDateGet = strDate;

                if (prodname.length() == 0) {
                    warningName.setText("This Field is Required!");
                    val = 1;
                } else {
                    warningName.setText("");
                }

                if (prodpriceS.length() == 0 || prodpriceM.length() == 0 || prodpriceL.length() == 0) {
                    warningPrice.setText("All of These Price Fields are Required.");
                    val = 1;
                } else if ((!isValidPrice(prodPriceSGet) || !isValidPrice(prodPriceMGet) || !isValidPrice(prodPriceLGet))) {
                    warningPrice.setText("One or More of their prices are INVALID");
                    val = 1;
                } else {
                    warningPrice.setText("");
                }

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (i = 0; i < snapshot.getChildrenCount() + 1; i++) {
                            String prodNameGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("name").getValue());
                            String shopIdGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("shop_id").getValue());
                            if (prodNameGetter.equals(prodNameGet) && shopIdGetter.equals(String.valueOf(i))) {
                                warningName.setText("This Product Name has already EXISTED in your Shop.");
                                val = 1;
                            }
                        }

                        if (val == 0) {
                            warningName.setText("");
                            warningPrice.setText("");
//                            for (i = 0; i < snapshot.getChildrenCount() + 1; i++) {
//                                if (snapshot.child(String.valueOf(i)).getChildrenCount() == 0) {
//
////                                    db.child(String.valueOf(i)).setValue(new MilkteaItem(prodNameGet,prodDescGet,prodPriceSGet,prodPriceMGet,prodPriceLGet,prodDateGet));
////                                    db.child(String.valueOf(i)).child("shop_id").setValue(shop_id);
//                                    db.child(String.valueOf(i)).setValue(new MilkteaItem(prodNameGet, prodDescGet, prodPriceSGet, prodPriceMGet, prodPriceLGet, prodDateGet, Integer.parseInt(shop_id)));
//
////                                    Toast.makeText(getBaseContext(),"Product Added Successfully.",Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(getBaseContext(), "Added Product from Shop ID " + shop_id, Toast.LENGTH_SHORT).show();
//                                    finish();
//                                    return;
//                                }
//                           }
                            db1.child("name").setValue(prodNameGet);
                            db1.child("description").setValue(prodDescGet);
                            db1.child("price_s").setValue(prodPriceSGet);
                            db1.child("price_m").setValue(prodPriceMGet);
                            db1.child("price_l").setValue(prodPriceLGet);
                            Toast.makeText(getBaseContext(), "Product Updated Successfully.", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    warningName.setText("");
                                    warningPrice.setText("");
                                }
                            }, 3000);
                        }
                        return;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getBaseContext(), "Product Added Failed.", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }

}