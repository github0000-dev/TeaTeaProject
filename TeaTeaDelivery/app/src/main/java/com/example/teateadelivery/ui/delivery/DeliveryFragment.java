package com.example.teateadelivery.ui.delivery;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teateadelivery.R;


public class DeliveryFragment extends Fragment {

    private DeliveryViewModel mViewModel;



    public static DeliveryFragment newInstance() {
        return new DeliveryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_delivery, container, false);



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DeliveryViewModel.class);
        // TODO: Use the ViewModel
    }

}