package com.example.teateashops.ui.shop;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teateashops.MainMenu;
import com.example.teateashops.R;
import com.example.teateashops.Shop;
import com.example.teateashops.Vendor;

public class ShopFragment extends Fragment {

    private ShopViewModel mViewModel;

    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        MainMenu main = (MainMenu) getActivity();
        Shop shop = main.getShop();
        Vendor vend = main.getVendor();


        TextView shopname = root.findViewById(R.id.shopNameAbout);
        TextView shopdesc = root.findViewById(R.id.shopDescAbout);
        TextView shopowner = root.findViewById(R.id.shopOwnerAbout);
        TextView shopadd = root.findViewById(R.id.shopAddAbout);

        shopname.setText(shop.shop_name);
        shopdesc.setText(shop.shop_desc);
        shopadd.setText(shop.shop_address);
        shopowner.setText(vend.name+", the "+shop.shop_position);







        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        // TODO: Use the ViewModel
    }

}