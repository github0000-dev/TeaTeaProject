package com.example.teateashops.ui.orders.pending;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.teateashops.R;
import com.example.teateashops.ui.orders.OrderAdapter;
import com.example.teateashops.ui.orders.OrderItem;
import com.google.common.collect.ArrayTable;

import java.util.ArrayList;
import java.util.List;

public class PendingFragment extends Fragment {
    private String mParam1;
    private String mParam2;

    public PendingFragment() {
        // Required empty public constructor
    }
    public static PendingFragment newInstance() {
        return new PendingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_orders_pending, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.rec_pending);

        List<OrderItem> list = new ArrayList<>();
        list.add(new OrderItem("My Tea",10.00,4));
        list.add(new OrderItem("Ian Tea",20.00,3));
        list.add(new OrderItem("This Tea",15.00,1));
        OrderAdapter.OnClickListener listener = new OrderAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int position) {
//                Toast.makeText(getActivity(), "You have clicked "+list.get(position).name+".", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setPositiveButton()
//                builder.create();
//                builder.show();
            }
        };

        recyclerView.setAdapter(new OrderAdapter(list,listener));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return root;
    }
}