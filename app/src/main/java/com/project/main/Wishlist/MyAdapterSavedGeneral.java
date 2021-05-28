package com.project.main.Wishlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.main.DatabaseHelper;
import com.project.main.MyAdapterGeneral;
import com.project.main.Product;
import com.project.main.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterSavedGeneral extends RecyclerView.Adapter<MyAdapterSavedGeneral.ViewHolder> {
    Context context;
    ArrayList<Product> products;

    public MyAdapterSavedGeneral(Context c, ArrayList<Product> products){
        this.context = c;
        this.products = products;
    }

    @NonNull
    @Override
    public MyAdapterSavedGeneral.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyAdapterSavedGeneral.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterSavedGeneral.ViewHolder holder, int position) {
        Product product = products.get(position);
        try {
            holder.saveButton.setVisibility(View.INVISIBLE);
            holder.mainTitle.setText(product.getTitle());
            holder.priceBefore.setText(product.getPriceBefore());
            holder.discPrice.setText(product.getDiscountedPrice());
            holder.discount.setText(product.getDiscount());
            holder.ratingBar.setRating(product.getRating());
            holder.ratingCount.setText(product.getRatingCount());
            String tag = product.getTag();
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
            if (product.getImageLink().length() <= 0) {
                Picasso.with(context).load("https://1m19tt3pztls474q6z46fnk9-wpengine.netdna-ssl.com/wp-content/themes/unbound/images/No-Image-Found-400x264.png").into(holder.productImage);
            } else {
                Picasso.with(context).load(product.getImageLink()).into(holder.productImage);
            }
        } catch (Exception m) {
            System.out.println(m);
        }

        holder.deleteButton.setOnClickListener(v->{
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            @SuppressLint("InflateParams") View popupView = inflater.inflate(R.layout.delete_layout,null);
            final int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
            popupWindow.setOutsideTouchable(false);
            popupWindow.update();
            popupWindow.showAtLocation(v, Gravity.CENTER,0,0);

            Button cancelButton = popupView.findViewById(R.id.cancelButtonDelete);
            Button confirmButton = popupView.findViewById(R.id.confirmButtonDelete);

            cancelButton.setOnClickListener(v1 -> {
                popupWindow.dismiss();
            });

            confirmButton.setOnClickListener(v1->{
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                if (databaseHelper.deleteGeneralItems(product.getProductLink())) {
                    notifyDataSetChanged();
                    Toast.makeText(context, "Deleted data!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Error in deleting!", Toast.LENGTH_LONG).show();
                }
                popupWindow.dismiss();
            });
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mainTitle, priceBefore, discPrice, discount, ratingCount;
        public ImageView productImage,tag;
        public RatingBar ratingBar;
        ImageButton saveButton, deleteButton;
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
            saveButton = itemView.findViewById(R.id.saveButton);
            deleteButton = itemView.findViewById(R.id.deleteItem);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Product url = products.get(position);
            String link = url.getProductLink();
            Intent intent = new Intent((Intent.ACTION_VIEW));
            intent.setData(Uri.parse(link));
            context.startActivity(intent);
        }
    }

}
