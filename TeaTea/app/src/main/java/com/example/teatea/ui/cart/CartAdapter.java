package com.example.teatea.ui.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.teatea.Cart;
import com.example.teatea.MilkteaItem;
import com.example.teatea.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Cart> list;
    private RecycleViewDeleteListener listener;

    public CartAdapter(List<Cart> list) {
        this.list = list;
    }

    public CartAdapter(List<Cart> list,RecycleViewDeleteListener listener) {
        this.list = list;
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView name_milk,price_milk,size_milk,quantity_milk;
        public ImageView image;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            price_milk = (TextView) itemView.findViewById(R.id.milk_price);
            size_milk = (TextView) itemView.findViewById(R.id.milk_size);
            quantity_milk = (TextView) itemView.findViewById(R.id.milk_quantity);
            name_milk = (TextView) itemView.findViewById(R.id.milk_name1);
            image = itemView.findViewById(R.id.milkteaPic1);

            ImageButton delete = itemView.findViewById(R.id.delete_cartAdapter);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemRemove(v,getAdapterPosition());
                }
            });

        }
    }
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View milkteaView = inflater.inflate(R.layout.milktealayout, parent, false);

        LinearLayout layout_testing = milkteaView.findViewById(R.id.layout_testing);
        layout_testing.setVisibility(LinearLayout.GONE);



        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(milkteaView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        // Get the data model based on position

        Cart item = list.get(position);
        holder.price_milk.setText(String.valueOf(item.totalprice));
        holder.quantity_milk.setText(String.valueOf(item.quantity));
        holder.size_milk.setText(String.valueOf(item.teasize) + " - " + String.valueOf(item.price));
        holder.name_milk.setText(String.valueOf(item.name));

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        // return mContacts.size();
        return list.size();
    }

    public interface RecycleViewDeleteListener {
        void onItemRemove(View v,int position);
    }
}
