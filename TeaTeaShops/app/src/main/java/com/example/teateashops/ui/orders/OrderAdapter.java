package com.example.teateashops.ui.orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teateashops.MilkteaItem;
import com.example.teateashops.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<OrderItem> list;
    OnClickListener listener;

    public OrderAdapter(List<OrderItem> list,OnClickListener listener) {
        this.list = list;
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,price,totalprice,quantity;

        public ViewHolder(View milkteaView) {
            super(milkteaView);

            name = milkteaView.findViewById(R.id.milktea_order_name);
            price = milkteaView.findViewById(R.id.milktea_order_price);
            totalprice = milkteaView.findViewById(R.id.milktea_order_totalprice);
            quantity = milkteaView.findViewById(R.id.milktea_order_quantity);

            milkteaView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v,getAdapterPosition());
                }
            });
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View milkteaView = layoutInflater.inflate(R.layout.orderlayout,parent,false);

        ViewHolder viewHolder = new ViewHolder(milkteaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).name);
        holder.price.setText(String.valueOf(list.get(position).price));
        holder.totalprice.setText(String.valueOf(list.get(position).price*list.get(position).quantity));
        holder.quantity.setText(String.valueOf(list.get(position).quantity));
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnClickListener {
        void onClick(View v,int position);
    }
}
