package com.example.patientregistration_backup;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Doctorlogin extends AppCompatActivity {
    EditText e1,e2;
    ImageView i1,i2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlogin);

        i1=(ImageView)findViewById(R.id.signin);
        i2=(ImageView)findViewById(R.id.baack);
        e1=(EditText)findViewById(R.id.username);
        e2=(EditText)findViewById(R.id.password);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().equals("Kutumb") &&
                        e2.getText().toString().equals("test")){
                    Intent i= new Intent(Doctorlogin.this,Doctorpage.class);
                    startActivity(i);

                }
                else if (e1.getText().toString().equals("") ||
                        e2.getText().toString().equals("")) {
                    Toast.makeText(Doctorlogin.this,"Please fill all entity",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Doctorlogin.this,"Username or Password is Wrong",Toast.LENGTH_SHORT).show();

                }

            }

        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Doctorlogin.this,Loginoption.class);
                startActivity(i);
            }
        });

    }
}