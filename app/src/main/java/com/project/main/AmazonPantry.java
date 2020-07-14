package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class AmazonPantry extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
    //ArrayList for corresponding objects
    ArrayList<Product> products = new ArrayList<>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<Product> s) {
        super.onPostExecute(s);
        arraySave.products.addAll(s);

    }

    @Override
    protected ArrayList<Product> doInBackground(String... strings) {
        try{
            System.out.println("Running Amazon Pantry on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(9,strings[0],"celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results","a","a-size-base-plus a-color-base a-text-normal","a-price-whole","a-offscreen", "s-image","auto");
            //initialising and referencing Calling method variables
            products = calling.products;
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Amazon pantry not working" + e);
            return null;
        }
    }
}
