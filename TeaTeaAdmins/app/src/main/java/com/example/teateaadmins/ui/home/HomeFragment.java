package com.example.teateaadmins.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teateaadmins.HomeAdapter;
import com.example.teateaadmins.MainActivity;
import com.example.teateaadmins.MainMenu;
import com.example.teateaadmins.Notification;
import com.example.teateaadmins.R;
import com.example.teateaadmins.Customer;
import com.example.teateaadmins.CustomerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView reclist = root.findViewById(R.id.reclist_home);
        List<Notification> list = new ArrayList<>();
        HomeAdapter.ItemOnClickListener delete,view;

        list.add(new Notification("You Got a New Customer!","Dom Mejares"));
        list.add(new Notification("Verify The Delivery Man","Ramz Ramos"));
        list.add(new Notification("You Got a New Customer!","Dom Mejares"));
        list.add(new Notification("You Got a New Customer!","Dom Mejares"));
        list.add(new Notification("Verify The Delivery Man","Ramz Ramos"));

        delete = new HomeAdapter.ItemOnClickListener() {
            @Override
            public void onClick(View v, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are You sure to delete "+ list.get(position).name_user +" on your notification list?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing to see dude
                    }
                });
                builder.create();
                builder.show();
            }
        };

        view = new HomeAdapter.ItemOnClickListener() {
            @Override
            public void onClick(View v, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("NOW VIEWING: "+ list.get(position).name_user +".");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing to see dude
                    }
                });
                builder.create();
                builder.show();
            }
        };

        reclist.setAdapter(new HomeAdapter(list,delete,view));
        reclist.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

}