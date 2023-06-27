package com.example.productcomparison_online;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Choosepage extends AppCompatActivity {


    TextView mtext,search_tv,tv_vhis;
    ListView lv_hislist;
    AutoCompleteTextView autoCompleteTextView;
    ProgressDialog progressDoalog;

    ImageView im_his;

    String[] searchv={"Book","Ear Phone","Electronic Gadgets","Watch"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepage);

        mtext=(TextView) findViewById(R.id.martext);
        search_tv=(TextView)findViewById(R.id.searchicons);
        autoCompleteTextView=findViewById(R.id.autoComplete);
        im_his=findViewById(R.id.logout);

        mtext.setSelected(true);




        ArrayAdapter adapter=new ArrayAdapter(Choosepage.this,android.R.layout.simple_spinner_dropdown_item,searchv);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);//start searching from 1 character
        autoCompleteTextView.setAdapter(adapter);

     /*   im_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentj1=new Intent(Choosepage.this,Product_History_Details.class);
                startActivity(intentj1);

            }
        });*/



        search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String kj1="Book";
                String kj2="Watch";
                String kj3="Ear Phone";
                String kj4="Electronic Gadgets";

                String ssk=autoCompleteTextView.getText().toString();
                //Toast.makeText(getApplicationContext(),autostring,Toast.LENGTH_SHORT).show();


                //String item = arg1.getItemAtPosition(pos);



                if (ssk.equals("Book")){

                    SharedPreferences modeltype=getSharedPreferences("Model",MODE_PRIVATE);
                    SharedPreferences.Editor editortype=modeltype.edit();
                    editortype.putString("Type","Book");
                    editortype.commit();

                    progressDoalog = new ProgressDialog(Choosepage.this);
                    progressDoalog.setMax(100);
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDoalog.setMessage("Please wait....");
                    progressDoalog.setMessage("Searching books....");

                    progressDoalog.show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub

                            Intent intentj=new Intent(Choosepage.this,Booklist.class);
                            startActivity(intentj);
                        }
                    }, 5000);



                }
                else if (ssk.equals("Watch")){

                    SharedPreferences modeltype=getSharedPreferences("Model",MODE_PRIVATE);
                    SharedPreferences.Editor editortype=modeltype.edit();
                    editortype.putString("Type","Watch");
                    editortype.commit();
                    progressDoalog = new ProgressDialog(Choosepage.this);
                    progressDoalog.setMax(100);
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDoalog.setMessage("Please wait....");
                    progressDoalog.setMessage("Searching low price watch....");

                    progressDoalog.show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub

                            Intent intente=new Intent(Choosepage.this,Watchlist.class);
                            startActivity(intente);
                        }
                    }, 5000);



                }
                else if (ssk.equals("Ear Phone")){

                    SharedPreferences modeltype=getSharedPreferences("Model",MODE_PRIVATE);
                    SharedPreferences.Editor editortype=modeltype.edit();
                    editortype.putString("Type","Ear Phone");
                    editortype.commit();

                    progressDoalog = new ProgressDialog(Choosepage.this);
                    progressDoalog.setMax(100);
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDoalog.setMessage("Please wait....");
                    progressDoalog.setMessage("Searching low price Ear Phone....");

                    progressDoalog.show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub

                            Intent intentb=new Intent(Choosepage.this,Headsetlist.class);
                            startActivity(intentb);

                        }
                    }, 5000);
                }
                else if (ssk.equals("Electronic Gadgets")){

                    SharedPreferences modeltype=getSharedPreferences("Model",MODE_PRIVATE);
                    SharedPreferences.Editor editortype=modeltype.edit();
                    editortype.putString("Type","Electronic Gadgets");
                    editortype.commit();

                    progressDoalog = new ProgressDialog(Choosepage.this);
                    progressDoalog.setMax(100);
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDoalog.setMessage("Please wait....");
                    progressDoalog.setMessage("Searching low price electronic devices....");

                    progressDoalog.show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub

                            Intent intenta=new Intent(Choosepage.this,Gadgetlist.class);
                            startActivity(intenta);

                        }
                    }, 5000);

                }
                else {
                    autoCompleteTextView.setError("pls fill the field");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.koilmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){

            case R.id.item3:
                Intent intenthis=new Intent(Choosepage.this,Product_History_Details.class);
                startActivity(intenthis);
                return true;
            case R.id.item4:
                Intent intentout=new Intent(Choosepage.this,Logoutpage.class);
                startActivity(intentout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}