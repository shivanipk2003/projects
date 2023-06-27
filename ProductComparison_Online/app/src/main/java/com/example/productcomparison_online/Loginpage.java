package com.example.productcomparison_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Loginpage extends AppCompatActivity {


    TextInputLayout logedit1,logedit2;
    Button btlogin;

    TextView createtext;

    ProgressDialog progressDoalog;
    FirebaseDatabase firebaseDatabase1;
    DatabaseReference reference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        logedit1=(TextInputLayout)findViewById(R.id.edit1);
        logedit2=(TextInputLayout)findViewById(R.id.edit2);
        createtext=(TextView) findViewById(R.id.textcreate);
        btlogin=(Button) findViewById(R.id.lbut);

        firebaseDatabase1=FirebaseDatabase.getInstance();
        reference1=firebaseDatabase1.getReference("Product Comparison");


        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String k1=logedit1.getEditText().getText().toString().trim();
                String k2=logedit2.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(k1))
                {
                    logedit1.setError("Mobile number required");
                }
                else if(TextUtils.isEmpty(k2))
                {
                    logedit2.setError("Password required");
                }
                else{
                            SharedPreferences loginshared=getSharedPreferences("Loginname",MODE_PRIVATE);
                            SharedPreferences.Editor editor=loginshared.edit();
                        //    editor.putString("username",k2);
                            editor.putString("usernumber",k1);
                            editor.commit();

                            reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            if(snapshot.child("Login Details").child(k1).exists()){

                                                Loginjava usersData1=snapshot.child("Login Details").child(k1).getValue(Loginjava.class);

                                                if((usersData1.getNumber().equals(k1)) && (usersData1.getPassword().equals(k2))){

                                                    Toast.makeText(getApplicationContext(),"Logged successfully....",Toast.LENGTH_LONG).show();

                                                    Intent homeid1=new Intent(Loginpage.this,Choosepage.class);
                                                    startActivity(homeid1);
                                                    finish();
                                                }
                                                else{
                                                    Toast.makeText(getApplicationContext(),"Incorrect User number or password",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            else {
                                                Toast.makeText(getApplicationContext(),"This User Does not exist",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error){
                                        }
                                    });

                }
            }
        });

        createtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        Intent next = new Intent(Loginpage.this, Registrationpage.class);
                        startActivity(next);
                        finish();

            }
        });


    }
}