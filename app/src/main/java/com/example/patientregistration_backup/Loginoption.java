package com.example.patientregistration_backup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Loginoption extends AppCompatActivity {

 Button pat,doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginoption);
        pat=(Button) findViewById(R.id.patientBtn);
        doc=(Button) findViewById(R.id.doctorBtn);
        pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Loginoption.this,Register.class);
                startActivity(i);
            }
        });
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j= new Intent(Loginoption.this,Doctorlogin.class);
                startActivity(j);

            }
        });
    }
}