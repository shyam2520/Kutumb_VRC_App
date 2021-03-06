package com.example.patientregistration_backup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    EditText phoneNumber,codeEnter;
    Button nextBtn,backbtn;
    ProgressBar progressBar;
    TextView state;
    CountryCodePicker codePicker;
    String verificationId;
    PhoneAuthProvider.ForceResendingToken token;
    Boolean verificationInProgress=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        phoneNumber=findViewById(R.id.phone);
        codeEnter=findViewById(R.id.codeEnter);
        progressBar=findViewById(R.id.progressBar);
        nextBtn=findViewById(R.id.nextBtn);
        backbtn=findViewById(R.id.back);
        state=findViewById(R.id.state);
        codePicker =findViewById(R.id.ccp);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i= new Intent(Register.this,Loginoption.class);
                //startActivity(i);
                finish();
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificationInProgress)
                {
                    if(!phoneNumber.getText().toString().isEmpty()&&phoneNumber.getText().toString().length()==10)
                {
                    String phoneNum="+"+codePicker.getSelectedCountryCode()+phoneNumber.getText().toString();
                    Log.d("phone", "Phone No.: " + phoneNum);
                    progressBar.setVisibility(View.VISIBLE);
                    state.setText("Sending OTP ....");
                    state.setVisibility(View.VISIBLE);
                    requestPhoneAuth(phoneNum);
                    nextBtn.setEnabled(false);
                }
                else{
                    phoneNumber.setError("Invalid Phone Number");
                }}
                else{

                    String userOTP=codeEnter.getText().toString();
                    if(!userOTP.isEmpty() && userOTP.length()==6)
                    {
                        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,userOTP);
                        verifyAuth(credential);


                    }
                    else{
                        codeEnter.setError("Valid OTP is required");
                    }

                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(fAuth.getCurrentUser()!=null)
        {
            progressBar.setVisibility(View.VISIBLE);
            state.setText("Checking..");
            state.setVisibility(View.VISIBLE);
            checkUserProfile();

        }
    }



    private void verifyAuth(PhoneAuthCredential credential) {
        fAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    checkUserProfile();
                }
                else{
                    Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkUserProfile() {
        DocumentReference docRef=fstore.collection("users").document(fAuth.getCurrentUser().getPhoneNumber());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists())
                {
                    startActivity(new Intent(getApplicationContext(),navigator.class));
                    finish();
                }
                else{
                    startActivity(new Intent(getApplicationContext(),addDetails.class));
                    finish();
                }


            }
        });

    }

    private void requestPhoneAuth(String phoneNum) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNum, 60L, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId=s;
                token=forceResendingToken;
                progressBar.setVisibility(View.GONE);
                state.setVisibility(View.GONE);
                codeEnter.setVisibility(View.VISIBLE);
                nextBtn.setText("Verify");
                nextBtn.setEnabled(true);
                verificationInProgress=true;

            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                Toast.makeText(Register.this, "OTP Expired", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    verifyAuth(phoneAuthCredential);
//                Log.d("phone ", "Verified Phone No.: ");

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
//                Toast.makeText(Register.this, "Cannot Create Account Phone Number Verification Failed", Toast.LENGTH_SHORT).show();
                Toast.makeText(Register.this, "error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Register.class));
                    finish();
//                Toast.makeText(Register.this, e, Toast.LENGTH_SHORT).show();
                Log.d("phone failure","error message is"+e.getMessage());
//                Log.d("phone", "Phone No.: " + phoneNum);

            }
        });
    }
}