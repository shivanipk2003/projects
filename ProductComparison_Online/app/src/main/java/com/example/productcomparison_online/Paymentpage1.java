package com.example.productcomparison_online;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Paymentpage1 extends AppCompatActivity {

    TextView tv_title,tv_appusername,tv_price,tv_username,tv_door,tv_street,tv_city,tv_pin,tv_phone;
    EditText ed_pin,ed_phno;
    Button bt_pay;
    TextView tv_type,tv_showdate;

    ProgressDialog progressDoalog;

    FirebaseDatabase firebaseDatabase11;
    DatabaseReference reference11;
    int size;

    String name,phone_number,door_no,street,city,pincode,title,price;
    String app_user_number,purchase_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentpage1);

        tv_type=findViewById(R.id.producttype);

        tv_title=findViewById(R.id.billproductname);
        tv_price=findViewById(R.id.billprice);
        tv_username=findViewById(R.id.addname);
        tv_door=findViewById(R.id.adddoor);
        tv_street=findViewById(R.id.addstreet);
        tv_city=findViewById(R.id.addcity);
        tv_pin=findViewById(R.id.addpin);
        tv_phone=findViewById(R.id.addphone);
        bt_pay=findViewById(R.id.proceed);
        tv_showdate=findViewById(R.id.showdate);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy MM dd - HH:mm:ss");
        purchase_date = sdf1.format(new Date());



        SharedPreferences loginshared=getSharedPreferences("Loginname",MODE_PRIVATE);
        SharedPreferences.Editor editor=loginshared.edit();
       // String apass =loginshared.getString("username","");
         app_user_number=loginshared.getString("usernumber","");

        firebaseDatabase11=FirebaseDatabase.getInstance();
        reference11=firebaseDatabase11.getReference("Product Comparison").child("Payment Details");

        reference11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get total available quest
                size = (int) dataSnapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        tv_type.setText(purchase_date);


        SharedPreferences getnamesdetails=getSharedPreferences("Namedetails",MODE_PRIVATE);
        SharedPreferences.Editor editor1=getnamesdetails.edit();

        tv_username.setText(getnamesdetails.getString("Addname",""));
        tv_phone.setText(getnamesdetails.getString("Addnum",""));
        tv_door.setText(getnamesdetails.getString("Adddoor",""));
        tv_street.setText(getnamesdetails.getString("Addstreet",""));
        tv_city.setText(getnamesdetails.getString("Addcity",""));
        tv_pin.setText(getnamesdetails.getString("Addpin",""));
        tv_title.setText(getnamesdetails.getString("title",""));
        tv_price.setText(getnamesdetails.getString("price",""));

        bt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                AlertDialog.Builder bbill=new AlertDialog.Builder(Paymentpage1.this);
                bbill.setTitle("CONFIRMATION!!!");
                bbill.setMessage("The SMS will be Sent to your mobile");
                bbill.setCancelable(false);

                bbill.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        name=tv_username.getText().toString();
                        phone_number=tv_phone.getText().toString();
                        door_no=tv_door.getText().toString();
                        street=tv_street.getText().toString();
                        city=tv_city.getText().toString();
                        pincode=tv_pin.getText().toString();
                        title=tv_title.getText().toString();
                        price=tv_price.getText().toString();

                        Final_paymentjava final_paymentjava=new Final_paymentjava(name,phone_number,door_no,street,city,pincode,title,price,app_user_number,purchase_date);
                        reference11.child(String.valueOf(size)).setValue(final_paymentjava);

                        Toast.makeText(getApplicationContext(),"Payment successfully completeded...",Toast.LENGTH_SHORT).show();

                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(phone_number,null,"Hii  " + name + ",  " +
                                "you have received the order from this application.\n" +
                                "Details:\n" +
                                "Name : " + title + "\n" +
                                "Price : " +price + "  Successfully Buyed."
                                ,null,null);

                        Intent veri=new Intent(Paymentpage1.this,SuccessPage.class);
                        veri.putExtra("shareno",phone_number);
                        startActivity(veri);
                        finish();

                    }
                });
                bbill.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                bbill.show();
            }
        });



    }
}