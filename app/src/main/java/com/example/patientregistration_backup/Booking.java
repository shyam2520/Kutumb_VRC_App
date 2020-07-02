package com.example.patientregistration_backup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patientregistration_backup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

public class Booking extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    CalendarView calendarView;
    Button confirm;
    TextView myDate,eight,ten,twelve,three,five,seven;
    String time,date,name,phoneNo,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        firebaseAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        confirm=(Button)findViewById(R.id.confirm); 
        calendarView=(CalendarView)findViewById(R.id.CalendarView);
        myDate=(TextView)findViewById(R.id.myDate);
        eight=(TextView)findViewById(R.id.eigth_am);
        ten=(TextView)findViewById(R.id.ten_am);
        twelve=(TextView)findViewById(R.id.twelve_am);
        three=(TextView)findViewById(R.id.three_pm);
        five=(TextView)findViewById(R.id.five_pm);
        seven=(TextView)findViewById(R.id.seven_pm);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eight=(TextView)findViewById(R.id.eigth_am);
//                eight.setBackgroundColor(Color.parseColor("yellow"));
                eight.setTextColor(Color.parseColor("Black"));
                eight.setBackgroundResource(R.drawable.on_change_background_button);
                time="8:00";
            }
        });
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eight=(TextView)findViewById(R.id.ten_am);
//                eight.setBackgroundColor(Color.parseColor("yellow"));
                eight.setTextColor(Color.parseColor("Black"));
                eight.setBackgroundResource(R.drawable.on_change_background_button);
                time="10:00";
            }
        });
        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eight=(TextView)findViewById(R.id.twelve_am);
//                eight.setBackgroundColor(Color.parseColor("yellow"));
                eight.setTextColor(Color.parseColor("Black"));
                eight.setBackgroundResource(R.drawable.on_change_background_button);
                time="12:00";
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eight=(TextView)findViewById(R.id.three_pm);
//                eight.setBackgroundColor(Color.parseColor("yellow"));
                eight.setTextColor(Color.parseColor("Black"));
                eight.setBackgroundResource(R.drawable.on_change_background_button);
                time="3:00";
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eight=(TextView)findViewById(R.id.seven_pm);
//                eight.setBackgroundColor(Color.parseColor("yellow"));
                eight.setTextColor(Color.parseColor("Black"));
                eight.setBackgroundResource(R.drawable.on_change_background_button);
                time="7:00";
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eight=(TextView)findViewById(R.id.five_pm);
//                eight.setBackgroundColor(Color.parseColor("yellow"));
                eight.setTextColor(Color.parseColor("Black"));
                eight.setBackgroundResource(R.drawable.on_change_background_button);
                time="5:00";
            }
        });
        Log.d("firebase reading", "onCreate: "+firebaseAuth.getCurrentUser());
        String userID=firebaseAuth.getCurrentUser().getPhoneNumber();
        final DocumentReference docRef= fstore.collection("Appointments").document(userID);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date=String.valueOf(year)+'/'+String.valueOf(month)+'/'+String.valueOf(dayOfMonth);


            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneNo=firebaseAuth.getCurrentUser().getPhoneNumber();
                DocumentReference docRef=fstore.collection("users").document(firebaseAuth.getCurrentUser().getPhoneNumber());
                final DocumentReference newdocRef= fstore.collection("Appointments").document(phoneNo);
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            String name=documentSnapshot.getString("firstName")+" "+documentSnapshot.getString("lastName");
                            String email=documentSnapshot.getString("email");
                            Log.d("data acquired", "data "+ name+" "+ email);

                               Map<String,Object> user=new HashMap<>();
                                user.put("PatientName",name);
                                user.put("phoneNumber",phoneNo);
                                user.put("email",email);
                                user.put("Timings",time);
                                user.put("Date",date);
                                newdocRef.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            startActivity(new Intent(getApplicationContext(),navigator.class));
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(Booking.this, "invalid details check again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                        }
                        else{
                            Log.d("failed retrieval", "Retrieving Data: Profile Data Not Found ");
                        }

                    }
                });


            }

        });

    }
}