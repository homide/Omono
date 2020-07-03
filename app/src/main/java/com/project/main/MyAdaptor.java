package com.project.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.ViewHolder> {
    Context context;
    ArrayList<String> pricebefore, discountedprice, discount;
    ArrayList<String> producturl;
    ArrayList<String> producttitle;
    ArrayList<String> imageurl;

    public MyAdaptor(Context c, ArrayList<String> pricebefore,ArrayList<String> discountedprice, ArrayList<String> discount, ArrayList<String> producturl,ArrayList<String> producttitle, ArrayList<String> imageurl){
    //    super(c,R.layout.row,R.id.mainTitle,discountedprice);
        this.context = c;
        this.pricebefore = pricebefore;
        this.discountedprice = discountedprice;
        this.discount = discount;
        this.producturl = producturl;
        this.producttitle = producttitle;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MyAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdaptor.ViewHolder holder, int position) {

        holder.mainTitle.setText(producttitle.get(position));
        holder.priceBefore.setText(pricebefore.get(position));
        holder.discPrice.setText(discountedprice.get(position));
        holder.discount.setText(discount.get(position));
        try {
            if (imageurl.get(position).length() <= 0) {
                Picasso.with(context).load("https://1m19tt3pztls474q6z46fnk9-wpengine.netdna-ssl.com/wp-content/themes/unbound/images/No-Image-Found-400x264.png").into(holder.productImage);
            } else {
                Picasso.with(context).load(imageurl.get(position)).into(holder.productImage);
            }
        } catch (Exception m) {

        }

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mainTitle, priceBefore, discPrice, discount;
        public ImageView productImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mainTitle = itemView.findViewById(R.id.mainTitle);
            priceBefore = itemView.findViewById(R.id.priceBefore);
            discPrice = itemView.findViewById(R.id.discPrice);
            discount = itemView.findViewById(R.id.discount);
            productImage = itemView.findViewById(R.id.productImage);
        }

        @Override
        public void onClick(View v) {
            Log.d("Click from viewholder", "clicked");
            Toast.makeText(context, "yes, clicked", Toast.LENGTH_SHORT).show();

//            String link = arraySave.producturl.get(position);
//            Intent intent = new Intent((Intent.ACTION_VIEW));
//            intent.setData(Uri.parse(link));
//            context.startActivity(intent);

        }
    }
}