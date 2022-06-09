package com.example.teateaadmins.ui.customers;

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
import com.example.teateaadmins.ViewCustomerActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomersFragment extends Fragment {

    private CustomersViewModel mViewModel;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Customers");
    private static int i;
    private String custID;

    public static CustomersFragment newInstance() {
        return new CustomersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_customers, container, false);

        RecyclerView reclist = root.findViewById(R.id.reclist_customer);
        List<Customer> list = new ArrayList<>();

//        User user1 = new User("Customer","Ian Moreno");
//        list.add(user1);
//
//        UserAdapter useradapter = new UserAdapter(list);
//        reclist.setAdapter(useradapter);
//        reclist.setLayoutManager(new LinearLayoutManager(getContext()));

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CustomerAdapter.RecyclerViewClickListener listener;
                list.clear();
                for (i=0;i<snapshot.getChildrenCount();i++) {
                    if (snapshot.child(String.valueOf(i)).exists()) {
                        String nameGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("name").getValue());
                        String addGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("address").getValue());
                        custID = String.valueOf(i);
                        list.add(new Customer(nameGetter,addGetter,String.valueOf(i)));
                    }
                }
                listener = new CustomerAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View v, int position) {
//                        Intent addcart_intent = new Intent(getActivity(),AddToCartActivity.class);
//                        addcart_intent.putExtra("ProductID",items.get(position).getId());
//                        Log.d("Position",String.valueOf(position));
//                        Log.d("Item ID",items.get(position).getId());
//                        Toast.makeText(getContext(), "Item ID: "+ items.get(position).getId(), Toast.LENGTH_SHORT).show();
//                        startActivity(addcart_intent);

                        Intent viewCustomerAct = new Intent(getActivity(), ViewCustomerActivity.class);
                        viewCustomerAct.putExtra("customerid",list.get(position).getCustID());
                        startActivity(viewCustomerAct);
                    }
                };
                reclist.setAdapter(new CustomerAdapter(list,listener));
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
        mViewModel = new ViewModelProvider(this).get(CustomersViewModel.class);
        // TODO: Use the ViewModel
    }

}