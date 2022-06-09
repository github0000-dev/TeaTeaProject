package com.example.teateaadmins.ui.shops;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.teateaadmins.R;
import com.example.teateaadmins.ui.customers.Customer;
import com.example.teateaadmins.ui.customers.CustomerAdapter;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private List<Shops> list;
    private ShopAdapter.RecyclerViewClickListener listener;

    public ShopAdapter(List<Shops> list, ShopAdapter.RecyclerViewClickListener listener) {
        this.list = list;
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView address,fullname;
        public ImageView image;

        public ViewHolder(View itemView) {

            super(itemView);

            address = (TextView) itemView.findViewById(R.id.address_useradapter);
            fullname = (TextView) itemView.findViewById(R.id.name_useradapter);
            image = itemView.findViewById(R.id.userPic_adapter);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View milkteaView = inflater.inflate(R.layout.userlayout, parent, false);
        // Return a new holder instance
        ShopAdapter.ViewHolder viewHolder = new ShopAdapter.ViewHolder(milkteaView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(ShopAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
//
        Shops shop = list.get(position);
        holder.address.setText(shop.address);
        holder.fullname.setText(shop.name);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v,int position);
    }

}
