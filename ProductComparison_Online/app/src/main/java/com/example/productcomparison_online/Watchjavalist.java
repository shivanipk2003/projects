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

public class Watchjavalist extends BaseAdapter {

    ArrayList<String> wimg;
    ArrayList<String> wprice;
    ArrayList<String> wname;
    ArrayList<String> head;

    Context context;
    LayoutInflater layoutInflater;

    public Watchjavalist(ArrayList<String> wimg,ArrayList<String> head, ArrayList<String> wprice, ArrayList<String> wname, Context context) {
        this.wimg = wimg;
        this.wprice = wprice;
        this.wname = wname;
        this.context = context;
        this.head=head;

        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return wname.size();
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

        Picasso.get().load(wimg.get(i)).into(icon);
        textname.setText(wname.get(i));
        textprice.setText(wprice.get(i));
        texthead.setText(head.get(i));

        return view;
    }
}
