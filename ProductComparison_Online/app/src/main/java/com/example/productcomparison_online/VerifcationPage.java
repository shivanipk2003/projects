package com.example.productcomparison_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class VerifcationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifcation_page);

        Handler h=new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent checky=new Intent(VerifcationPage.this,Paymentpage1.class);
                startActivity(checky);
                finish();

            }
        },8000);

    }
}