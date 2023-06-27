package com.example.productcomparison_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
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

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Paymentdetails extends AppCompatActivity {

    EditText ed_payacc,ed_payifsc,ed_payname,ed_paybranch,ed_paypass;
    TextView tv_paynum;
    Button btn_continue;

    String encrypt_Accno,encrypt_Ifsc,payname,paybranch,paynumber,encrypt_password;

    FirebaseDatabase firebaseDatabase20,firebaseDatabase21;
    DatabaseReference databaseReference20,databaseReference21;

    String KEY_VALUE="07";
    
    String payaccno,payifsc,paypass,showpaynumber;

    int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentdetails);

        ed_payacc=findViewById(R.id.payacc);
        ed_payifsc=findViewById(R.id.payifsc);
        ed_payname=findViewById(R.id.payname);
        ed_paybranch=findViewById(R.id.paybranch);
        ed_paypass=findViewById(R.id.paypass);
        tv_paynum=findViewById(R.id.payph);

        btn_continue=findViewById(R.id.payment);


        SharedPreferences getnamesdetails = getSharedPreferences("Namedetails", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = getnamesdetails.edit();
        showpaynumber=getnamesdetails.getString("Addnum","");
       /* Intent ee=getIntent();

         showpaynumber=ee.getStringExtra("phno");
*/
        tv_paynum.setText(showpaynumber);

        firebaseDatabase20=FirebaseDatabase.getInstance();
        databaseReference20=firebaseDatabase20.getReference("Product Comparison").child("Account Details").child(("Encrypt"));

        firebaseDatabase21=FirebaseDatabase.getInstance();
        databaseReference21=firebaseDatabase21.getReference("Product Comparison").child("Account Details").child(("Decrypt"));


        databaseReference20.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get total available quest
                size = (int) dataSnapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                payaccno=ed_payacc.getText().toString();
                payifsc=ed_payifsc.getText().toString();
                payname=ed_payname.getText().toString();
                paybranch=ed_paybranch.getText().toString();
                paynumber=tv_paynum.getText().toString();
                paypass=ed_paypass.getText().toString();




             /*   if (payaccno.isEmpty()){
                    ed_payacc.setError("Please enter your Account number");

                }
                else if(payifsc.isEmpty()){
                    ed_payifsc.setError("Please enter your IFSC code");
                }
                else if (payname.isEmpty()){
                    ed_payname.setError("Please enter your Name");
                }
                else if (paybranch.isEmpty()){
                    ed_paybranch.setError("Please enter your Branch name");
                }
                else if (paypass.isEmpty()){
                    ed_paypass.setError("Please enter your valid Password");
                }*/

                if (!((payaccno.isEmpty()) && (payifsc.isEmpty()) && (payname.isEmpty()) && (paybranch.isEmpty()) && (paynumber.isEmpty()) && (paypass.isEmpty()))){

                    try {
                        encrypt_Accno = encrypt(payaccno, KEY_VALUE);
                        encrypt_Ifsc = encrypt1(payifsc, KEY_VALUE);
                        encrypt_password = encrypt2(paypass, KEY_VALUE);


                        Accountjava accountjava=new Accountjava(encrypt_Accno,encrypt_Ifsc,payname,paybranch,paynumber,encrypt_password);

                        databaseReference20.child(String.valueOf(size)).setValue(accountjava);

                        DecryptJava decryptJava=new DecryptJava(payaccno,payifsc,paypass);
                        databaseReference21.child(String.valueOf(size)).setValue(decryptJava);


                        Intent payy = new Intent(Paymentdetails.this, Otpverification.class);
                        payy.putExtra("shownum", showpaynumber);
                        startActivity(payy);
                        finish();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                  /*  tv_showtext.setText(outputstring);
                    tv_showtext1.setText(outputstring1);*/

                }
                else {
                    Toast.makeText(getApplicationContext(),"Please fill all the field",Toast.LENGTH_LONG);
                }

            }
        });

    }

    private String encrypt2(String paypass, String key_value) throws Exception {

        SecretKeySpec key=generatekey(KEY_VALUE);
        Cipher c=Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] en_pass=c.doFinal(paypass.getBytes());
        String encryptedpass= Base64.encodeToString(en_pass,Base64.DEFAULT);
        return encryptedpass;
    }

    private String encrypt1(String payifsc, String key_value) throws Exception{

        SecretKeySpec key=generatekey(KEY_VALUE);
        Cipher c=Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] en_ifsc=c.doFinal(payifsc.getBytes());
        String encryptedifsc= Base64.encodeToString(en_ifsc,Base64.DEFAULT);
        return encryptedifsc;
    }

    private String encrypt(String payaccno, String key_value) throws Exception{
        SecretKeySpec key=generatekey(KEY_VALUE);
        Cipher c=Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] en_acc=c.doFinal(payaccno.getBytes());
        String encryptedacc= Base64.encodeToString(en_acc,Base64.DEFAULT);
        return encryptedacc;
    }

    private SecretKeySpec generatekey(String password) throws Exception {

        final MessageDigest digest=MessageDigest.getInstance("SHA-256");
        byte[] bytes=password.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key=digest.digest();
        SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }
}