package com.example.patientregistration_backup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Pay extends AppCompatActivity {

    EditText amount, note, name, upiid;
    Button pay;
    final int UPI_PAYMENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        initializement();

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String amounttext = amount.getText().toString();
                String notetext = note.getText().toString();
                String nametxt = name.getText().toString();
                String upitext = upiid.getText().toString();

                payUsingUpi(amounttext, notetext, nametxt, upitext);


            }
        });

    }

    private void payUsingUpi(String amounttext, String notetext, String nametxt, String upitext) {


        Uri uri = Uri.parse("upi://pay").buildUpon().appendQueryParameter("pa", upitext)
                .appendQueryParameter("pn", nametxt)
                .appendQueryParameter("tn", notetext)
                .appendQueryParameter("am", amounttext)
                .appendQueryParameter("cu", "INR").build();

        Intent upi_payment = new Intent(Intent.ACTION_VIEW);
        upi_payment.setData(uri);

        Intent chooser = Intent.createChooser(upi_payment, "Pay With");
        if (null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(this, "No UPI App found", Toast.LENGTH_SHORT).show();
        }

    }

    private void initializement() {

        pay = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.name);
        amount = (EditText) findViewById(R.id.amount);
        note = (EditText) findViewById(R.id.note);
        upiid = (EditText) findViewById(R.id.upi_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode || (resultCode == 11))) {
                    if (data != null) {
                        String txt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult:" + txt);
                        ArrayList<String> dataLst = new ArrayList<>();
                        dataLst.add("Nothing");
                        upipaymentdataoperation(dataLst);
                    } else {
                        Log.d("UPI", "onActivityResult:" + "Return data is null");
                        ArrayList<String> dataLst = new ArrayList<>();
                        dataLst.add("Nothing");
                        upipaymentdataoperation(dataLst);
                    }
                } else {

                    Log.d("UPI", "onActivityResult:" + "Return data is null");
                    ArrayList<String> dataLst = new ArrayList<>();
                    dataLst.add("Nothing");
                    upipaymentdataoperation(dataLst);


                }

                break;

        }

    }

    private void upipaymentdataoperation(ArrayList<String> data) {
        if (isConnectionAvaliable(Pay.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upipaymentoperation:" + str);
            String paymentCancel = "";
            if (str == null) str = "discard";
            String status = "";
            String approvalref = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalsStr[] = response[i].split("=");
                if (equalsStr.length >= 2) {
                    if (equalsStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalsStr[1].toLowerCase();
                    } else if (equalsStr[0].toLowerCase().equals("approval Ref".toLowerCase()) ||
                            equalsStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalref = equalsStr[1];
                    }
                } else {
                    paymentCancel = "Payment cancel by user";
                }
            }

            if (status.equals("success")) {
                Toast.makeText(this, "Transaction Successful", Toast.LENGTH_SHORT).show();
                Log.d("UPI", "responseStr: " + approvalref);
            } else if ("Payment cancelled bu user".equals(paymentCancel)) {
                Toast.makeText(this, "Payment cancelled by user", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Transaction failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isConnectionAvaliable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.isConnectedOrConnecting()
                    && networkInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}