package com.example.productcomparison_online;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Gadgetjavalist extends BaseAdapter {

    ArrayList<String> gimg;
    ArrayList<String> gprice;
    ArrayList<String> gname;
    ArrayList<String> head;


    Context context;
    LayoutInflater layoutInflater;


    public Gadgetjavalist(ArrayList<String> gimg,  ArrayList<String> head, ArrayList<String> gprice, ArrayList<String> gname, Context context) {

        this.gimg = gimg;
        this.gprice = gprice;
        this.gname = gname;
        this.head=head;
        this.context = context;

        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return gname.size();
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

        view=layoutInflater.inflate(R.layout.listshowxml,null);

        ImageView icon = (ImageView) view.findViewById(R.id.bookimg);
        TextView textname=(TextView)view.findViewById(R.id.bookname);
        TextView textprice=(TextView)view.findViewById(R.id.bookprice);
        TextView texthead=(TextView)view.findViewById(R.id.bookhead);

        Picasso.get().load(gimg.get(i)).into(icon);
        textname.setText(gname.get(i));
        textprice.setText(gprice.get(i));
        texthead.setText(head.get(i));

        return view;
    }
}
