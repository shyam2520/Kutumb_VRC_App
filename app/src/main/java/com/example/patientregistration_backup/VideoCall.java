package com.example.patientregistration_backup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VideoCall extends AppCompatActivity {

    Button button;
    ImageView phyiscianCall,gyancelogyCall,dermatologyCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);

        button = (Button)findViewById(R.id.button);
        phyiscianCall = (ImageView)findViewById(R.id.physiciancall);
        gyancelogyCall = (ImageView)findViewById(R.id.gyanecologycall);
        dermatologyCall = (ImageView)findViewById(R.id.dermatologycall);

        phyiscianCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent();
                i.setPackage("com.google.android.apps.tachyon");
                i.setAction("com.google.android.apps.tachyon.action.CALL");
                i.setData(Uri.parse("tel:9140165386"));
                startActivity(i);
            }
        });

        gyancelogyCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setPackage("com.google.android.apps.tachyon");
                intent.setAction("com.google.android.apps.tachyon.action.CALL");
                intent.setData(Uri.parse("tel:9580151019"));
                startActivity(intent);
            }
        });

        dermatologyCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent();
                k.setPackage("com.google.android.apps.tachyon");
                k.setAction("com.google.android.apps.tachyon.action.CALL");
                k.setData(Uri.parse("tel:9580151019"));
                startActivity(k);
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent();
                i.setPackage("com.google.android.apps.tachyon");
                i.setAction("com.google.android.apps.tachyon.action.CALL");
                i.setData(Uri.parse("tel:9140165386"));
                startActivity(i);

            }
        });


    }
}