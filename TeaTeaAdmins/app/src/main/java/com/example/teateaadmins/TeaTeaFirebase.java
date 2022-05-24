
package com.example.teateaadmins;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.core.Query;

import static android.content.ContentValues.TAG;

public class TeaTeaFirebase {

    public static int thereIs;
    DataSnapshot snap;
    private DatabaseReference dbwrite,dbread,db;
    private Query dbquery;
    public void nameExisted (String name) {
        dbread = FirebaseDatabase.getInstance().getReference().child("Deliverers");
        thereIs = 0;
        dbread.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    String username = data.child("name").getValue().toString();
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

    public static int i;

    public void registerAdmin(String name,String address,String email,String number,String username, String password) {
        Admin ad = new Admin(name,address,email,number,username,password);
        dbread = FirebaseDatabase.getInstance().getReference().child("Customers");
        dbread.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (i=0;i<snapshot.getChildrenCount()+1;i++) {
                    Log.d("Value of i",String.valueOf(i));
                    Log.d("Snapshot Count",String.valueOf(snapshot.child(String.valueOf(i)).getChildrenCount()));
                    if (snapshot.child(String.valueOf(i)).getChildrenCount() == 0) {
                        dbread.child(String.valueOf(i)).setValue(new Admin(name, address, email, number, username, password));
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

