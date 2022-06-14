package com.example.teatea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
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

    private DatabaseReference db;


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
        String shopid = itemDetails.getStringExtra("shopid");

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

        quantity[0] = 1;
        size[0] = 1;

        final double[] price = new double[1];



//        Spinner spinner_size = findViewById(R.id.spinner_size_addtocart);
//        ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,
//                R.array.itemsize_cart, android.R.layout.simple_spinner_item);
//        adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_size.setAdapter(adapter_size);
//        spinner_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Object item = parent.getItemAtPosition(position);
//                if (String.valueOf(item).equals("S")) {
//                    size[0] = 1;
//                } else if (String.valueOf(item).equals("M")) {
//                    size[0] = 2;
//                } else if (String.valueOf(item).equals("L")) {
//                    size[0] = 3;
//                }
//                priceTotal.setText("TOTAL = " + price[0]*quantity[0] + " PHP");
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//        Spinner spinner_quantity = findViewById(R.id.spinner_quantity_addtocart);
//        ArrayAdapter<CharSequence> adapter_quantity = ArrayAdapter.createFromResource(this,
//                R.array.quantity_cart, android.R.layout.simple_spinner_item);
//        adapter_quantity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_quantity.setAdapter(adapter_quantity);
//        spinner_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Object item = parent.getItemAtPosition(position);
////                Log.d("Item",String.valueOf(item));
//
//                quantity[0] = Integer.parseInt(String.valueOf(item));
//                priceTotal.setText("TOTAL = " + price[0]*quantity[0] + " PHP");
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//

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




                db = FirebaseDatabase.getInstance().getReference().child("Cart");

                DatabaseReference dbGetProd = FirebaseDatabase.getInstance().getReference().child("Products").child(String.valueOf(prodID));

                Spinner spinner_size = findViewById(R.id.spinner_size_addtocart);
                ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(AddToCartActivity.this,
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
                        if (size[0] == 1) {
                            price[0] = Double.parseDouble(priceSGetter);
                        } else if (size[0] == 2) {
                            price[0] = Double.parseDouble(priceMGetter);
                        } else if (size[0] == 3) {
                            price[0] = Double.parseDouble(priceLGetter);
                        }
                        priceTotal.setText("TOTAL = " + price[0]*quantity[0] + " PHP");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                Spinner spinner_quantity = findViewById(R.id.spinner_quantity_addtocart);
                ArrayAdapter<CharSequence> adapter_quantity = ArrayAdapter.createFromResource(AddToCartActivity.this,
                        R.array.quantity_cart, android.R.layout.simple_spinner_item);
                adapter_quantity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_quantity.setAdapter(adapter_quantity);
                spinner_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object item = parent.getItemAtPosition(position);
//                Log.d("Item",String.valueOf(item));

                        quantity[0] = Integer.parseInt(String.valueOf(item));
                        priceTotal.setText("TOTAL = " + price[0]*quantity[0] + " PHP");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




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

                        db.addListenerForSingleValueEvent(new ValueEventListener() {
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
                                for (i=0;i<snapshot.child(custID).getChildrenCount()+1;i++) {
                                    if (snapshot.child(custID).child(String.valueOf(i)).getChildrenCount() == 0 ) {
                                        db.child(custID).child(String.valueOf(i)).setValue(new Cart(prodID,quantity[0],sizer,price[0],price[0]*quantity[0],prodName));
                                        Toast.makeText(getBaseContext(),"Added to Cart!",Toast.LENGTH_SHORT).show();
                                        finish();
                                        break;
                                    }
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