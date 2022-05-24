package com.example.teatea.ui.cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teatea.CheckoutActivity;
import com.example.teatea.MilkteaItem;
import com.example.teatea.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.example.teatea.MainMenu.menu_act;

public class CartFragment2 extends Fragment {

    private CartViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        Button checkoutbtn = view.findViewById(R.id.btn_checkoutproc);

        RecyclerView listView = view.findViewById(R.id.cartList);




        List<MilkteaItem> items = new ArrayList<>();

        CartAdapter2.DeleteClickerListener listener;

        items.clear();
        items.add(new MilkteaItem("Plain","M","2","140.00"));
        items.add(new MilkteaItem("Soft Gellatio","M","1","70.00"));
        items.add(new MilkteaItem("Chocolat","S","1","65.00"));
        items.add(new MilkteaItem("Wacko Mole","L","3","225.00"));

        listener = new CartAdapter2.DeleteClickerListener() {
            @Override
            public void onRemoveItem(View v, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure to remove "+ items.get(position).name +"?");
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing to see here desu
                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        items.remove(position);
                    }
                });
                builder.create();
                builder.show();
            }
        };

        CartAdapter2 adapter = new CartAdapter2(items,listener);

        listView.setAdapter(adapter);



        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_checkout = new Intent (getActivity(),CheckoutActivity.class);
                startActivity(intent_checkout);
            }
        });

        return view;

    }


}