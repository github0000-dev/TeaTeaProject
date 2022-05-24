package com.example.teatea;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

public class networkValClass {

    private Boolean wifiConnected, mobileConnected;


    public Boolean netChecker(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i=0;i<info.length;i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }

         /*
         NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

         if (!(networkInfo != null && networkInfo.isConnected())) {
             Toast.makeText()
             return true;
         } else {
             return false;
         }

          */

/*
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }

        }
*/
        return false;
    }

    public void onRecieve (Context context, Intent intent) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet,null);
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
                onRecieve(context,intent);
            }
        });
    }

}