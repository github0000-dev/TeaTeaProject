package com.example.teatea;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teatea.ui.cart.CartFragment;
import com.example.teatea.ui.history.HistoryFragment;
import com.example.teatea.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainMenu extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public static Activity menu_act;
    private static int i;
    private String menu_name,menu_email;
    private Customer cust = new Customer();

    private Toast backToast;
    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent_login_get = getIntent();

        String sessionid = intent_login_get.getStringExtra("sessionnum");
        String sessionuse = intent_login_get.getStringExtra("username");

        SessionClass session = new SessionClass();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Customers");
        menu_act = MainMenu.this;

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

        // View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        View headerLayout = navigationView.getHeaderView(0);
        TextView header_name = headerLayout.findViewById(R.id.navheader_name);
        TextView header_email = headerLayout.findViewById(R.id.navheader_email);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_cart, R.id.nav_history, R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        db.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                }
//                else {
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                }
//            }
//        });

//
//        TextView header_name = findViewById(R.id.navheader_name);
//        TextView header_email = findViewById(R.id.navheader_email);

        System.out.println("User: "+sessionuse);
        System.out.println("ID of the User: "+sessionid);


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                menu_name = String.valueOf(snapshot.child(sessionid).child("name").getValue());
                menu_email = String.valueOf(snapshot.child(sessionid).child("email").getValue());
                cust.name = String.valueOf(snapshot.child(sessionid).child("name").getValue());
                cust.email = String.valueOf(snapshot.child(sessionid).child("email").getValue());
                cust.address = String.valueOf(snapshot.child(sessionid).child("address").getValue());
                cust.username = String.valueOf(snapshot.child(sessionid).child("username").getValue());
                cust.number = String.valueOf(snapshot.child(sessionid).child("number").getValue());
                cust.password = String.valueOf(snapshot.child(sessionid).child("password").getValue());
                cust.id = sessionid;
                System.out.println("Name of the User: "+menu_name);
                System.out.println("Email of the User: "+menu_email);
                header_name.setText(menu_name);
                header_email.setText(menu_email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(""+error.getMessage());
            }
        });
        System.out.println("Name of the User Out: "+menu_name);
        System.out.println("Email of the User Out: "+menu_email);


        System.out.println("Name of the User Class: "+cust.name);
        System.out.println("Email of the User Class: "+cust.email);






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public Customer getCustomer() {
        return cust;
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
}