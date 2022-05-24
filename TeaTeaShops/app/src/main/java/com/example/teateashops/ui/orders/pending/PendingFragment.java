package com.example.teateashops.ui.orders.pending;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teateashops.R;
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



        return root;
    }
}