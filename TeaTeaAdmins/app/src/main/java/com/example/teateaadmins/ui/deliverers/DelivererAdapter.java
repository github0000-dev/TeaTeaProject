package com.example.teateaadmins.ui.deliverers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.teateaadmins.R;

import java.util.List;

public class DelivererAdapter extends RecyclerView.Adapter<DelivererAdapter.ViewHolder> {

    private List<Deliverer> list;
    private RecyclerViewClickListener listener;

    public DelivererAdapter(List<Deliverer> list, RecyclerViewClickListener listener) {
        this.list = list;
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView address,fullname;
        public ImageView image;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
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
    public DelivererAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(DelivererAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
//
        Deliverer user = list.get(position);
        holder.address.setText(user.address);
        holder.fullname.setText(user.name);

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
