package com.example.productcomparison_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Watchlist extends AppCompatActivity {

    ListView wl;


    ArrayList<String> wimg;
    ArrayList<String> wprice;
    ArrayList<String> wname;
    ArrayList<String> head;

    ArrayList<String> wimg1;
    ArrayList<String> wprice1;
    ArrayList<String> wname1;
    ArrayList<String> head1;

    FirebaseDatabase firebaseDatabase4,firebaseDatabase41;
    DatabaseReference reference4,reference41;

    Button btn_lowpricewatch;
    String getwatch;
    String clickedbtn=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        wl=findViewById(R.id.watchlist);

        btn_lowpricewatch=findViewById(R.id.lowpricewatch);


        wimg=new ArrayList<String>();
        wprice=new ArrayList<String>();
        wname=new ArrayList<String>();
        head=new ArrayList<String>();

        wimg1=new ArrayList<String>();
        wprice1=new ArrayList<String>();
        wname1=new ArrayList<String>();
        head1=new ArrayList<String>();




            firebaseDatabase4 = FirebaseDatabase.getInstance();
            reference4 = firebaseDatabase4.getReference("Product Comparison").child("Product Information").child("Watch Information");

            reference4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        String image = dataSnapshot.child("Imageurl").getValue().toString();
                        String names = dataSnapshot.child("Name").getValue().toString();
                        String prices = dataSnapshot.child("Price").getValue().toString();
                        String heads = dataSnapshot.child("Head").getValue().toString();

                        wimg.add(image);
                        wname.add(names);
                        wprice.add(prices);
                        head.add(heads);


                        Watchjavalist watchlist = new Watchjavalist(wimg,head, wprice, wname, Watchlist.this);
                        wl.setAdapter(watchlist);

                       /* SharedPreferences modeltype=getSharedPreferences("Model",MODE_PRIVATE);
                        SharedPreferences.Editor editortype=modeltype.edit();*/



                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            btn_lowpricewatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    clickedbtn="koil";

                    firebaseDatabase41=FirebaseDatabase.getInstance();
                    reference41=firebaseDatabase41.getReference("Product Comparison").child("Product Information").child("Watch Information");


                    Query query3=reference41.orderByChild("No").equalTo("1");

                    query3.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot ds:snapshot.getChildren()){


                                String image1=ds.child("Imageurl").getValue().toString();
                                String names1=ds.child("Name").getValue().toString();
                                String prices1=ds.child("Price").getValue().toString();
                                String heads1=ds.child("Head").getValue().toString();

                                wimg1.add(image1);
                                wname1.add(names1);
                                wprice1.add(prices1);
                                head1.add(heads1);


                                Watchjavalist watchlist = new Watchjavalist(wimg1,head1, wprice1, wname1, Watchlist.this);
                                wl.setAdapter(watchlist);

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            });

            wl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    if (clickedbtn.equals("koil")){

                        getwatch=head1.get(i);

                        Intent share=new Intent(Watchlist.this,BuyOtherProducts.class);

                        share.putExtra("Type1","watch");
                        share.putExtra("Type",getwatch);
                        startActivity(share);

                    }else {
                        getwatch=head.get(i);

                        Intent share=new Intent(Watchlist.this,BuyOtherProducts.class);

                        share.putExtra("Type1","watch");
                        share.putExtra("Type",getwatch);
                        startActivity(share);
                    }

                    // getwatch=wname.get(i);
                   // String wpos=String.valueOf(i);
                    Intent share=new Intent(Watchlist.this,BuyOtherProducts.class);

                    share.putExtra("Type1","watch");
                    share.putExtra("Type",getwatch);
                    startActivity(share);

                }
            });
        }
    }
