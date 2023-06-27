package com.example.productcomparison_online;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class Logoutpage extends AppCompatActivity {

    TextView llout;
    ProgressDialog progressDoalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logoutpage);

        llout=(TextView) findViewById(R.id.layouda);

        llout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences loginshared=getSharedPreferences("Loginname",MODE_PRIVATE);
                SharedPreferences.Editor editor=loginshared.edit();
                editor.putString("usernumber","");
                editor.commit();

                progressDoalog = new ProgressDialog(Logoutpage.this);
                progressDoalog.setMax(100);
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.setMessage("Please wait....");
                progressDoalog.setMessage("Logged out your account....");

                progressDoalog.show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        Intent intent2=new Intent(Logoutpage.this,MainActivity.class);
                        startActivity(intent2);
                        finish();

                    }
                }, 5000);



            }
        });
    }
}