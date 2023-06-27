package com.example.productcomparison_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Registrationpage extends AppCompatActivity {


    TextInputLayout et_createname,et_createpass,et_createmail,et_createphone;
    Button bt_createbutton;
    ProgressDialog progressDoalog;

    String username,password,email,number;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);


        et_createname=(TextInputLayout) findViewById(R.id.cedit1);
        et_createpass=(TextInputLayout) findViewById(R.id.cedit2);
        et_createmail=(TextInputLayout) findViewById(R.id.cedit3);
        et_createphone=(TextInputLayout) findViewById(R.id.cedit4);
        bt_createbutton=(Button) findViewById(R.id.cbut);

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Product Comparison");

        bt_createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                username=et_createname.getEditText().getText().toString().trim();
                password=et_createpass.getEditText().getText().toString().trim();
                email=et_createmail.getEditText().getText().toString().trim();
                number=et_createphone.getEditText().getText().toString().trim();


                if (TextUtils.isEmpty(username))
                {
                    et_createname.setError("Enter The Username");
                }
                else if(TextUtils.isEmpty(password))
                {
                    et_createpass.setError("Enter The Password");
                }

                else if(TextUtils.isEmpty(email))
                {
                    et_createmail.setError("Enter The Mail");
                }
                else if(TextUtils.isEmpty(number))
                {
                    et_createphone.setError("Enter The Number");
                }
                else {
                           /* Loginjava loginjava=new Loginjava(username,password,email,number);
                            reference.child("Login Details").child(number).setValue(loginjava);
*/

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (!(snapshot.child("Login Details").child(number).exists())) {

                                HashMap hashMap = new HashMap();
                                hashMap.put("username", username);
                                hashMap.put("password", password);
                                hashMap.put("number", number);
                                hashMap.put("email", email);

                                Loginjava loginjava=new Loginjava(username,password,email,number);
                                reference.child("Login Details").child(number).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
                                            Intent in = new Intent(Registrationpage.this, Loginpage.class);
                                            startActivity(in);
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Network problem", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });


                            } else {
                                Toast.makeText(getApplicationContext(), "This " + number + " number is  already exist", Toast.LENGTH_LONG).show();

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                   /* Toast.makeText(getApplicationContext(),"Successfully added",Toast.LENGTH_SHORT).show();

                            Intent in = new Intent(Registrationpage.this, Loginpage.class);
                            startActivity(in);
                            finish();*/
                       }
            }
        });
    }
}