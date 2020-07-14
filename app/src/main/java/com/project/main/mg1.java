package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class mg1 extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running 1mg on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(15,strings[0],"style__product-box___3oEU6" , "a", "style__pro-title___3G3rr","style__price-tag___KzOkY", "style__discount-price___qlNIB" , "style__image___Ny-Sa style__loaded___22epL","style__off-badge___1-Jwf" );
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Ended 1mg");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("1mg not working" + e);
            return null;
        }
    }
}
