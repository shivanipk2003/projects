package com.example.productcomparison_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PaymentChoosePage extends AppCompatActivity {

    TextView tv_cashpayment,tv_onlinepayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_choose_page);

        tv_cashpayment=findViewById(R.id.cashpayment);
        tv_onlinepayment=findViewById(R.id.onlinepayment);

        tv_cashpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent closepage=new Intent(PaymentChoosePage.this,CashOnDelivery.class);
                startActivity(closepage);

            }
        });

        tv_onlinepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent closepage1=new Intent(PaymentChoosePage.this,Paymentdetails.class);
                startActivity(closepage1);

            }
        });
    }
}