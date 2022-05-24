package com.example.teateaadmins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

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

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Admins");
    public static Activity main_menu_act;
    private Admin admin = new Admin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                R.id.nav_home, R.id.nav_shops,R.id.nav_customers,R.id.nav_deliverers,R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        Intent intent_login_get = getIntent();

        String sessionid = intent_login_get.getStringExtra("sessionnum");
        String sessionuse = intent_login_get.getStringExtra("username");

        main_menu_act = MainMenu.this;

        View headerLayout = navigationView.getHeaderView(0);
        TextView header_name = headerLayout.findViewById(R.id.navheader_name);
        TextView header_email = headerLayout.findViewById(R.id.navheader_email);

        System.out.println("User: "+sessionuse);
        System.out.println("ID of the User: "+sessionid);


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                admin.name = String.valueOf(snapshot.child(sessionid).child("name").getValue());
                admin.email = String.valueOf(snapshot.child(sessionid).child("email").getValue());
                admin.address = String.valueOf(snapshot.child(sessionid).child("address").getValue());
                admin.username = String.valueOf(snapshot.child(sessionid).child("username").getValue());
                admin.number = String.valueOf(snapshot.child(sessionid).child("number").getValue());
                admin.password = String.valueOf(snapshot.child(sessionid).child("password").getValue());
                admin.id = sessionid;
                header_name.setText(admin.name);
                header_email.setText(admin.email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(""+error.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public Admin getAdmin() {
        return admin;
    }
}