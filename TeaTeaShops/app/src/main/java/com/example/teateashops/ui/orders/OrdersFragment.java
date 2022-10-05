package com.example.teateashops.ui.orders;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teateashops.R;
import com.example.teateashops.ui.orders.onmaking.OnMakingFragment;
import com.example.teateashops.ui.orders.pending.PendingFragment;
import com.example.teateashops.ui.orders.readytodeliver.ReadyToDeliverFragment;

public class OrdersFragment extends Fragment {

    private OrdersViewModel mViewModel;


    public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_orders, container, false);

        ViewPager pagerHistory = root.findViewById(R.id.pager_orders);
        pagerHistory.setAdapter(new myPagerAdapter(getChildFragmentManager()));

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
    }

    private class myPagerAdapter extends FragmentPagerAdapter {

        private String[] titles = {"Pending","On Making","Done Prepared"};

        public myPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return PendingFragment.newInstance();
                case 1:
                    return OnMakingFragment.newInstance();
                case 2:
                    return ReadyToDeliverFragment.newInstance();
                default:
                    return PendingFragment.newInstance();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

}