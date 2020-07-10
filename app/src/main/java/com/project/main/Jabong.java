package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Jabong extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running Jabong on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(10,strings[0],"rilrtl-products-list__link","a","name","price  ","orginal-price","rilrtl-lazy-img  rilrtl-lazy-img-loaded","discount");
            //initialising and referencing Calling method variables
            this.products = calling.products;
            System.out.println("Jabing ended");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Jabong not working" + e);
            return null;
        }
    }
}
