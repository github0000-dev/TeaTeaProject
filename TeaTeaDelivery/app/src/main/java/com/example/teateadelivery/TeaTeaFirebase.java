
package com.example.teateadelivery;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.core.Query;

import static android.content.ContentValues.TAG;
import static com.google.firebase.storage.FirebaseStorage.getInstance;

public class TeaTeaFirebase {


    private static int i;
    public static int thereIs;
    DataSnapshot snap;
    public DatabaseReference dbwrite,dbread,db;
    private Query dbquery;
    public void nameExisted (String name) {
        FirebaseDatabase.getInstance();
        dbread = FirebaseDatabase.getInstance().getReference().child("Customers");
        thereIs = 0;
        dbread.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    String username = data.child("name").getValue().toString().trim();
                    if (username.equals(name)) {
                        thereIs = 1;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*
        dbread.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                snap = snapshot;
                Map<String,Object> customers = (Map<String, Object>) snapshot.getValue();
                for (String uid : customers.keySet()) {
                    String nameGet = String.valueOf(snapshot.child(uid).child("name").getValue());
                    ch = new CheckerInst()
                    signupActivity su = new signupActivity();
                    if (nameGet.equals(name)) {
                        thereIs = 1;
                        ch.thereIs = 1;
                        su.thereIs = 1;
                    }
                    Log.d("UID", uid);
                    Log.d("Name", name);
                    Log.d("NameGet", nameGet);
                    Log.d("ThereIs", String.valueOf(thereIs));
                }


            }



            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(null,""+ error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        */
        System.out.println("Hello there!");
        Log.d("Value of thereIs",String.valueOf(thereIs));
        Log.d("Snapshot Snapped", String.valueOf(snap));
    }

    public void registerDeliverers(String name,String address,String email,String number,String username, String password) {

        dbread = FirebaseDatabase.getInstance().getReference().child("Deliverers");
        dbread.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (i=0;i<snapshot.getChildrenCount()+1;i++) {
                    Log.d("Value of i",String.valueOf(i));
                    Log.d("Snapshot Count",String.valueOf(snapshot.child(String.valueOf(i)).getChildrenCount()));
                    if (snapshot.child(String.valueOf(i)).getChildrenCount() == 0) {
                        dbread.child(String.valueOf(i)).setValue(new Deliverer(name, address, email, number, username, password));
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

