package com.example.teateashops.ui.orders.readytodeliver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teateashops.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadyToDeliverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadyToDeliverFragment extends Fragment {

    public ReadyToDeliverFragment() {
        // Required empty public constructor
    }

    public static ReadyToDeliverFragment newInstance() {
        return new ReadyToDeliverFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_orders_readytodeliver, container, false);



        return root;
    }
}