package com.example.productcomparison_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;

public class Otpverification extends AppCompatActivity {

    TextView tv_sendotp;
    EditText ed_editotp;
    Button bt_verifyotp;

    String s,otpnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);


        tv_sendotp=findViewById(R.id.sendotp);
        ed_editotp=findViewById(R.id.otpedit);
        bt_verifyotp=findViewById(R.id.verifyotp);

        /*Intent sss=getIntent();

        String otpnum=sss.getStringExtra("shownum");
*/

        bt_verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String otpgets=ed_editotp.getText().toString();

                if (otpgets.isEmpty()){
                    ed_editotp.setError("Please enter the valid OTP number...");
                }
                else {


                    if (otpgets.equals(s)){

                        Intent check=new Intent(Otpverification.this,VerifcationPage.class);
                        startActivity(check);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Please enter valid otp number",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        tv_sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent numget=getIntent();

                otpnum=numget.getStringExtra("shownum");

                sendOtp();
            }
        });
    }

    private int sendOtp() {

        int randomNumber;
        int range = 9;
        int length = 4;
        SecureRandom secureRandom = new SecureRandom();
        s = "";
        for (int i = 0; i < 4; i++) {
            int number = secureRandom.nextInt(range);
            if (number == 0 && i == 0)
            {
                i = -1;
                continue;
            }
            s = s + number;
        }
        randomNumber = Integer.parseInt(s);

        SmsManager ssmmss = SmsManager.getDefault();
        ssmmss.sendTextMessage(otpnum, null,  s + "  This is the verification code to access your account at FarMarket. Please do not share it with anyone.", null, null);


        return randomNumber;

    }
}