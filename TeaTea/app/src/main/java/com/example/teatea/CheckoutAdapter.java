package com.example.teatea;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teatea.ui.cart.CartAdapter;

import org.w3c.dom.Text;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {
    private List<MilkteaItem> list;
    private ImageButton quest_recycler;
    private Context context_checkout;

    public CheckoutAdapter() {}

    public CheckoutAdapter(List<MilkteaItem> list) {
        this.list = list;
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
        }
    }
    @Override
    public CheckoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View milkteaView = inflater.inflate(R.layout.milktealayout, parent, false);

        LinearLayout layout_reciept = milkteaView.findViewById(R.id.recieptField);
        CheckBox check_rec = milkteaView.findViewById(R.id.checkboxRecItem);
        EditText reciept_field = milkteaView.findViewById(R.id.receiptNumInput);

        ImageButton del_button = milkteaView.findViewById(R.id.delete_cartAdapter);
        del_button.setVisibility(View.INVISIBLE);

        context_checkout = parent.getContext();


        quest_recycler = (ImageButton) milkteaView.findViewById(R.id.question_rec);

//        ImageButton quest_recycler = (ImageButton) milkteaView.findViewById(R.id.question_rec);
//
        quest_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context_checkout);
                builder.setMessage("This methods prevents of double orders.");
                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing to see dude
                    }
                });
                builder.create();
                builder.show();
            }
        });

        layout_reciept.setVisibility(LinearLayout.GONE);


        check_rec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layout_reciept.setVisibility(LinearLayout.VISIBLE);
                    reciept_field.setText("");
                } else {
                    layout_reciept.setVisibility(LinearLayout.GONE);
                    reciept_field.setText("");
                }
            }
        });

        // Return a new holder instance
        CheckoutAdapter.ViewHolder viewHolder = new CheckoutAdapter.ViewHolder(milkteaView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(CheckoutAdapter.ViewHolder holder, int position) {
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
        // return mContacts.size();
        return list.size();
    }

    public ImageButton getImageButton() {
        return quest_recycler;
    }

}
