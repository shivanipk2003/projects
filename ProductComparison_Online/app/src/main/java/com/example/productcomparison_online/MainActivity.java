package com.example.productcomparison_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences loginshared=getSharedPreferences("Loginname",MODE_PRIVATE);
        SharedPreferences.Editor editor=loginshared.edit();

        String splash=loginshared.getString("usernumber","");

        if(splash.equals("")){

            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext() , Loginpage.class));
                    finish();
                }
            },3000);
        }
        else {
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext() , Choosepage.class));
                    finish();
                }
            },3000);
        }


    }
}