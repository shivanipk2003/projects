package com.example.productcomparison_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;

public class CashOnDelivery extends AppCompatActivity {

    EditText ed_cashotp;
    TextView tv_cashsendotp,tv_cashretunpage;
    Button bt_cashcontinue;

    String so,cashnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on_delivery);

        ed_cashotp=findViewById(R.id.cashotp);
        tv_cashsendotp=findViewById(R.id.cashsendotp);
        tv_cashretunpage=findViewById(R.id.returnpage);
        bt_cashcontinue=findViewById(R.id.cashconfirm);

        SharedPreferences getnamesdetails = getSharedPreferences("Namedetails", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = getnamesdetails.edit();
         cashnum=getnamesdetails.getString("Addnum","");

        tv_cashsendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendOTP();

            }
        });

        tv_cashretunpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in3=new Intent(CashOnDelivery.this,PaymentChoosePage.class);
                startActivity(in3);
                finish();
            }
        });


        bt_cashcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String kk1=ed_cashotp.getText().toString();

                if (kk1.isEmpty()){
                    ed_cashotp.setError("Please enter the otp");
                }

                  else if (kk1.equals(so)){

                    Intent in3=new Intent(CashOnDelivery.this,SuccessPage.class);
                    startActivity(in3);
                    finish();

                    SmsManager sms=SmsManager.getDefault();
                    sms.sendTextMessage(cashnum,null,"Hii customer !!! \nOrder Successfully Placed.\nYour Order will be delivered within 5 days.\norder no : OD1129347463",null,null);

                }
                else {

                    Toast.makeText(getApplicationContext(),"Please enter the valid OTP number",Toast.LENGTH_LONG).show();
                }



            }
        });
    }

    private int sendOTP() {
        int randomNumber;
        int range = 9;
        int length = 4;
        SecureRandom secureRandom = new SecureRandom();
        so = "";
        for (int i = 0; i < 4; i++) {
            int number = secureRandom.nextInt(range);
            if (number == 0 && i == 0)
            {
                i = -1;
                continue;
            }
            so = so + number;
        }
        randomNumber = Integer.parseInt(so);

        SmsManager ssmmss = SmsManager.getDefault();
        ssmmss.sendTextMessage(cashnum, null,  so + "  This is the verification code to access your account at FarMarket. Please do not share it with anyone.", null, null);


        return randomNumber;


    }
}