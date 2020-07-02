package com.example.patientregistration_backup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addDetails extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    EditText firstName,lastName,email;
    Button saveBtn;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        email=findViewById(R.id.emailAddress);
        saveBtn=findViewById(R.id.saveBtn);

        firebaseAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
//        userID=firebaseAuth.getCurrentUser().getUid();
        Log.d("firebase reading", "onCreate: "+firebaseAuth.getCurrentUser());
        userID=firebaseAuth.getCurrentUser().getPhoneNumber();
        final DocumentReference docRef= fstore.collection("users").document(userID);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!firstName.getText().toString().isEmpty()&&!lastName.getText().toString().isEmpty()&&!email.getText().toString().isEmpty())
                {
                    String first=firstName.getText().toString();
                    String last=lastName.getText().toString();
                    String userEmail=email.getText().toString();

                    Map<String,Object> user=new HashMap<>();
                    user.put("firstName",first);
                    user.put("lastName",last);
                    user.put("email",userEmail);
                    docRef.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(getApplicationContext(),StartupInstructions.class));
                                finish();
                            }
                            else{
                                Toast.makeText(addDetails.this, "invalid details check again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(addDetails.this, "All Fields are Required", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}