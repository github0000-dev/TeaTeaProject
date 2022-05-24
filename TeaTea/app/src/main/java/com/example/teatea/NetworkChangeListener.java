package com.example.teatea;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class NetworkChangeListener {
    public void onRecieve (Context context, Intent intent) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet, null);
        builder.setView(layout_dialog);

        AppCompatButton btnRetry = layout_dialog.findViewById(R.id.btnRetry);

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setGravity(Gravity.CENTER);

        btnRetry.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                dialog.dismiss();
                onRecieve(context, intent);
            }
        });

    }
}
