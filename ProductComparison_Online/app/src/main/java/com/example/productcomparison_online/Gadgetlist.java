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

public class Gadgetlist extends AppCompatActivity {

    ListView gl;

    ArrayList<String> gimg;
    ArrayList<String> gprice;
    ArrayList<String> gname;
    ArrayList<String> head;

    ArrayList<String> gimg1;
    ArrayList<String> gprice1;
    ArrayList<String> gname1;
    ArrayList<String> head1;

    FirebaseDatabase firebaseDatabase5,firebaseDatabase51;
    DatabaseReference reference5,reference51;

    Button btn_lowpricegadget;

    String gadname="";
    String gadpos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgetlist);

        gl=findViewById(R.id.gadgetlist);
        btn_lowpricegadget=findViewById(R.id.lowpricegadget);

        gimg=new ArrayList<String>();
        gprice=new ArrayList<String>();
        gname=new ArrayList<String>();
        head=new ArrayList<String>();

        gimg1=new ArrayList<String>();
        gprice1=new ArrayList<String>();
        gname1=new ArrayList<String>();
        head1=new ArrayList<String>();

        firebaseDatabase5=FirebaseDatabase.getInstance();
        reference5=firebaseDatabase5.getReference("Product Comparison").child("Product Information").child("Gadget Information");

        reference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    String image=dataSnapshot.child("Imageurl").getValue().toString();
                    String names=dataSnapshot.child("Name").getValue().toString();
                    String prices=dataSnapshot.child("Price").getValue().toString();
                    String heads=dataSnapshot.child("Head").getValue().toString();

                    gimg.add(image);
                    gname.add(names);
                    gprice.add(prices);
                    head.add(heads);

                    Gadgetjavalist gadgetjavalist =new Gadgetjavalist(gimg,head,gprice,gname,Gadgetlist.this);
                    gl.setAdapter(gadgetjavalist);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_lowpricegadget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gadname="koil";


                firebaseDatabase51=FirebaseDatabase.getInstance();
                reference51=firebaseDatabase51.getReference("Product Comparison").child("Product Information").child("Gadget Information");


                Query query2=reference51.orderByChild("No").equalTo("1");

                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot ds:snapshot.getChildren()){


                            String image1=ds.child("Imageurl").getValue().toString();
                            String names1=ds.child("Name").getValue().toString();
                            String prices1=ds.child("Price").getValue().toString();
                            String heads1=ds.child("Head").getValue().toString();

                            gimg1.add(image1);
                            gname1.add(names1);
                            gprice1.add(prices1);
                            head1.add(heads1);

                            Gadgetjavalist gadgetjavalist =new Gadgetjavalist(gimg1,head1,gprice1,gname1,Gadgetlist.this);
                            gl.setAdapter(gadgetjavalist);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });

        gl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (gadname.equals("koil")){

                     gadpos=head1.get(i);
                    //   String gpos=String.valueOf(i);

                    Intent gin=new Intent(Gadgetlist.this,BuyOtherProducts.class);
                    gin.putExtra("Type",gadpos);
                    gin.putExtra("Type1","gadget");
                    startActivity(gin);

                }else {

                     gadpos=head.get(i);
                    //   String gpos=String.valueOf(i);

                    Intent gin=new Intent(Gadgetlist.this,BuyOtherProducts.class);
                    gin.putExtra("Type",gadpos);
                    gin.putExtra("Type1","gadget");
                    startActivity(gin);

                }
            }
        });
    }
}