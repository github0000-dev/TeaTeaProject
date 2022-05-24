package com.example.teateaadmins.ui.shops;

import androidx.lifecycle.ViewModelProvider;

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
import com.example.teateaadmins.Customer;
import com.example.teateaadmins.CustomerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopsFragment extends Fragment {

    private ShopsViewModel mViewModel;

    public static ShopsFragment newInstance() {
        return new ShopsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shops, container, false);

        RecyclerView reclist = root.findViewById(R.id.reclist_shops);
        List<Customer> list = new ArrayList<>();
//
//        Customer user3 = new Customer("Shop Vendor","Domz Mejares");
//
//        list.add(user3);
//
//        CustomerAdapter useradapter = new CustomerAdapter(list);
//        reclist.setAdapter(useradapter);
//        reclist.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShopsViewModel.class);
        // TODO: Use the ViewModel
    }

}