package com.example.productcomparison_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SuccessPage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_page);


        Handler handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent closepage=new Intent(SuccessPage.this,Choosepage.class);
                startActivity(closepage);
                finish();

            }
        },5000);
    }
}