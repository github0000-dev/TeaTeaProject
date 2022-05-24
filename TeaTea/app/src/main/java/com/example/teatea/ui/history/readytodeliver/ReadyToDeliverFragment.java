package com.example.teatea.ui.history.readytodeliver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teatea.Cart;
import com.example.teatea.MilkteaItem;
import com.example.teatea.R;
import com.example.teatea.ui.cart.CartAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadyToDeliverFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ReadyToDeliverFragment extends Fragment {


    public static ReadyToDeliverFragment newInstance() {
        ReadyToDeliverFragment fragment = new ReadyToDeliverFragment();


        return fragment;
    }

    public ReadyToDeliverFragment() {
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

        items.clear();
        items.add(new Cart("Plain","M",2,140.00));
        items.add(new Cart("Soft Gellatio","M",1,70.00));
        items.add(new Cart("Chocolat","S",1,65.00));
        items.add(new Cart("Wacko Mole","L",3,225.00));

        CartAdapter adapter = new CartAdapter(items);
        listView.setAdapter(adapter);

        listView.setLayoutManager(new LinearLayoutManager(getContext()));


        return root;
    }
}