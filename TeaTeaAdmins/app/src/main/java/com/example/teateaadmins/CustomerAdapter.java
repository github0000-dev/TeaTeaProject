package com.example.teateaadmins;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private List<Customer> list;
    private RecyclerViewClickListener listener;

    public CustomerAdapter(List<Customer> list, RecyclerViewClickListener listener) {
        this.list = list;
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView address,fullname;
        public ImageView image;

        public ViewHolder(View itemView) {

            super(itemView);

            address = (TextView) itemView.findViewById(R.id.name_useradapter);
            fullname = (TextView) itemView.findViewById(R.id.address_useradapter);
            image = itemView.findViewById(R.id.userPic_adapter);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View milkteaView = inflater.inflate(R.layout.userlayout, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(milkteaView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(CustomerAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
//
        Customer user = list.get(position);
        holder.address.setText(user.address);
        holder.fullname.setText(user.name);

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
