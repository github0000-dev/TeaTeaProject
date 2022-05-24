package com.example.teateaadmins;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public List<Notification> list;
    public ItemOnClickListener deleteOnCLick,viewOnClick;

    public HomeAdapter(List<Notification> list,ItemOnClickListener deleteOnCLick,ItemOnClickListener viewOnClick) {
        this.list = list;
        this.deleteOnCLick = deleteOnCLick;
        this.viewOnClick = viewOnClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView notif,user;
        public ImageButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notif = itemView.findViewById(R.id.notif_homeadapter);
            user = itemView.findViewById(R.id.name_user_homeadapter);
            delete = itemView.findViewById(R.id.delete_notif);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteOnCLick.onClick(v,getAdapterPosition());
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewOnClick.onClick(v,getAdapterPosition());
                }
            });

        }

    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.notification_item_mainhome,parent,false);
        return new ViewHolder(viewItem);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        Notification notif = list.get(position);
        holder.notif.setText(notif.notification);
        holder.user.setText(notif.name_user);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ItemOnClickListener{
        void onClick(View v,int position);
    }

}
