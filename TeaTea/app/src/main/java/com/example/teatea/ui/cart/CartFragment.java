package com.example.teatea.ui.cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.teatea.Cart;
import com.example.teatea.CheckoutActivity;
import com.example.teatea.Customer;
import com.example.teatea.MainMenu;
import com.example.teatea.MilkteaItem;
import com.example.teatea.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private CartViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        Button checkoutbtn = view.findViewById(R.id.btn_checkoutproc);

        RecyclerView listView = view.findViewById(R.id.cartList);

        MainMenu main = (MainMenu) getActivity();
        Customer cust = main.getCustomer();

//        System.out.println("Customer ID: " + cust.id);

        List<Cart> items = new ArrayList<>();

//        items.clear();
//        items.add(new Cart("Plain","M",2,140.00));
//        items.add(new Cart("Soft Gellatio","M",1,70.00));
//        items.add(new Cart("Chocolat","S",1,65.00));
//        items.add(new Cart("Wacko Mole","L",3,225.00));
//
//
//
//        CartAdapter adapter = new CartAdapter(items);
//
//        listView.setAdapter(adapter);
//
//
//
//        listView.setLayoutManager(new LinearLayoutManager(getContext()));


//      public Cart(int prod_id,int quantity,String teasize,double price,double totalprice,String name){
//        this.prod_id = prod_id;
//        this.quantity = quantity;
//        this.teasize = teasize;
//        this.price = price;
//        this.totalprice = totalprice;
//        this.name = name;
//    }


        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                int i;
                if (!snapshot.exists()) {
                    Toast.makeText(getActivity(),"Cart is Empty!",Toast.LENGTH_SHORT).show();
                }
                for (i=0;i<snapshot.child("Cart").child(cust.id).getChildrenCount()+1;i++) {
                    if (snapshot.child("Cart").child(cust.id).child(String.valueOf(i)).hasChildren()) {
                        String name = String.valueOf(snapshot.child("Cart").child(cust.id).child(String.valueOf(i)).child("name").getValue());
                        String size = String.valueOf(snapshot.child("Cart").child(cust.id).child(String.valueOf(i)).child("teasize").getValue());
                        int quantity  = Integer.parseInt(String.valueOf(snapshot.child("Cart").child(cust.id).child(String.valueOf(i)).child("quantity").getValue()));
                        double price  = Double.parseDouble(String.valueOf(snapshot.child("Cart").child(cust.id).child(String.valueOf(i)).child("price").getValue()));
                        double totalprice = Double.parseDouble(String.valueOf(snapshot.child("Cart").child(cust.id).child(String.valueOf(i)).child("totalprice").getValue()));
                        String shop_id = String.valueOf(snapshot.child("Shops").child(String.valueOf(snapshot.child("Products").child(String.valueOf(i)).child("shop_id").getValue())).child("shop_name").getKey());
                        items.add(new Cart(i,quantity,size,price,totalprice,name,shop_id));
                    }
                }
                CartAdapter.RecycleViewDeleteListener listener = new CartAdapter.RecycleViewDeleteListener() {
                    @Override
                    public void onItemRemove(View v, int position) {
                        Toast.makeText(getActivity(),items.get(position).name +" deleted.",Toast.LENGTH_SHORT).show();
                        db.child("Cart").child(cust.id).child(String.valueOf(position)).removeValue();
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
                listView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
            }
        });

        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items.size() == 0) {
                    Toast.makeText(getActivity(),"Cannot Check out if its empty.",Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent_checkout = new Intent (getActivity(),CheckoutActivity.class);
                    intent_checkout.putExtra("custid_checkout",cust.id);
                    startActivity(intent_checkout);
                }
            }
        });

        return view;

    }


}