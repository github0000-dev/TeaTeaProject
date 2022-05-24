package com.example.teateashops;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

private List<NotificationItem> list;
private RecyclerViewClickListener listener;

public NotificationAdapter(List<NotificationItem> list,RecyclerViewClickListener listener) {
        this.list = list;
        this.listener = listener;
        }


public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    public TextView notif_message,cust_name,total_price;

    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public ViewHolder(View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);

        notif_message = (TextView) itemView.findViewById(R.id.prod_message_home);
        cust_name = (TextView) itemView.findViewById(R.id.cust_name_home);
        total_price = (TextView) itemView.findViewById(R.id.total_price_home);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition());
    }

}
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View notifView = inflater.inflate(R.layout.notif_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(notifView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
//
        NotificationItem item = list.get(position);
        holder.notif_message.setText(item.prod_message);
        holder.cust_name.setText(item.cust_name);
        holder.total_price.setText(item.total_price);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        // return mContacts.size();
        return list.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v,int position);
    }
}
