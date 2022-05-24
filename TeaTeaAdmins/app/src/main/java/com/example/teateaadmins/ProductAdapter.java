package com.example.teateaadmins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private List<Product> list;

    public ProductAdapter(List<Product> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView address,fullname;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            int position = getAdapterPosition();
            address = (TextView) itemView.findViewById(R.id.name_useradapter);
            fullname = (TextView) itemView.findViewById(R.id.address_useradapter);
            image = itemView.findViewById(R.id.userPic_adapter);

        }
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View productView = inflater.inflate(R.layout.fragment_profile,parent,false);
        ViewHolder viewHolder = new ViewHolder(productView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
