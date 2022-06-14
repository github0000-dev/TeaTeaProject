package com.example.teatea.ui.history.onmaking;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teatea.Cart;
import com.example.teatea.Customer;
import com.example.teatea.MainMenu;
import com.example.teatea.R;
import com.example.teatea.ui.cart.CartAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnMakingFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class OnMakingFragment extends Fragment {


    public static OnMakingFragment newInstance() {
        OnMakingFragment fragment = new OnMakingFragment();


        return fragment;
    }

    public OnMakingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history_readytodeliver, container, false);

        RecyclerView listView = root.findViewById(R.id.recyclerReadyToDeliver);





        List<Cart> items = new ArrayList<>();


        MainMenu main = (MainMenu) getActivity();
        Customer cust = main.getCustomer();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot snap = snapshot.child("Orders");
                for (int i=0;i<snapshot.child("Orders").getChildrenCount()+1;i++) {
                    DataSnapshot snapCust = snap.child(String.valueOf(i)).child("cust_id");
                    DataSnapshot snapStatus = snap.child(String.valueOf(i)).child("status");
                    if (String.valueOf(snapCust.getValue()).equals(cust.id) &&
                            String.valueOf(snapStatus.getValue()).equals("onmaking")) {
                        Log.d("Count",String.valueOf(i));
                        Log.d("Count of Children",String.valueOf(snap.child(String.valueOf(i)).getChildrenCount()));
                        for (int a=0;a<snap.child(String.valueOf(i)).getChildrenCount()-3;a++) {
                            if (snap.child(String.valueOf(a)).hasChildren()) {
                                String name = String.valueOf(snap.child(String.valueOf(i)).child(String.valueOf(a)).child("name").getValue());
                                String size = String.valueOf(snap.child(String.valueOf(i)).child(String.valueOf(a)).child("teasize").getValue());
                                int quantity  = Integer.parseInt(String.valueOf(snap.child(String.valueOf(i)).child(String.valueOf(a)).child("quantity").getValue()));
                                double price  = Double.parseDouble(String.valueOf(snap.child(String.valueOf(i)).child(String.valueOf(a)).child("price").getValue()));
                                double totalprice = Double.parseDouble(String.valueOf(snap.child(String.valueOf(i)).child(String.valueOf(a)).child("totalprice").getValue()));
                                String shop_id = String.valueOf(snapshot.child("Shops").child(String.valueOf(snapshot.child("Products").child(String.valueOf(i)).child("shop_id").getValue())).child("shopname").getValue());
                                items.add(new Cart(i,quantity,size,price,totalprice,name,shop_id));
                            }
                        }
                    }

                }
                CartAdapter adapter = new CartAdapter(items);
                listView.setAdapter(adapter);
                listView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return root;
    }
}