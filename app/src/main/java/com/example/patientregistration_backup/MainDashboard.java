package com.example.patientregistration_backup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
public class MainDashboard extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    TextView pName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dashboard);

        pName = (TextView) findViewById(R.id.profileName);


        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        DocumentReference docRef = fstore.collection("users").document(fAuth.getCurrentUser().getPhoneNumber());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("firstName") + " " + documentSnapshot.getString("lastName");
                    String email = documentSnapshot.getString("email");
                    Log.d("data acquired", "data " + name + " " + email);

                    pName.setText(name);

                } else {
                    Log.d("failed retrieval", "Retrieving Data: Profile Data Not Found ");
                }

            }
        });
    }
}