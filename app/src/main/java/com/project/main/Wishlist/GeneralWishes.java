package com.project.main.Wishlist;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.main.DatabaseHelper;
import com.project.main.Product;
import com.project.main.R;

import java.util.ArrayList;

public class GeneralWishes extends Fragment {

    View view;
    String state,city;
    RecyclerView recyclerView;

    Context context;

    TextView noSaveData;
    ArrayList<Product> mainList;
    DatabaseHelper databaseHelper;
    MyAdapterSavedGeneral adapter;

    public GeneralWishes(Context context){
        this.context = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wishlist_common_fragment, container, false);

        noSaveData = view.findViewById(R.id.noWishesText);
        noSaveData.setVisibility(View.INVISIBLE);
        mainList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(context);

        try {
            Cursor cur = databaseHelper.getData("generalItems");
            while (cur.moveToNext()){
                String title = cur.getString(1);
                String priceBefore = cur.getString(2);
                String discountedPrice = cur.getString(3);
                String discount = cur.getString(4);
                String imageLink= cur.getString(5);
                String productLink= cur.getString(6);
                String tag = cur.getString(7);
                float rating = cur.getFloat(8);
                String ratingCount = cur.getString(9);
                Product oxy = new Product(title,priceBefore,discountedPrice,discount,imageLink,productLink,tag,rating,ratingCount);
                mainList.add(oxy);
            }
        }catch (Exception e){

        }
        if (mainList.size() <= 0){
            noSaveData.setVisibility(View.VISIBLE);
        }else{
            recyclerView = view.findViewById(R.id.recycler_view);
            adapter = new MyAdapterSavedGeneral(context, mainList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

}