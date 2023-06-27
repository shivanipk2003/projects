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

public class Listshowdetails extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    ArrayList<String> img;
    ArrayList<String> name;
    ArrayList<String> price;
    ArrayList<String> head;

    public Listshowdetails(Context context, ArrayList<String> img, ArrayList<String> name, ArrayList<String> price, ArrayList<String> head) {
        this.context = context;
        this.img = img;
        this.name = name;
        this.price = price;
        this.head=head;

        inflater=(LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        return name.size();
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

        view = inflater.inflate(R.layout.listshowxml, null);
        ImageView icon = (ImageView) view.findViewById(R.id.bookimg);
        TextView textname=(TextView)view.findViewById(R.id.bookname);
        TextView textprice=(TextView)view.findViewById(R.id.bookprice);
        TextView texthead=(TextView)view.findViewById(R.id.bookhead);
        Picasso.get().load(img.get(i)).into(icon);
        textname.setText(name.get(i));
        textprice.setText(price.get(i));
        texthead.setText(head.get(i));
        return view;
    }
}
