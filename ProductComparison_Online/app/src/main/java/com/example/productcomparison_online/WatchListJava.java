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

public class WatchListJava extends BaseAdapter {
    ArrayList<String> wimg1;
    ArrayList<String> wprice1;
    ArrayList<String> wname1;
    ArrayList<String> head1;

    Context context;
    LayoutInflater layoutInflater;

    public WatchListJava(ArrayList<String> wimg1,ArrayList<String> head1, ArrayList<String> wprice1, ArrayList<String> wname1, Context context) {
        this.wimg1 = wimg1;
        this.wprice1 = wprice1;
        this.wname1 = wname1;
        this.context = context;
        this.head1=head1;

        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return wname1.size();
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

        Picasso.get().load(wimg1.get(i)).into(icon);
        textname.setText(wname1.get(i));
        textprice.setText(wprice1.get(i));
        textprice.setText(head1.get(i));

        return view;
    }
}
