package com.example.teateashops.ui.products;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

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

import com.example.teateashops.AddProductActivity;
import com.example.teateashops.MainMenu;
import com.example.teateashops.ProductAdapter;
import com.example.teateashops.MilkteaItem;
import com.example.teateashops.R;
import com.example.teateashops.Shop;
import com.example.teateashops.Vendor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    private ProductsViewModel mViewModel;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Products");
    private static int i;

    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_products, container, false);
        Button addButton = (Button) root.findViewById(R.id.addProductButton);


        MainMenu main = (MainMenu) getActivity();
        // Vendor vendor = main.getVendor();
        Shop shop = main.getShop();

        String shop_id = shop.id;

        RecyclerView reclist = root.findViewById(R.id.reclist_products);

        List<MilkteaItem> list = new ArrayList<>();

//        list.add(new MilkteaItem("Lipton Tea","The Best","60.00","65.00","70.00"));
//        list.add(new MilkteaItem("Tang Tea","The Worst","60.00","65.00","70.00"));
//        list.add(new MilkteaItem("Dark Tea","The Unknown as Hell","60.00","65.00","70.00"));
//        list.add(new MilkteaItem("Angel Tea","The Heavenly as you could imagine","60.00","65.00","70.00"));
//
//        reclist.setAdapter(new ProductAdapter(list));
//
//        reclist.setLayoutManager(new LinearLayoutManager(getContext()));

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProductAdapter.DeleteItemClickListener listener;
                list.clear();
                for (i=0;i<snapshot.getChildrenCount()+1;i++) {
                    if (snapshot.child(String.valueOf(i)).hasChildren() && String.valueOf(shop_id).equals(String.valueOf(snapshot.child(String.valueOf(i)).child("shop_id").getValue()))) {
                        String nameGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("name").getValue());
                        String descGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("description").getValue());
                        String priceSGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("price_s").getValue());
                        String priceMGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("price_m").getValue());
                        String priceLGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("price_l").getValue());
//                        double priceSGetter = Double.parseDouble(String.valueOf(snapshot.child(String.valueOf(i)).child("price_s").getValue()));
//                        double priceMGetter =  Double.parseDouble(String.valueOf(snapshot.child(String.valueOf(i)).child("price_m").getValue()));
//                        double priceLGetter =  Double.parseDouble(String.valueOf(snapshot.child(String.valueOf(i)).child("price_l").getValue()));
                        list.add(new MilkteaItem(nameGetter,descGetter,priceSGetter,priceMGetter,priceLGetter,i));
                    }
                }
                listener = new ProductAdapter.DeleteItemClickListener() {
                    @Override
                    public void onItemRemove(View v, int position) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Are You sure to delete "+ list.get(list.get(position).prod_id).name +" on your product list?");
                        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                int pos;
//                                for (pos=position;pos<snapshot.getChildrenCount()+1;pos++) {
//                                    db.child(String.valueOf(pos)).child("name").setValue(snapshot.child(String.valueOf(pos+1)).child("name").getValue());
//                                    db.child(String.valueOf(pos)).child("description").setValue(snapshot.child(String.valueOf(pos+1)).child("description").getValue());
//                                    db.child(String.valueOf(pos)).child("datereleased").setValue(snapshot.child(String.valueOf(pos+1)).child("datereleased").getValue());
//                                    db.child(String.valueOf(pos)).child("price_s").setValue(snapshot.child(String.valueOf(pos+1)).child("price_s").getValue());
//                                    db.child(String.valueOf(pos)).child("price_m").setValue(snapshot.child(String.valueOf(pos+1)).child("price_m").getValue());
//                                    db.child(String.valueOf(pos)).child("price_l").setValue(snapshot.child(String.valueOf(pos+1)).child("price_l").getValue());
//                                }

                                // db.child(String.valueOf(snapshot.getChildrenCount()+1)).removeValue();

                                db.child(String.valueOf(list.get(position).prod_id)).removeValue();

                            }
                        });
                        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // nothing to see dude
                            }
                        });
                        builder.create();
                        builder.show();
                    }
                };
                reclist.setAdapter(new ProductAdapter(list,listener));
                reclist.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_addProd = new Intent(getActivity(), AddProductActivity.class);
                intent_addProd.putExtra("shop_id",shop_id);
                startActivity(intent_addProd);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        // TODO: Use the ViewModel
    }



}