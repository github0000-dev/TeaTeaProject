
package com.example.teatea;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.core.Query;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class TeaTeaFirebase {


    Customer cust = new Customer();
    

    public static int thereIs;
    CheckerInst ch;
    DataSnapshot snap;
    private DatabaseReference dbwrite,dbread,db;
    private Query dbquery;
    private DocumentReference db2;

    public static boolean status;
    private int i;






    public void registerCustomer(String name,String address,String email,String number,String username, String password) {
//        dbwrite = FirebaseDatabase.getInstance().getReference();
//        final int breaker = 0,i;
//        Customer cust = new Customer(name,address,email,number,username,password);
//        dbwrite.push().setValue(cust);








            dbread = FirebaseDatabase.getInstance().getReference().child("Customers");
            dbread.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (i=0;i<snapshot.getChildrenCount()+1;i++) {
                        Log.d("Value of i",String.valueOf(i));
                        Log.d("Snapshot Count",String.valueOf(snapshot.child(String.valueOf(i)).getChildrenCount()));
                        if (snapshot.child(String.valueOf(i)).getChildrenCount() == 0) {
                            dbread.child(String.valueOf(i)).setValue(new Customer(name, address, email, number, username, password));
                            return;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w(TAG, "loadPost:onCancelled",error.toException());
                }
            });



    }
}

