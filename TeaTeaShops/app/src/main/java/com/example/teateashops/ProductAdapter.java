package com.example.teateashops;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<MilkteaItem> list;
    private DeleteItemClickListener listener;

    public ProductAdapter(List<MilkteaItem> list,DeleteItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView name_milk,price_milk_l,price_milk_m,price_milk_s,desc_milk;
        public ImageView image;
        public ImageButton del_button;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            price_milk_l = (TextView) itemView.findViewById(R.id.milk_size_l_product);
            price_milk_m = (TextView) itemView.findViewById(R.id.milk_size_m_product);
            price_milk_s = (TextView) itemView.findViewById(R.id.milk_size_s_product);
            desc_milk = (TextView) itemView.findViewById(R.id.milk_desc_product);
            name_milk = (TextView) itemView.findViewById(R.id.milk_name_product);
            image = itemView.findViewById(R.id.milkteapic_prod);

            del_button = itemView.findViewById(R.id.delete_productFromShop);

            del_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemRemove(v,getAdapterPosition());
                }
            });

        }
    }
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View milkteaView = inflater.inflate(R.layout.productlayout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(milkteaView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
//
        MilkteaItem item = list.get(position);
        holder.desc_milk.setText(item.description);
//        holder.price_milk_l.setText(item.price_l);
//        holder.price_milk_m.setText(item.price_m);
//        holder.price_milk_s.setText(item.price_s);
        holder.price_milk_l.setText(String.valueOf(item.price_l));
        holder.price_milk_m.setText(String.valueOf(item.price_m));
        holder.price_milk_s.setText(String.valueOf(item.price_s));
        holder.name_milk.setText(item.name);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        // return mContacts.size();
        return list.size();
    }

    public interface DeleteItemClickListener {
        void onItemRemove(View v, int position);
    }
}
