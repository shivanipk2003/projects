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

public class HeadsetListJava extends BaseAdapter {
    ArrayList<String> himg1;
    ArrayList<String> hprice1;
    ArrayList<String> hname1;
    ArrayList<String> head1;

    Context context;
    LayoutInflater inflater;

    public HeadsetListJava(ArrayList<String> himg1,ArrayList<String> head1, ArrayList<String> hprice1, ArrayList<String> hname1, Context context) {
        this.himg1 = himg1;
        this.hprice1 = hprice1;
        this.hname1 = hname1;
        this.context = context;
        this.head1=head1;

        inflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return hname1.size();
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

        Picasso.get().load(himg1.get(i)).into(icon);
        textname.setText(hname1.get(i));
        textprice.setText(hprice1.get(i));
        texthead.setText(head1.get(i));

        return view;
    }
}
