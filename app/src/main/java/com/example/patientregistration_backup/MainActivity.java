package com.example.patientregistration_backup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    TextView pName,pEmail,pPhone;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pName=(TextView)findViewById(R.id.profileFullName);
        getSupportActionBar().setTitle("Hi! \n\n"+pName.toString());

        pEmail=(TextView)findViewById(R.id.profileEmail);
        pPhone=(TextView)findViewById(R.id.profilePhone);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        DocumentReference docRef=fstore.collection("users").document(fAuth.getCurrentUser().getPhoneNumber());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                    String name=documentSnapshot.getString("firstName")+" "+documentSnapshot.getString("lastName");
                    String email=documentSnapshot.getString("email");
                    Log.d("data acquired", "data "+ name+" "+ email);

                    pName.setText(name);
                    pEmail.setText(email);
                    pPhone.setText(fAuth.getCurrentUser().getPhoneNumber());
                }
                else{
                    Log.d("failed retrieval", "Retrieving Data: Profile Data Not Found ");
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.logout)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),Register.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}