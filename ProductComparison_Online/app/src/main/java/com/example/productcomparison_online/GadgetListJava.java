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

public class GadgetListJava extends BaseAdapter {
    ArrayList<String> gimg1;
    ArrayList<String> gprice1;
    ArrayList<String> gname1;
    ArrayList<String> head1;

    Context context;
    LayoutInflater layoutInflater;


    public GadgetListJava(ArrayList<String> gimg1,ArrayList<String> head1, ArrayList<String> gprice1, ArrayList<String> gname1, Context context) {

        this.gimg1 = gimg1;
        this.gprice1 = gprice1;
        this.gname1 = gname1;
        this.context = context;
        this.head1=head1;

        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return gname1.size();
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

        Picasso.get().load(gimg1.get(i)).into(icon);
        textname.setText(gname1.get(i));
        textprice.setText(gprice1.get(i));
        texthead.setText(head1.get(i));

        return view;
    }
}
