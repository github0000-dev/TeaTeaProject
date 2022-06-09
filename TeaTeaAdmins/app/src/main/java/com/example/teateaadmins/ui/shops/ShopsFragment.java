package com.example.teateaadmins.ui.shops;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.teateaadmins.R;
import com.example.teateaadmins.ViewCustomerActivity;
import com.example.teateaadmins.ViewShopActivity;
import com.example.teateaadmins.ui.customers.Customer;
import com.example.teateaadmins.ui.customers.CustomerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopsFragment extends Fragment {

    private ShopsViewModel mViewModel;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    public static ShopsFragment newInstance() {
        return new ShopsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shops, container, false);

        RecyclerView reclist = root.findViewById(R.id.reclist_shops);
        List<Shops> list = new ArrayList<>();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {
                DataSnapshot snapshot = snap.child("Staffs");
                ShopAdapter.RecyclerViewClickListener listener;
                list.clear();
                int i;
                for (i = 0; i < snapshot.getChildrenCount()+1; i++) {
                    if (snapshot.child(String.valueOf(i)).exists()) {
                        String nameGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("name").getValue());
                        String addGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("address").getValue());
                        String vendID = String.valueOf(i);
                        list.add(new Shops(nameGetter,addGetter,String.valueOf(i)));
                    }
                }
                listener = new ShopAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View v, int position) {
//                        Intent addcart_intent = new Intent(getActivity(),AddToCartActivity.class);
//                        addcart_intent.putExtra("ProductID",items.get(position).getId());
//                        Log.d("Position",String.valueOf(position));
//                        Log.d("Item ID",items.get(position).getId());
//                        Toast.makeText(getContext(), "Item ID: "+ items.get(position).getId(), Toast.LENGTH_SHORT).show();
//                        startActivity(addcart_intent);

                        Intent viewShopAct = new Intent(getActivity(), ViewShopActivity.class);
                        viewShopAct.putExtra("shopid", list.get(position).shopid);
                        startActivity(viewShopAct);
                    }
                };

                reclist.setAdapter(new ShopAdapter(list,listener));
                reclist.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                Log.e("error",String.valueOf(error));
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShopsViewModel.class);
        // TODO: Use the ViewModel
    }

}