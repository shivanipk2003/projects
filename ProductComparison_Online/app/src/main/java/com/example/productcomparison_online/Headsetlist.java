package com.example.productcomparison_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Headsetlist extends AppCompatActivity {

    ListView hl;

    ArrayList<String> himg;
    ArrayList<String> hprice;
    ArrayList<String> hname;
    ArrayList<String> head;

    ArrayList<String> himg1;
    ArrayList<String> hprice1;
    ArrayList<String> hname1;
    ArrayList<String> head1;

    FirebaseDatabase firebaseDatabase5,firebaseDatabase55;
    DatabaseReference reference5,reference55;

    Button btn_lowpriceheadset;

    String getheadset;
    String clickedbtn="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headsetlist);

        hl=findViewById(R.id.headsetlist);

        btn_lowpriceheadset=findViewById(R.id.lowpriceheadset);

        himg=new ArrayList<String>();
        hprice=new ArrayList<String>();
        hname=new ArrayList<String>();
        head=new ArrayList<String>();

        himg1=new ArrayList<String>();
        hprice1=new ArrayList<String>();
        hname1=new ArrayList<String>();
        head1=new ArrayList<String>();

        firebaseDatabase5=FirebaseDatabase.getInstance();
        reference5=firebaseDatabase5.getReference("Product Comparison").child("Product Information").child("Headset Information");

        reference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    String image=dataSnapshot.child("Imageurl").getValue().toString();
                    String names=dataSnapshot.child("Name").getValue().toString();
                    String prices=dataSnapshot.child("Price").getValue().toString();
                    String heads=dataSnapshot.child("Head").getValue().toString();

                    himg.add(image);
                    hname.add(names);
                    hprice.add(prices);
                    head.add(heads);

                    Headsetjavalist headsetjavalist=new Headsetjavalist(himg,head,hprice,hname,Headsetlist.this);
                    hl.setAdapter(headsetjavalist);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_lowpriceheadset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickedbtn="koil";

                firebaseDatabase55=FirebaseDatabase.getInstance();
                reference55=firebaseDatabase55.getReference("Product Comparison").child("Product Information").child("Headset Information");


                Query query2=reference55.orderByChild("No").equalTo("1");

                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot ds:snapshot.getChildren()){


                            String image1=ds.child("Imageurl").getValue().toString();
                            String names1=ds.child("Name").getValue().toString();
                            String prices1=ds.child("Price").getValue().toString();
                            String heads1=ds.child("Head").getValue().toString();

                            //   Listshowdetails data=ds.getValue(Listshowdetails.class);
                            himg1.add(image1);
                            hname1.add(names1);
                            hprice1.add(prices1);
                            head1.add(heads1);

                            HeadsetListJava headsetjavalist=new HeadsetListJava(himg1,head1,hprice1,hname1,Headsetlist.this);
                            hl.setAdapter(headsetjavalist);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        hl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (clickedbtn.equals("koil")){

                    getheadset=head1.get(i);

                    Intent share=new Intent(Headsetlist.this,BuyOtherProducts.class);

                    share.putExtra("Type",getheadset);
                    share.putExtra("Type1","headset");
                    startActivity(share);

                }else {

                    getheadset=head.get(i);

                    Intent share=new Intent(Headsetlist.this,BuyOtherProducts.class);

                    share.putExtra("Type",getheadset);
                    share.putExtra("Type1","headset");
                    startActivity(share);

                }

                // getheadset=hname.get(i);
              //  String spos=String.valueOf(i);


            }
        });
    }
}