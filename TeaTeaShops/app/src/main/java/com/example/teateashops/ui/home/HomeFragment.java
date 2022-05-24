package com.example.teateashops.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teateashops.NotificationAdapter;
import com.example.teateashops.NotificationItem;
import com.example.teateashops.NotificationViewActivity;
import com.example.teateashops.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


//        RecyclerView list_notif = root.findViewById(R.id.notiflist_home);
//        NotificationAdapter.RecyclerViewClickListener listener;
//
//        List<NotificationItem> listitems = new ArrayList<>();
//
//        listitems.add(new NotificationItem("You got a Customer!","600.00 PHP","Martin From Mars"));
//        listitems.add(new NotificationItem("You got a Customer!","400.00 PHP","Venus and Stars"));
//
//        listener = new NotificationAdapter.RecyclerViewClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Intent notifview_act = new Intent(getActivity(), NotificationViewActivity.class);
//                startActivity(notifview_act);
//            }
//        };
//
//        NotificationAdapter notifAdapt = new NotificationAdapter(listitems,listener);
//        list_notif.setAdapter(notifAdapt);
//        list_notif.setLayoutManager(new LinearLayoutManager(getContext()));

        BarChart barChartHome = root.findViewById(R.id.barChartHome);

        ArrayList<BarEntry> sales = new ArrayList<>();
        int i;
        for (i=0;i<7;i++) {
            sales.add(new BarEntry(i+1,i));
        }

        BarDataSet barDataSet = new BarDataSet(sales,"Number of Sales");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChartHome.setFitBars(true);
        barChartHome.setData(barData);
        barChartHome.getDescription().setText("Number of Sales in Each Day");
        barChartHome.animateY(2000);

        return root;
    }
}