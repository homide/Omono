package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Grofers extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave{
    //ArrayList for corresponding objects
    ArrayList<Product> products = new ArrayList<>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<Product> s) {
        super.onPostExecute(s);
        arraySave.products.addAll(products);
    }


    @Override
    protected ArrayList<Product> doInBackground(String... strings) {
        try{
            System.out.println("Running grofers on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(6,strings[0],"product__wrapper","a","plp-product__name--box",
                    "plp-product__price--new","plp-product__price--old display--inline-block@mobile","img","plp-product__offer");
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Grofers ended");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Grofers not working" + e);
            return null;
        }
    }
}
