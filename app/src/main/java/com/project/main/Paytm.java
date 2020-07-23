//Refer to MainActivity, Main2Activity and Calling for commenting references

package com.project.main;

import android.os.AsyncTask;
import java.util.ArrayList;

public class Paytm extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {

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
            System.out.println("Running paytm on thread");
            //initialising calling.java and referencing it
            CallingGeneral calling = new CallingGeneral();
            link = strings[0];
            calling.call(4,strings[0],"_3WhJ","a","UGUy","_1kMS","dQm2",
                    "img","c-ax","No use","No use");
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Ended paytm");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Paytm not working" + e);
            return null;
        }
    }
}