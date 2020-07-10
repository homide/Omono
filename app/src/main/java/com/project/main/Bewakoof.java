package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Bewakoof extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running Bewakoof on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(13,strings[0],"col-sm-4 col-xs-6", "a", "h3","discountedPriceText",
                    "actualPriceText","img", "loyaltyPriceBox");
            //initialising and referencing Calling method variables
            this.products = calling.products;
            System.out.println("Bewakoof ended");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Bewakoof not working" + e);
            return null;
        }
    }
}
