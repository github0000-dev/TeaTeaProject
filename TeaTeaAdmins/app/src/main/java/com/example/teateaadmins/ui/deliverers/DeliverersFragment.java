package com.example.teateaadmins.ui.deliverers;

import androidx.lifecycle.ViewModelProvider;

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

import com.example.teateaadmins.R;
import com.example.teateaadmins.ViewDelivererActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeliverersFragment extends Fragment {

    private DeliverersViewModel mViewModel;


    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Deliverers");
    private static int i;
    private String delID;

    public static DeliverersFragment newInstance() {
        return new DeliverersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_deliverers, container, false);

        RecyclerView reclist = root.findViewById(R.id.reclist_deliverers);
        List<Deliverer> list = new ArrayList<>();

//        RecyclerView reclist = root.findViewById(R.id.reclist_deliverers);
//        List<Customer> list = new ArrayList<>();
//        Customer user2 = new Customer("Delivery Guy","John Ramos");
//
//        list.add(user2);
//
//        CustomerAdapter useradapter = new CustomerAdapter(list);
//        reclist.setAdapter(useradapter);
//        reclist.setLayoutManager(new LinearLayoutManager(getContext()));

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DelivererAdapter.RecyclerViewClickListener listener;
                list.clear();
                for (i=0;i<snapshot.getChildrenCount();i++) {
                    if (snapshot.child(String.valueOf(i)).exists()) {
                        String nameGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("name").getValue());
                        String addGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("address").getValue());
                        delID = String.valueOf(i);
                        list.add(new Deliverer(nameGetter,addGetter,String.valueOf(i)));
                    }
                }
                listener = new DelivererAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View v, int position) {
//                        Intent addcart_intent = new Intent(getActivity(),AddToCartActivity.class);
//                        addcart_intent.putExtra("ProductID",items.get(position).getId());
//                        Log.d("Position",String.valueOf(position));
//                        Log.d("Item ID",items.get(position).getId());
//                        Toast.makeText(getContext(), "Item ID: "+ items.get(position).getId(), Toast.LENGTH_SHORT).show();
//                        startActivity(addcart_intent);

                        Intent viewDelivererAct = new Intent(getActivity(), ViewDelivererActivity.class);
                        viewDelivererAct.putExtra("delivererid",list.get(position).getDelID());
                        startActivity(viewDelivererAct);
                    }
                };
                reclist.setAdapter(new DelivererAdapter(list,listener));
                reclist.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DeliverersViewModel.class);
        // TODO: Use the ViewModel
    }


}