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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BuyPage extends AppCompatActivity {


    TextView tv_buyname,tv_published,tv_author,tv_price,tv_ratting,tv_discription;
    ImageView iv_buyimage;

    Button bt_buybook;

    ProgressDialog progressDoalog;

    FirebaseDatabase firebaseDatabase6;
    DatabaseReference reference6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);

        tv_buyname=findViewById(R.id.buyname);
        tv_published=findViewById(R.id.buypublished);
        tv_author=findViewById(R.id.buyauthor);
        tv_price=findViewById(R.id.buyprice);
        tv_ratting=findViewById(R.id.buyratting);
        tv_discription=findViewById(R.id.buydiscription);

        iv_buyimage=findViewById(R.id.buyimage);


        bt_buybook=findViewById(R.id.buybookbutton);

        Intent e1=getIntent();
        String searchname=e1.getStringExtra("keyval");
       // String searchname1=e1.getStringExtra("keyval1");
        //Integer searchname1=Integer.parseInt(searchname);

       //Toast.makeText(getApplicationContext(),searchname,Toast.LENGTH_SHORT).show();


        firebaseDatabase6=FirebaseDatabase.getInstance();
        reference6=firebaseDatabase6.getReference("Product Comparison").child("Product Information").child("Book Information").child(searchname);

        //Query query7=reference6.orderByChild("Name").equalTo(searchname);

        reference6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String  b_name = snapshot.child("Name").getValue().toString();
                String  b_Imageurl = snapshot.child("Imageurl").getValue().toString();
                String  b_published = snapshot.child("Published").getValue().toString();
                String  b_author = snapshot.child("Author").getValue().toString();
                String  b_ratting = snapshot.child("Ratting").getValue().toString();
                String  b_discription = snapshot.child("Discription").getValue().toString();
                String  b_price = snapshot.child("Price").getValue().toString();


                Picasso.get().load(b_Imageurl).into(iv_buyimage);
                tv_buyname.setText(b_name);
                tv_published.setText(b_published);
                tv_author.setText(b_author);
                tv_ratting.setText(b_ratting);
                tv_discription.setText(b_discription);
                tv_price.setText(b_price);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bt_buybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(BuyPage.this);
                View promptsView = li.inflate(R.layout.alertfile, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BuyPage.this);
                final EditText userInput1 = (EditText) promptsView.findViewById(R.id.editalert1);
                final EditText userInput2 = (EditText) promptsView.findViewById(R.id.editalert2);
                final EditText userInput3 = (EditText) promptsView.findViewById(R.id.editalert3);
                final EditText userInput4 = (EditText) promptsView.findViewById(R.id.editalert4);
                final EditText userInput5 = (EditText) promptsView.findViewById(R.id.editalert5);
                final EditText userInput6 = (EditText) promptsView.findViewById(R.id.editalert6);

                AlertDialog.Builder alert=new AlertDialog.Builder(BuyPage.this);
                alert.setView(promptsView);
                alert.setCancelable(false);

                /*ImageView imageView = (ImageView) findViewById(R.id.imageView1);
                 imageView = new ImageView(ListActivity.this);

                imageView.setImageResource(R.drawable.deleteicon);
                alert.setView(imageView);*/
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alert.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String res1 = userInput1.getText().toString();
                        String res2 = userInput2.getText().toString();
                        String res3 = userInput3.getText().toString();
                        String res4 = userInput4.getText().toString();
                        String res5 = userInput5.getText().toString();
                        String res6 = userInput6.getText().toString();
                        String res7 = tv_buyname.getText().toString();
                        String res8 = tv_price.getText().toString();

                        if(res1.isEmpty()){
                            userInput1.setError("Please enter your Name");
                        }
                        else if(res2.isEmpty()){
                            userInput2.setError("please enter your Ph.Number");
                        }
                        else if(res3.isEmpty()){
                            userInput3.setError("please enter your Door No");
                        }
                        else if(res4.isEmpty()){
                            userInput4.setError("please enter your Street name");
                        }
                        else if(res5.isEmpty()){
                            userInput5.setError("please enter your City");
                        }
                        else if(res6.isEmpty()){
                            userInput6.setError("please enter your Pin code");
                        }
                        else{

                            SharedPreferences getnamesdetails = getSharedPreferences("Namedetails", MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = getnamesdetails.edit();
                            editor1.putString("Addname", res1);
                            editor1.putString("Addnum", res2);
                            editor1.putString("Adddoor", res3);
                            editor1.putString("Addstreet", res4);
                            editor1.putString("Addcity", res5);
                            editor1.putString("Addpin", res6);
                            editor1.putString("title", res7);
                            editor1.putString("price", res8);
                            editor1.commit();

                            progressDoalog = new ProgressDialog(BuyPage.this);
                            progressDoalog.setMax(100);
                            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressDoalog.setMessage("Analysing....");

                            progressDoalog.show();
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub

                                    Intent re = new Intent(BuyPage.this, PaymentChoosePage.class);
                                   // re.putExtra("phno",res2);
                                    startActivity(re);
                                    finish();
                                }
                            }, 3000);
                        }
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
                // Toast.makeText(getApplicationContext(),"delete",Toast.LENGTH_SHORT).show();

            }
        });
    }
}