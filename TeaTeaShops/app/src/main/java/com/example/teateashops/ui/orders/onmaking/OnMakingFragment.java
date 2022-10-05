package com.example.teateashops.ui.orders.onmaking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teateashops.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnMakingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnMakingFragment extends Fragment {



    public OnMakingFragment() {
        // Required empty public constructor
    }

    public static OnMakingFragment newInstance() {
        return new OnMakingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_on_making, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.rec_onmaking);


        return root;
    }
}