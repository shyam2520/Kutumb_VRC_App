package com.example.patientregistration_backup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
//import android.widget.Toolbar;

public class navigator extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    TextView toolbar_name,navigator_name;
    CardView health_tips,video_call,user_payments,appointment;
    String displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        toolbar_name=(TextView)findViewById(R.id.profileName);
        navigator_name=(TextView)findViewById(R.id.navbar_name);
        health_tips=(CardView)findViewById(R.id.HealthTip);
        video_call=(CardView)findViewById(R.id.VideoCalling);
        user_payments=(CardView)findViewById(R.id.pay);
        appointment=(CardView)findViewById(R.id.Appointment);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        DocumentReference docRef = fstore.collection("users").document(fAuth.getCurrentUser().getPhoneNumber());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("firstName") + " " + documentSnapshot.getString("lastName");
                    String firstname=documentSnapshot.getString("firstName");
                    String email = documentSnapshot.getString("email");
                    Log.d("data acquired", "data " + name + " " + email);
                    if(name.length()<=12){
                        displayName=name;
                    }
                    else{
                        displayName=firstname;
                    }
                    displayName=firstname;
                    toolbar_name.setText(displayName);
                    //navbar header name
                    NavigationView navigationView = (NavigationView) findViewById(R.id.navigator_view);
                    View headerView = navigationView.getHeaderView(0);
                    TextView navUsername = (TextView) headerView.findViewById(R.id.navbar_name);
                    navUsername.setText(displayName);
                } else {
                    Log.d("failed retrieval", "Retrieving Data: Profile Data Not Found ");
                }

            }
        });
        drawerLayout=findViewById(R.id.drawer);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorBlack));
        drawerToggle.setDrawerIndicatorEnabled(true);// hamburger menu
        drawerToggle.syncState();

        navigationView=findViewById(R.id.navigator_view);
        navigationView.setNavigationItemSelectedListener(this);
        health_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(navigator.this, "checking if click works" +
//                        "", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),HealthTips.class));
            }
        });
        video_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(navigator.this, "checking if click works" +
//                        "", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),VideoCall.class));
            }
        });
        user_payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(navigator.this, "checking if click works" +
//                        "", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Pay.class));
            }
        });
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(navigator.this, "checking if click works" +
//                        "", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Booking.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(),Register.class));
                finish();
                break;
            case R.id.nav_location:
                break;
            case R.id.nav_signout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Register.class));
                finish();
            case R.id.nav_pay:
                startActivity(new Intent(getApplicationContext(),Pay.class));
                finish();


        }
        return false;
    }

}