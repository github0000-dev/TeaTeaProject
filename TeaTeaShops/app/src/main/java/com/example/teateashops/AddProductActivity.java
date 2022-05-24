package com.example.teateashops;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.android.gms.maps.MapView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddProductActivity extends AppCompatActivity {

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Products");
    private static int i,val;

    private static final String PRICE_PATTERN = "(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$";
    private static final Pattern pattern_price = Pattern.compile(PRICE_PATTERN);
    public static boolean isValidPrice(final String price) {
        Matcher matcher = pattern_price.matcher(price);
        return matcher.matches();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Your Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_product);




        MainMenu main = new MainMenu();
//        Vendor vendor = main.getVendor();

        int shop_id = main.getSessionId();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        Log.d("Date","DATE : " + strDate);

        EditText prodname = (EditText) findViewById(R.id.prodNameAdd);
        EditText prodpriceS = (EditText) findViewById(R.id.prodPriceAdd_S);
        EditText prodpriceM = (EditText) findViewById(R.id.prodPriceAdd_M);
        EditText prodpriceL = (EditText) findViewById(R.id.prodPriceAdd_L);
        EditText proddesc = (EditText) findViewById(R.id.prodDescAdd);

        TextView warningName = findViewById(R.id.warningName_addProduct);
        TextView warningPrice = findViewById(R.id.warningPrice_addProduct);

        Button btn_add = (Button) findViewById(R.id.addProductButton);

//        Intent get_extra = getIntent();
//        String shop_id = get_extra.getStringExtra("shop_id");




        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val=0;
                String prodNameGet = prodname.getText().toString().trim();
                String prodPriceSGet = prodpriceS.getText().toString().trim();
                String prodPriceMGet = prodpriceM.getText().toString().trim();
                String prodPriceLGet = prodpriceL.getText().toString().trim();
                String prodDescGet = proddesc.getText().toString().trim();
                String prodDateGet = strDate;

                if (prodname.length()==0) {
                    warningName.setText("This Field is Required!");
                    val=1;
                } else {
                    warningName.setText("");
                }

                if (prodpriceS.length()==0 || prodpriceM.length()==0 || prodpriceL.length()==0) {
                    warningPrice.setText("All of These Price Fields are Required.");
                    val=1;
                } else if ((!isValidPrice(prodPriceSGet) || !isValidPrice(prodPriceMGet) || !isValidPrice(prodPriceLGet))) {
                    warningPrice.setText("One or More of their prices are INVALID");
                    val=1;
                } else {
                    warningPrice.setText("");
                }

                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (i=0;i<snapshot.getChildrenCount()+1;i++) {
                            String prodNameGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("name").getValue());
                            String shopIdGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("shop_id").getValue());
                            if (prodNameGetter.equals(prodNameGet) && shopIdGetter.equals(String.valueOf(i))) {
                                warningName.setText("This Product Name has already EXISTED in your Shop.");
                                val=1;
                            }
                        }

                        if (val == 0) {
                            warningName.setText("");
                            warningPrice.setText("");
                            for (i=0;i<snapshot.getChildrenCount()+1;i++) {
                                if (snapshot.child(String.valueOf(i)).getChildrenCount() == 0) {

//                                    db.child(String.valueOf(i)).setValue(new MilkteaItem(prodNameGet,prodDescGet,prodPriceSGet,prodPriceMGet,prodPriceLGet,prodDateGet));
//                                    db.child(String.valueOf(i)).child("shop_id").setValue(shop_id);
                                    db.child(String.valueOf(i)).setValue(new MilkteaItem(prodNameGet,prodDescGet,prodPriceSGet,prodPriceMGet,prodPriceLGet,prodDateGet,shop_id));

//                                    Toast.makeText(getBaseContext(),"Product Added Successfully.",Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getBaseContext(),"Added Product from Shop ID "+shop_id,Toast.LENGTH_SHORT).show();
                                    finish();
                                    return;
                                }
                            }
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    warningName.setText("");
                                    warningPrice.setText("");
                                }
                            },3000);
                        }
                        return;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getBaseContext(),"Product Added Failed.",Toast.LENGTH_SHORT).show();
                    }
                });

                //db.child("Products").child(prodNameGet).setValue(new Product(prodPriceGet,prodDescGet,"Migz Shop",strDate));

            }
        });

    }
}