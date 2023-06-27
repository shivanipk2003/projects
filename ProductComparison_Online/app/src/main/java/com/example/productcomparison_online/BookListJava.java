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

public class BookListJava extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

    ArrayList<String> img1;
    ArrayList<String> name1;
    ArrayList<String> price1;
    ArrayList<String> head1;

    public BookListJava(Context context, ArrayList<String> img1, ArrayList<String> name1, ArrayList<String> price1, ArrayList<String> head1) {
        this.context = context;
        this.img1 = img1;
        this.name1 = name1;
        this.price1 = price1;
        this.head1=head1;

        inflater=(LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        return name1.size();
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
        Picasso.get().load(img1.get(i)).into(icon);
        textname.setText(name1.get(i));
        textprice.setText(price1.get(i));
        return view;
    }
}
