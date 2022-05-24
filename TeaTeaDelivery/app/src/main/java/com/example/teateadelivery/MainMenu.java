package com.example.teateadelivery;

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
    public static Activity main_menu_act;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Deliverers");
    private Deliverer del = new Deliverer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        main_menu_act = MainMenu.this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_delivery, R.id.nav_statistics)
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


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                del.name = String.valueOf(snapshot.child(sessionid).child("name").getValue());
                del.email = String.valueOf(snapshot.child(sessionid).child("email").getValue());
                del.address = String.valueOf(snapshot.child(sessionid).child("address").getValue());
                del.username = String.valueOf(snapshot.child(sessionid).child("username").getValue());
                del.number = String.valueOf(snapshot.child(sessionid).child("number").getValue());
                del.password = String.valueOf(snapshot.child(sessionid).child("password").getValue());
                del.id = sessionid;
                header_name.setText(del.name);
                header_email.setText(del.email);
                return;
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
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public Deliverer getDeliverer() {
        return del;
    }
}