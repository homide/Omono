package com.project.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdaptor extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> aboutproduct;
    ArrayList<String> producturl;
    ArrayList<String> producttitle;
    ArrayList<String> imageurl;
    MyAdaptor(Context c, ArrayList<String> aboutproduct, ArrayList<String> producturl,ArrayList<String> producttitle, ArrayList<String> imageurl){
        super(c,R.layout.row,R.id.textView1,aboutproduct);
        this.context = c;
        this.aboutproduct = aboutproduct;
        this.producturl = producturl;
        this.producttitle = producttitle;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row,parent,false);
        TextView producttitleshow = row.findViewById(R.id.textView1);
        TextView aboutproductdisplay = row.findViewById(R.id.textView2);
        ImageView iv = row.findViewById(R.id.productimage);
        Context context = parent.getContext();
        try {
            if(imageurl.get(position).length() <=0){
                Picasso.with(context).load("https://1m19tt3pztls474q6z46fnk9-wpengine.netdna-ssl.com/wp-content/themes/unbound/images/No-Image-Found-400x264.png").into(iv);
            }else {
                Picasso.with(context).load(imageurl.get(position)).into(iv);
            }
        } catch (Exception m) {

        }
        aboutproductdisplay.setText(aboutproduct.get(position));
        producttitleshow.setText(producttitle.get(position));
        if(position == 0){
            producttitleshow.setTextSize(30);
            producttitleshow.setGravity(20);
        }
        if(position == 7){
            producttitleshow.setTextSize(30);
            producttitleshow.setGravity(20);
        }
        if(position == 14){
            producttitleshow.setTextSize(30);
            producttitleshow.setGravity(20);
        }
        if(position == 21){
            producttitleshow.setTextSize(30);
            producttitleshow.setGravity(20);
        }

        return row;
    }
}