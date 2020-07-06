package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Bigbasket extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running Bigbasket on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(7,strings[0],"item prod-deck row ng-scope","a","ng-binding",
                    "discnt-price","mp-price ng-scope","img-responsive blur-up lazyautosizes lazyloaded","ng-scope");
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Bigbasket ended");
            return null;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Bigbasket not working" + e);
            return null;
        }
    }
}
