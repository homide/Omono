package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Tatacliq extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running TataCliq on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(20,strings[0],"_3QWVOHsZwQ0i2szr3TaVoZ", "a","Bt9jWqBhJEHlqtj4x2xNa", "dKCupF_5rtgdFHZiQ3xpQ MZydWWaSr0xQud-F-Jwfa",
                        "_1shOb7_K6buhJQdpA10hpT MZydWWaSr0xQud-F-Jwfa", "rnVlQIG2OU_zvETUcW0TW", "_1IxIox0fPQHTc_jS_sokZD");
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Ended Tatacliq");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("TataCliq not working" + e);
            return null;
        }
    }
}
