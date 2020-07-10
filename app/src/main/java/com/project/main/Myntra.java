package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Myntra extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running Myntra on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(11, strings[0],"product-base","a","product-product","product-discountedPrice","product-strike","img","product-discountPercentage" );
            //initialising and referencing Calling method variables
            this.products = calling.products;
            System.out.println("Myntra ended");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Myntra not working" + e);
            return null;
        }
    }
}
