package com.example.productcomparison_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Booklist extends AppCompatActivity {

    ListView listbook;

    ArrayList<String> img;
    ArrayList<String> name;
    ArrayList<String> price;
    ArrayList<String> head;

    ArrayList<String> img1;
    ArrayList<String> name1;
    ArrayList<String> price1;
    ArrayList<String> head1;

    FirebaseDatabase firebaseDatabase3,firebaseDatabase31;
    DatabaseReference reference3,reference31;

    Button  bt_lowpricebook;

    String clickedbtn=" ";
    String nameget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);

        listbook=(ListView) findViewById(R.id.booklist);
        bt_lowpricebook=findViewById(R.id.lowpricebook);

        firebaseDatabase3=FirebaseDatabase.getInstance();
        reference3=firebaseDatabase3.getReference("Product Comparison").child("Product Information").child("Book Information");

        img=new ArrayList<String>();
        name=new ArrayList<String>();
        price=new ArrayList<String>();
        head=new ArrayList<String>();

        img1=new ArrayList<String>();
        name1=new ArrayList<String>();
        price1=new ArrayList<String>();
        head1=new ArrayList<String>();

        //Query query=reference3.orderByChild("number").equalTo(wnum);

        reference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    String image=dataSnapshot.child("Imageurl").getValue().toString();
                    String names=dataSnapshot.child("Name").getValue().toString();
                    String prices=dataSnapshot.child("Price").getValue().toString();
                    String heads=dataSnapshot.child("Head").getValue().toString();

                    img.add(image);
                    name.add(names);
                    price.add(prices);
                    head.add(heads);


                    Listshowdetails listshowdetails=new Listshowdetails(Booklist.this,img,name,price,head);
                    listbook.setAdapter(listshowdetails);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        bt_lowpricebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickedbtn="koil";


                        firebaseDatabase31=FirebaseDatabase.getInstance();
                        reference31=firebaseDatabase31.getReference("Product Comparison").child("Product Information").child("Book Information");


                        Query query1=reference31.orderByChild("No").equalTo("1");

                        query1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for(DataSnapshot ds:snapshot.getChildren()){


                                    String image1=ds.child("Imageurl").getValue().toString();
                                    String names1=ds.child("Name").getValue().toString();
                                    String prices1=ds.child("Price").getValue().toString();
                                    String head11=ds.child("Head").getValue().toString();


                                    //   Listshowdetails data=ds.getValue(Listshowdetails.class);
                                    img1.add(image1);
                                    name1.add(names1);
                                    price1.add(prices1);
                                    head1.add(head11);


                                    BookListJava listshowdetails1=new BookListJava(Booklist.this,img1,name1,price1,head1);
                                    listbook.setAdapter(listshowdetails1);

                                }
                           }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    /* }
                },4000);*/

            }
        });

        listbook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (clickedbtn.equals("koil")){

                     nameget=head1.get(i);

                    Intent ee=new Intent(Booklist.this,BuyPage.class);
                    ee.putExtra("keyval",nameget);
                    // ee.putExtra("keyval1",nameget1);
                    startActivity(ee);

                }
                else {
                    nameget=head.get(i);

                    Intent ee=new Intent(Booklist.this,BuyPage.class);
                    ee.putExtra("keyval",nameget);
                    // ee.putExtra("keyval1",nameget1);
                    startActivity(ee);
                }


               // String nameget=head.get(i);
               // String nameget1=head1.get(i);
                //int namegetts=Integer.parseInt(nameget);

              //  String pos=String.valueOf(i);

/*

                Intent ee=new Intent(Booklist.this,BuyPage.class);
                ee.putExtra("keyval",nameget);
               // ee.putExtra("keyval1",nameget1);
                startActivity(ee);
*/

            }
        });

            }
        }