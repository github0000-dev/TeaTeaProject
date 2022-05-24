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

import com.example.teatea.MilkteaItem;
import com.example.teatea.R;

import java.util.List;

public class CartAdapter2 extends RecyclerView.Adapter<CartAdapter2.ViewHolder> {

    private List<MilkteaItem> list;
    private DeleteClickerListener listener;

    public CartAdapter2(List<MilkteaItem> list,DeleteClickerListener listener) {
        this.list = list;
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView name_milk,price_milk,size_milk,quantity_milk;
        public ImageView image;
        public ImageButton del_button;

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

            del_button = itemView.findViewById(R.id.delete_cartAdapter);

            del_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRemoveItem(v,getAdapterPosition());
                }
            });

        }
    }
    @Override
    public CartAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(CartAdapter2.ViewHolder holder, int position) {
        // Get the data model based on position

        MilkteaItem item = list.get(position);
        holder.price_milk.setText(item.totalitem_price);
        holder.quantity_milk.setText(item.quantity_item);
        holder.size_milk.setText(item.size_price);
        holder.name_milk.setText(item.name);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface DeleteClickerListener {
        void onRemoveItem(View v,int position);
    }

}
