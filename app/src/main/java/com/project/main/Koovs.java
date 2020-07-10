package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Koovs extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running koovs on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(12, strings[0],"imageView", "a", "product_title clip-text productName","hide", "discountPrice", "prodImg", "discount_per");
            //initialising and referencing Calling method variables
            this.products = calling.products;
            System.out.println("Koovs ended");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Koovs not working" + e);
            return null;
        }
    }
}
