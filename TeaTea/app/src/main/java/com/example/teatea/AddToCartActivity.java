package com.example.teatea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileReader;

public class AddToCartActivity extends AppCompatActivity {

    private DatabaseReference db,db1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Product Details to Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_to_cart);

        Intent itemDetails = getIntent();
        int prodID = Integer.parseInt(itemDetails.getStringExtra("productid"));
        String custID = itemDetails.getStringExtra("custid");
        String prodName = itemDetails.getStringExtra("prodname");
        String shopname = itemDetails.getStringExtra("shopname");

        TextView prodname = findViewById(R.id.prodName);
        TextView proddesc = findViewById(R.id.prodDesc);
        TextView prodprice = findViewById(R.id.prodPrice);

        TextView priceTotal = findViewById(R.id.totalPriceText_addCart);

        db = FirebaseDatabase.getInstance().getReference().child("Products").child(String.valueOf(prodID));
//        db.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String nameGetter = String.valueOf(snapshot.child("name").getValue());
//                String descGetter = String.valueOf(snapshot.child("description").getValue());
//                String priceSGetter = String.valueOf(snapshot.child("price_s").getValue());
//                String priceMGetter = String.valueOf(snapshot.child("price_m").getValue());
//                String priceLGetter = String.valueOf(snapshot.child("price_l").getValue());
//                prodname.setText(nameGetter);
//                proddesc.setText(descGetter);
//                prodprice.setText("S - "+priceSGetter+" PHP\nM - "+priceMGetter+" PHP\nL - "+priceLGetter+" PHP");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        final int[] quantity = new int[1];
        final int[] size = new int[1];

        final double[] price = new double[1];



        Spinner spinner_size = findViewById(R.id.spinner_size_addtocart);
        ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,
                R.array.itemsize_cart, android.R.layout.simple_spinner_item);
        adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_size.setAdapter(adapter_size);
        spinner_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (String.valueOf(item).equals("S")) {
                    size[0] = 1;
                } else if (String.valueOf(item).equals("M")) {
                    size[0] = 2;
                } else if (String.valueOf(item).equals("L")) {
                    size[0] = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinner_quantity = findViewById(R.id.spinner_quantity_addtocart);
        ArrayAdapter<CharSequence> adapter_quantity = ArrayAdapter.createFromResource(this,
                R.array.quantity_cart, android.R.layout.simple_spinner_item);
        adapter_quantity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_quantity.setAdapter(adapter_quantity);
        spinner_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
//                Log.d("Item",String.valueOf(item));

                quantity[0] = Integer.parseInt(String.valueOf(item));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        db1 = FirebaseDatabase.getInstance().getReference().child("Cart").child(custID);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nameGetter = String.valueOf(snapshot.child("name").getValue());
                String descGetter = String.valueOf(snapshot.child("description").getValue());
                String priceSGetter = String.valueOf(snapshot.child("price_s").getValue());
                String priceMGetter = String.valueOf(snapshot.child("price_m").getValue());
                String priceLGetter = String.valueOf(snapshot.child("price_l").getValue());
                prodname.setText(nameGetter);
                proddesc.setText(descGetter);
                prodprice.setText("S - "+priceSGetter+" PHP\nM - "+priceMGetter+" PHP\nL - "+priceLGetter+" PHP");


                // quantity = Integer.parseInt(spinner_quantity.getSelectedItem().toString());
                Log.d("Quantity",String.valueOf(quantity[0]));


                if (size[0] == 1) {
                    price[0] = Double.parseDouble(priceSGetter);
                } else if (size[0] == 2) {
                    price[0] = Double.parseDouble(priceMGetter);
                } else if (size[0] == 3) {
                    price[0] = Double.parseDouble(priceLGetter);
                }

//                LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//                priceTotal.setLayoutParams(layoutParams);
                priceTotal.setText("TOTAL = " + price[0]*quantity[0] + " PHP");



                Button btn_addcart = findViewById(R.id.button_addtocart);
                btn_addcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                Toast.makeText(getBaseContext()," Size: "+ size[0] + "\n Quantity: " + quantity[0] + "\n Customer ID: " + custID + "\n Product ID: " + prodID,Toast.LENGTH_SHORT).show();

//                        String sizer = "";
//                        if (size[0] == 1) {
//                            price[0] = Double.parseDouble(priceSGetter);
//                            sizer = "S";
//                        } else if (size[0] == 2) {
//                            price[0] = Double.parseDouble(priceMGetter);
//                            sizer = "M";
//                        } else if (size[0] == 3) {
//                            price[0] = Double.parseDouble(priceLGetter);
//                            sizer = "L";
//                        }

                        priceTotal.setText("TOTAL = " + price[0]*quantity[0] + " PHP");

//                        Toast.makeText(getBaseContext()," Size: "+ size[0] + "\n Sizer: "+ sizer + " \n Price: " + price[0] + "PHP \n Quantity: " + quantity[0] + "\n Customer ID: " + custID + "\n Product ID: " + prodID + "\n Total: " + price[0]*quantity[0] + " PHP" ,Toast.LENGTH_SHORT).show();

                        db1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int i;
                                String sizer = "";
                                if (size[0] == 1) {
                                    price[0] = Double.parseDouble(priceSGetter);
                                    sizer = "S";
                                } else if (size[0] == 2) {
                                    price[0] = Double.parseDouble(priceMGetter);
                                    sizer = "M";
                                } else if (size[0] == 3) {
                                    price[0] = Double.parseDouble(priceLGetter);
                                    sizer = "L";
                                }
                                int checkVal = 0;
                                for (i=0;i<snapshot.getChildrenCount()*2;i++) {
                                    if (snapshot.child(String.valueOf(i)).getChildrenCount() == 0 ) {
                                        db.child(String.valueOf(i)).setValue(new Cart(prodID,quantity[0],sizer,price[0],price[0]*quantity[0],prodName));
                                        Toast.makeText(getBaseContext(),"Added to Cart!",Toast.LENGTH_SHORT).show();
                                        finish();
                                        break;

//                                        System.out.println(snapshot.getValue());
//                                        Boolean thisis1 = (String.valueOf(snapshot.child(String.valueOf(i)).child("prod_id").getValue()).equals(String.valueOf(prodID)));
//                                        Boolean thisis2 = (String.valueOf(snapshot.child(String.valueOf(i)).child("teasize").getValue()).equals(sizer));
//                                        System.out.println(" Con 1: " + thisis1 + "\n Con 2:" + thisis2);
//                                        if (!(String.valueOf(snapshot.child(String.valueOf(i)).child("prod_id").getValue()).equals(String.valueOf(prodID)) &&
//                                                String.valueOf(snapshot.child(String.valueOf(i)).child("teasize").getValue()).equals(sizer) )) {
//                                            db.child(String.valueOf(i)).setValue(new Cart(prodID,quantity[0],sizer,price[0],price[0]*quantity[0],prodName));
//                                            Toast.makeText(getBaseContext(),"Added to Cart!",Toast.LENGTH_SHORT).show();
//                                            finish();
//                                            break;
//                                        } else {
//                                            double dbprice = 0; // Double.parseDouble(String.valueOf(snapshot.child(String.valueOf(i)).child("totalprice").getValue()));
//                                            int dbquantity = 0; // Integer.parseInt(String.valueOf(snapshot.child(String.valueOf(i)).child("quantity").getValue()));
//                                            FirebaseDatabase.getInstance().getReference().child("Cart").child(custID).child(String.valueOf(i)).child("quantity").setValue(dbquantity+Integer.parseInt(String.valueOf(quantity)));
//                                            FirebaseDatabase.getInstance().getReference().child("Cart").child(custID).child(String.valueOf(i)).child("totalprice").setValue(dbprice+Double.parseDouble(String.valueOf(price[0]*quantity[0])));
//                                            Toast.makeText(getBaseContext(),"Added to Cart!",Toast.LENGTH_SHORT).show();
//                                            finish();
//                                        }
//                                        String teasizer = String.valueOf(snapshot.child("teasize").getValue());
//                                        String prodid = String.valueOf(snapshot.child("prod_id").getValue());
//                                        if (sizer.equals(teasizer) && prodid.equals(String.valueOf(prodID))) {
//                                            checkVal = 1;
//                                        }
//                                        System.out.println(i+" "+ teasizer + " " + prodid);

                                    }
//                                    if (checkVal == 1) {
//                                        Toast.makeText(getBaseContext(),"Product Exists",Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(getBaseContext(),"Product not Exists",Toast.LENGTH_SHORT).show();
//                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }
}