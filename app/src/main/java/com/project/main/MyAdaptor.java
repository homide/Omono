package com.project.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.ViewHolder> {
    Context context;
    ArrayList<Product> products;

    public MyAdaptor(Context c, ArrayList<Product> products){
        this.context = c;
        this.products = products;

    }

    @NonNull
    @Override
    public MyAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdaptor.ViewHolder holder, int position) {
        Product product = products.get(position);
        try {
            holder.mainTitle.setText(product.title);
            holder.priceBefore.setText(product.priceBefore);
            holder.discPrice.setText(product.discountedPrice);
            holder.discount.setText(product.discount);
        }catch (Exception m){
            System.out.println(m);
        }
        try {
            if (product.imageLink.length() <= 0) {
                Picasso.with(context).load("https://1m19tt3pztls474q6z46fnk9-wpengine.netdna-ssl.com/wp-content/themes/unbound/images/No-Image-Found-400x264.png").into(holder.productImage);
            } else {
                Picasso.with(context).load(product.imageLink).into(holder.productImage);
            }
        } catch (Exception m) {
            System.out.println(m);
        }


    }

    @Override
    public int getItemCount() {
        return products.size();
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
            int position = getLayoutPosition();
            Product url = products.get(position);
            String link = url.productLink;
            Intent intent = new Intent((Intent.ACTION_VIEW));
            intent.setData(Uri.parse(link));
            context.startActivity(intent);


        }
    }
}