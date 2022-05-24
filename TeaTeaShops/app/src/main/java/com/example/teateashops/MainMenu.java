package com.example.teateashops;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainMenu extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static Activity main_menu_act;
    private Vendor vend = new Vendor();
    private Shop shop = new Shop();
    private DatabaseReference db;

    private int session_id;

    private Toast backToast;
    private long backPressedTime;

//    private Intent intent_login_get = getIntent();
//    public String sessionid = intent_login_get.getStringExtra("sessionnum");
//    public String sessionuse = intent_login_get.getStringExtra("username");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        main_menu_act = MainMenu.this;

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_orders, R.id.nav_staff, R.id.nav_shop, R.id.nav_products)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        Intent intent_login_get = getIntent();
        String sessionid = intent_login_get.getStringExtra("sessionnum");
        String sessionuse = intent_login_get.getStringExtra("username");

        View headerLayout = navigationView.getHeaderView(0);
        TextView header_name = headerLayout.findViewById(R.id.navheader_name);
        TextView header_email = headerLayout.findViewById(R.id.navheader_email);

        System.out.println("User: "+sessionuse);
        System.out.println("ID of the User: "+sessionid);
        db = FirebaseDatabase.getInstance().getReference();


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vend.name = String.valueOf(snapshot.child("Staffs").child(sessionid).child("name").getValue());
                vend.email = String.valueOf(snapshot.child("Staffs").child(sessionid).child("email").getValue());
                vend.address = String.valueOf(snapshot.child("Staffs").child(sessionid).child("address").getValue());
                vend.username = String.valueOf(snapshot.child("Staffs").child(sessionid).child("username").getValue());
                vend.number = String.valueOf(snapshot.child("Staffs").child(sessionid).child("number").getValue());
                vend.password = String.valueOf(snapshot.child("Staffs").child(sessionid).child("password").getValue());
                shop.shop_name = String.valueOf(snapshot.child("Shops").child(sessionid).child("shop_name").getValue());
                shop.shop_position = String.valueOf(snapshot.child("Shops").child(sessionid).child("shop_position").getValue());
                shop.shop_desc = String.valueOf(snapshot.child("Shops").child(sessionid).child("shop_desc").getValue());
                shop.shop_address= String.valueOf(snapshot.child("Shops").child(sessionid).child("shop_address").getValue());
                vend.id = sessionid;
                shop.id = sessionid;
                header_name.setText(vend.name);
                header_email.setText(vend.email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(""+error.getMessage());
            }
        });


        System.out.println(vend.id);
        System.out.println(shop.id);

        session_id = Integer.parseInt(sessionid);

        System.out.println("Session ID: "+ sessionid);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public Vendor getVendor() {
        return vend;
    }
    public Shop getShop() {
        return shop;
    }


    public void onBackPressed () {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
        } else {
            backToast = Toast.makeText(getBaseContext(),"Press Back Again to Exit.",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    public String getShopId () {
        return shop.id;
    }

    public int getSessionId() { return session_id; }


}