package com.example.teatea;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.teatea.ui.home.HomeFragment;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<MilkteaItem> list;
    private RecyclerViewClickListener listener;

    public HomeAdapter(List<MilkteaItem> list,RecyclerViewClickListener listener) {
        this.list = list;
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name_milk,shop_from;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            name_milk = (TextView) itemView.findViewById(R.id.milk_name_home);
            shop_from = (TextView) itemView.findViewById(R.id.milk_shopfrom_home);
            image = itemView.findViewById(R.id.milkteaPic2);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View milkteaView = inflater.inflate(R.layout.homeproduct_layout, parent, false);

        HomeAdapter.ViewHolder viewHolder = new HomeAdapter.ViewHolder(milkteaView);
        return viewHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        MilkteaItem item = list.get(position);
        holder.name_milk.setText(item.name);
        holder.shop_from.setText(item.shop_from);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v,int position);
    }
}
