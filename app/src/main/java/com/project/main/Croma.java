package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Croma extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running Croma on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(18,strings[0],"product__list--item", "a", "h3", "pdpPrice", "pdpPriceMrp", "_primaryImg", "col-md-3   col-xs-3 listingDiscnt");
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Ended Croma");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Croma not working" + e);
            return null;
        }
    }
}
