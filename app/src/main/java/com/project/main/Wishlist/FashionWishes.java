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

public class FashionWishes extends Fragment {

    View view;
    String state,city;
    RecyclerView recyclerView;

    Context context;

    TextView noSaveData;
    ArrayList<Product> mainList;
    DatabaseHelper databaseHelper;

    public FashionWishes(Context context){
        this.context = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wishlist_common_fragment, container, false);

        noSaveData = view.findViewById(R.id.noWishesText);
        noSaveData.setVisibility(View.INVISIBLE);
        mainList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(context);

        return view;
    }

}