package com.example.patientregistration_backup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class splashScreen extends AppCompatActivity {
    public static int SPLASH_TIME_OUT=4000;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent homeIntent = new Intent(splashScreen.this, Register.class);
//                startActivity(homeIntent);
//                finish();
                 FirebaseUser user= fAuth.getCurrentUser();
                Log.d("firebase data", "data acquired "+fAuth.getCurrentUser());
                 if(user!=null)
                 {
                     startActivity(new Intent(getApplicationContext(),navigator.class));
                     finish();
                    }
                 else{
                     startActivity(new Intent(getApplicationContext(),Loginoption.class));
                     finish();

                }
            }

        },SPLASH_TIME_OUT);
    }
}