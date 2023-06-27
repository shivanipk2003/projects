package com.example.productcomparison_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Product_History_Details extends AppCompatActivity {

        ListView lhis;

    ArrayList<String> titlehead;
    ArrayList<String> titleusername;
    ArrayList<String> titleprice;
    ArrayList<String> titletime;

    TextView tv_username;

    ProgressDialog progressDoalog;

    FirebaseDatabase firebaseDatabase10,firebaseDatabase11;
    DatabaseReference databaseReference10,databaseReference11;


    String m_heading,m_username,m_type,m_price,m_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_history_details);

         lhis=findViewById(R.id.hislist);

        titlehead=new ArrayList<String>();
        titleusername=new ArrayList<String>();
        titleprice=new ArrayList<String>();
        titletime=new ArrayList<String>();

        SharedPreferences loginshared=getSharedPreferences("Loginname",MODE_PRIVATE);
        SharedPreferences.Editor editor=loginshared.edit();
        String kkoil=loginshared.getString("usernumber","");

       // Toast.makeText(getApplicationContext(),kkoil,Toast.LENGTH_LONG).show();

        firebaseDatabase10=FirebaseDatabase.getInstance();
        databaseReference10=firebaseDatabase10.getReference("Product Comparison").child("Payment Details");

        Query query=databaseReference10.orderByChild("app_user_number").equalTo(kkoil);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {

                    m_heading = dataSnapshot.child("title").getValue().toString();
                    m_username = dataSnapshot.child("name").getValue().toString();
                    m_price =("Price : " +dataSnapshot.child("price").getValue().toString());
                    m_time =("Date/Time : " +dataSnapshot.child("purchase_date").getValue().toString());


                    titlehead.add(m_heading);
                    titleusername.add(m_username);
                    titleprice.add(m_price);
                    titletime.add(m_time);


                    Javahistory javahistory=new Javahistory(Product_History_Details.this,titlehead,titleusername,titleprice,titletime);
                    lhis.setAdapter(javahistory);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

      /*  lhis.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                firebaseDatabase11=FirebaseDatabase.getInstance();
                databaseReference11=firebaseDatabase11.getReference("Product Comparison").child("Payment Details");


                AlertDialog.Builder alert=new AlertDialog.Builder(Product_History_Details.this);
                alert.setTitle("Do you want delete?");
                alert.setCancelable(false);

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        progressDoalog = new ProgressDialog(Product_History_Details.this);
                        progressDoalog.setMax(100);
                        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDoalog.setMessage("Please wait....");
                        progressDoalog.setMessage("Deleting....");

                        progressDoalog.show();
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                String teddy=titlehead.get(i);

                                Query query1=databaseReference10.orderByChild("title").equalTo(teddy);


                                query1.removeEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                                           // String key = postsnapshot.getKey();
                                          //  postsnapshot.getRef().removeValue();



                                            Toast.makeText(getApplicationContext(),"deleted successfully",Toast.LENGTH_LONG).show();


                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {


                                    };

                                });



                                Toast.makeText(getApplicationContext(),"Value deleted",Toast.LENGTH_SHORT).show();
                                Intent del1=new Intent(Product_History_Details.this,Choosepage.class);
                                startActivity(del1);
                                finish();

                            }
                        }, 5000);
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();


                return true;
            }
        });
*/

        }
}