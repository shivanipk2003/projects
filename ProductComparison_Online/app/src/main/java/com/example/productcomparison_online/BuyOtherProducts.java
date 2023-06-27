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
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BuyOtherProducts extends AppCompatActivity {


    TextView tv_productname,tv_productprice,tv_productratting,tv_productdiscription;
    ImageView iv_productimage;

    Button bt_buyproduct;
    ProgressDialog progressDoalog;

    FirebaseDatabase firebaseDatabase7,firebaseDatabase8,firebaseDatabase9;
    DatabaseReference reference7,reference8,reference9;

String getgpos,gname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_other_products);

        tv_productname=findViewById(R.id.watchname);
        tv_productprice=findViewById(R.id.watchprice);
        tv_productratting=findViewById(R.id.watchratting);
        tv_productdiscription=findViewById(R.id.watchdiscription1);

        iv_productimage=findViewById(R.id.watchimage);

        bt_buyproduct=findViewById(R.id.buywatchbutton);

        Intent sh=getIntent();

         getgpos=sh.getStringExtra("Type");
         gname=sh.getStringExtra("Type1");


       // Toast.makeText(getApplicationContext(),getgpos+ " , "+gname,Toast.LENGTH_SHORT).show();

        if (gname.equals("watch")) {


    firebaseDatabase7 = FirebaseDatabase.getInstance();
    reference7 = firebaseDatabase7.getReference("Product Comparison").child("Product Information").child("Watch Information").child(getgpos);


    reference7.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            String b_name = snapshot.child("Name").getValue().toString();
            String b_Imageurl = snapshot.child("Imageurl").getValue().toString();
            String b_ratting = snapshot.child("Ratting").getValue().toString();
            String b_discription = snapshot.child("Discription").getValue().toString();
            String b_price = snapshot.child("Price").getValue().toString();

            Picasso.get().load(b_Imageurl).into(iv_productimage);
            tv_productname.setText(b_name);
            tv_productratting.setText(b_ratting);
            tv_productdiscription.setText(b_discription);
            tv_productprice.setText(b_price);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

}
        if (gname.equals("headset")){

    firebaseDatabase8 = FirebaseDatabase.getInstance();
    reference8 = firebaseDatabase8.getReference("Product Comparison").child("Product Information").child("Headset Information").child(getgpos);


    reference8.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            String b_name = snapshot.child("Name").getValue().toString();
            String b_Imageurl = snapshot.child("Imageurl").getValue().toString();
            String b_ratting = snapshot.child("Ratting").getValue().toString();
            String b_discription = snapshot.child("Discription").getValue().toString();
            String b_price = snapshot.child("Price").getValue().toString();

            Picasso.get().load(b_Imageurl).into(iv_productimage);
            tv_productname.setText(b_name);
            tv_productratting.setText(b_ratting);
            tv_productdiscription.setText(b_discription);
            tv_productprice.setText(b_price);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
}
      if (gname.equals("gadget")){

    firebaseDatabase9 = FirebaseDatabase.getInstance();
    reference9 = firebaseDatabase9.getReference("Product Comparison").child("Product Information").child("Gadget Information").child(getgpos);


    reference9.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            String b_name = snapshot.child("Name").getValue().toString();
            String b_Imageurl = snapshot.child("Imageurl").getValue().toString();
            String b_ratting = snapshot.child("Ratting").getValue().toString();
            String b_discription = snapshot.child("Discription").getValue().toString();
            String b_price = snapshot.child("Price").getValue().toString();


            Picasso.get().load(b_Imageurl).into(iv_productimage);
            tv_productname.setText(b_name);
            tv_productratting.setText(b_ratting);
            tv_productdiscription.setText(b_discription);
            tv_productprice.setText(b_price);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


}


bt_buyproduct.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        String getthename=tv_productname.getText().toString();
        String gettheprice=tv_productprice.getText().toString();
        String gettheratting=tv_productratting.getText().toString();
        String getthediscription=tv_productdiscription.getText().toString();


        LayoutInflater li = LayoutInflater.from(BuyOtherProducts.this);
        View promptsView = li.inflate(R.layout.alertfile, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BuyOtherProducts.this);
        final EditText userInput1 = (EditText) promptsView.findViewById(R.id.editalert1);
        final EditText userInput2 = (EditText) promptsView.findViewById(R.id.editalert2);
        final EditText userInput3 = (EditText) promptsView.findViewById(R.id.editalert3);
        final EditText userInput4 = (EditText) promptsView.findViewById(R.id.editalert4);
        final EditText userInput5 = (EditText) promptsView.findViewById(R.id.editalert5);
        final EditText userInput6 = (EditText) promptsView.findViewById(R.id.editalert6);

        AlertDialog.Builder alert=new AlertDialog.Builder(BuyOtherProducts.this);
        alert.setView(promptsView);
        alert.setCancelable(false);

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
                String res7 = tv_productname.getText().toString();
                String res8 = tv_productprice.getText().toString();

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


                    progressDoalog = new ProgressDialog(BuyOtherProducts.this);
                    progressDoalog.setMax(100);
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDoalog.setMessage("Analysing....");

                    progressDoalog.show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub

                            Intent re = new Intent(BuyOtherProducts.this, PaymentChoosePage.class);
                            re.putExtra("phno",res2);
                            startActivity(re);
                            finish();


                        }
                    }, 3000);

                }

            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();



    }
});
    }
}