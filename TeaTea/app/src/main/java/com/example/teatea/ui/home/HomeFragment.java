package com.example.teatea.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teatea.AddToCartActivity;
import com.example.teatea.Customer;
import com.example.teatea.HomeAdapter;
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
import java.util.concurrent.ThreadLocalRandom;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private static int i;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView reclist = root.findViewById(R.id.itemlist);

        List<MilkteaItem> items = new ArrayList<>();

//        items.add(new MilkteaItem("Plain","Milktea shoppers"));
//        items.add(new MilkteaItem("Chocolate","Chocolatey Shop"));
//        items.add(new MilkteaItem("Milky","Taste Teas"));
//        items.add(new MilkteaItem("Cream","Milkies"));
//
//        listener = new HomeAdapter.RecyclerViewClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Intent addcart_intent = new Intent(getActivity(),AddToCartActivity.class);
//                addcart_intent.putExtra("Product Name",items.get(position).getProdName());
//                Log.d("Position",String.valueOf(position));
//                Log.d("Item Name",items.get(position).getProdName());
//                Toast.makeText(getContext(), "Item Name: "+ items.get(position).getProdName(), Toast.LENGTH_SHORT).show();
//                startActivity(addcart_intent);
//
//            }
//        };
//
//        HomeAdapter adapter = new HomeAdapter(items,listener);
//
//        reclist.setAdapter(adapter);
//
//        reclist.setLayoutManager(new LinearLayoutManager(getContext()));


        db.addValueEventListener(new ValueEventListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HomeAdapter.RecyclerViewClickListener listener;
                items.clear();
//                int numShuff = ThreadLocalRandom.current().nextInt(1, 9999);
//                int shuffler = (int) (numShuff%snapshot.child("Products").getChildrenCount()+1);
                for (i=0;i<snapshot.child("Products").getChildrenCount()*5;i++) {
//                    while (shuffler==numShuff%snapshot.child("Products").getChildrenCount()+1) {
//                        numShuff = ThreadLocalRandom.current().nextInt(1, 9999);
//                    }
//                    shuffler = (int) (numShuff%snapshot.child("Products").getChildrenCount()+1);
//                    if (snapshot.child("Products").child(String.valueOf(shuffler)).exists()) {
//                        String nameGetter = String.valueOf(snapshot.child("Products").child(String.valueOf(shuffler)).child("name").getValue());
//                        items.add(new MilkteaItem(nameGetter,"Milkie Shop",String.valueOf(shuffler)));
//                    }
                    if (snapshot.child("Products").child(String.valueOf(i)).exists()) {
                        String nameGetter = String.valueOf(snapshot.child("Products").child(String.valueOf(i)).child("name").getValue());
                        String shopname = String.valueOf(snapshot.child("Shops").child(String.valueOf(snapshot.child("Products").child(String.valueOf(i)).child("shop_id").getValue())).child("shop_name").getValue());
                        items.add(new MilkteaItem(nameGetter,shopname,String.valueOf(i)));
                    }
                }
                listener = new HomeAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        MainMenu mainMenu = (MainMenu) getActivity();
                        Customer cust = mainMenu.getCustomer();
                        Intent addcart_intent = new Intent(getActivity(),AddToCartActivity.class);
                        addcart_intent.putExtra("productid",items.get(position).getId());
                        addcart_intent.putExtra("custid",cust.id);
                        addcart_intent.putExtra("prodname",items.get(position).getProdName());
                        addcart_intent.putExtra("shopname",items.get(position).shop_from);
//                        Log.d("Position",String.valueOf(position));
//                        Log.d("Item ID",items.get(position).getId());
                        Log.d("Product Name",items.get(position).getProdName());
                         Toast.makeText(getContext(), "Item ID: "+ items.get(position).getId(), Toast.LENGTH_SHORT).show();
                        startActivity(addcart_intent);
                    }
                };
                reclist.setAdapter(new HomeAdapter(items,listener));
                reclist.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });



        return root;
    }



}