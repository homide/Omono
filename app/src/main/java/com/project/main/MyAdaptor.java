package com.project.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
            String rating = product.rating;
            float ratingvalue = Float.parseFloat(rating);
            holder.mainTitle.setText(product.title);
            holder.priceBefore.setText(product.priceBefore);
            holder.discPrice.setText(product.discountedPrice);
            holder.discount.setText(product.discount);
            holder.ratingBar.setRating(ratingvalue);
            holder.ratingCount.setText(product.ratingCount);
            String tag = product.tag;
            switch (tag){
                case "Paytm":
                    Picasso.with(context).load("https://store-images.s-microsoft.com/image/apps.30876.14627692376548906.9a3f6f3d-f2dd-491a-82aa-74db97c80cdd.2c236b26-6975-47ba-8423-4ad4fef81746").into(holder.tag);
                    break;
                case "Shopclues":
                    Picasso.with(context).load("https://bharatstories.in/wp-content/uploads/2019/12/ShopClues-1200x858.png").into(holder.tag);
                    break;
                case "Snapdeal":
                    Picasso.with(context).load("https://pbs.twimg.com/profile_images/774270055555137538/MHkmX_fU_400x400.jpg").into(holder.tag);
                    break;
                case "Flipkart":
                    Picasso.with(context).load("https://pbs.twimg.com/profile_images/1267713887165485061/WUR4QXtd.jpg").into(holder.tag);
                    break;
                case "Amazon":
                    Picasso.with(context).load("https://thumbor.forbes.com/thumbor/fit-in/416x416/filters%3Aformat%28jpg%29/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F5d825aa26de3150009a4616c%2F0x0.jpg%3Fbackground%3D000000%26cropX1%3D0%26cropX2%3D416%26cropY1%3D0%26cropY2%3D416").into(holder.tag);
                    break;
                case "Koovs":
                    Picasso.with(context).load("https://res-4.cloudinary.com/crunchbase-production/image/upload/c_lpad,h_256,w_256,f_auto,q_auto:eco/v1397190836/593d89ec956537ccb4212dd87539d092.jpg").into(holder.tag);
                    break;
                case "Bewakoof":
                    Picasso.with(context).load("https://images.bewakoof.com/original/bewakoof-b--app-logo-bewakoof-com-new-logo-1483957527.jpg").into(holder.tag);
                    break;
            }
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
        public TextView mainTitle, priceBefore, discPrice, discount, ratingCount;
        public ImageView productImage,tag;
        public RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mainTitle = itemView.findViewById(R.id.mainTitle);
            priceBefore = itemView.findViewById(R.id.priceBefore);
            discPrice = itemView.findViewById(R.id.discPrice);
            discount = itemView.findViewById(R.id.discount);
            productImage = itemView.findViewById(R.id.productImage);
            tag = itemView.findViewById(R.id.tag);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingCount = itemView.findViewById(R.id.ratingCount);
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