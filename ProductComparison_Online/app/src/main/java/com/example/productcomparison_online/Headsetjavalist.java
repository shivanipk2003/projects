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

public class Headsetjavalist extends BaseAdapter {

    ArrayList<String> himg;
    ArrayList<String> hprice;
    ArrayList<String> hname;
    ArrayList<String> head;

    Context context;
    LayoutInflater inflater;

    public Headsetjavalist(ArrayList<String> himg,ArrayList<String> head, ArrayList<String> hprice, ArrayList<String> hname, Context context) {
        this.himg = himg;
        this.hprice = hprice;
        this.hname = hname;
        this.context = context;
        this.head=head;

        inflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return hname.size();
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
        view=inflater.inflate(R.layout.listshowxml,null);

        ImageView icon = (ImageView) view.findViewById(R.id.bookimg);
        TextView textname=(TextView)view.findViewById(R.id.bookname);
        TextView textprice=(TextView)view.findViewById(R.id.bookprice);
        TextView texthead=(TextView)view.findViewById(R.id.bookhead);

        Picasso.get().load(himg.get(i)).into(icon);
        textname.setText(hname.get(i));
        textprice.setText(hprice.get(i));
        texthead.setText(head.get(i));

        return view;
    }
}
