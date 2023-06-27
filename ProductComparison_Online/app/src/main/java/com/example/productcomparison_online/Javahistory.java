package com.example.productcomparison_online;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Javahistory extends BaseAdapter {

    Context contexthis;
    LayoutInflater layoutInflater;

    ArrayList<String> titlehead;
    ArrayList<String> titleusername;
    ArrayList<String> titleprice;
    ArrayList<String> titletime;


    public Javahistory(Context contexthis, ArrayList<String> titlehead, ArrayList<String> titleusername,  ArrayList<String> titleprice,ArrayList<String> titletime) {
        this.contexthis = contexthis;
        this.titlehead = titlehead;
        this.titleusername = titleusername;
        this.titleprice = titleprice;
        this.titletime = titletime;


        layoutInflater=(LayoutInflater.from(contexthis));
    }
    @Override
    public int getCount() {
        return titlehead.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = layoutInflater.inflate(R.layout.historyxml, null);
        TextView thead=(TextView)view.findViewById(R.id.title1);
        TextView tusername=(TextView)view.findViewById(R.id.title2);
        TextView tprice=(TextView)view.findViewById(R.id.title4);
        TextView ttime=(TextView)view.findViewById(R.id.title5);

        thead.setText(titlehead.get(i));
        tusername.setText(titleusername.get(i));
        tprice.setText(titleprice.get(i));
        ttime.setText(titletime.get(i));

        return view;
    }
}
