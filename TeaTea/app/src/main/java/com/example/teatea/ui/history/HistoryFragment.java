package com.example.teatea.ui.history;

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

import com.example.teatea.R;
import com.example.teatea.ui.history.finished.FinishedFragment;
import com.example.teatea.ui.history.ontheway.OnTheWayFragment;
import com.example.teatea.ui.history.pending.PendingFragment;
import com.example.teatea.ui.history.onmaking.OnMakingFragment;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_history, container, false);

        ViewPager pagerHistory = root.findViewById(R.id.pager_history);
        pagerHistory.setAdapter(new myPagerAdapter(getChildFragmentManager()));

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
    }



    private class myPagerAdapter extends FragmentPagerAdapter {

        private String[] titles = {"Pending","On Making","On the Way","Finished"};

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
                    return OnTheWayFragment.newInstance();
                case 3:
                    return FinishedFragment.newInstance();
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
            return 4;
        }
    }

}