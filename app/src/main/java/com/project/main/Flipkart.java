package com.project.main;

import android.os.AsyncTask;
import android.telecom.Call;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Flipkart extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
    ArrayList<Product> products = new ArrayList<>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<Product> s) {
        super.onPostExecute(s);
        arraySave.products.addAll(products);

    }

    @Override
    protected ArrayList<Product> doInBackground(final String... strings) {
        try{
            Document doc = Jsoup.connect(strings[0]).get();
            Element s = doc.getElementsByClass("_1KHd47").get(1);
            String a = s.text();
            if(a.equals("Mobiles & Accessories") ||a.equals("Home Entertainment")){
                Calling calling = new Calling();
                calling.call(1,strings[0],"_3O0U0u","a","_3wU53n","_1vC4OE _2rQ-NK","_3auQ3N _2GcJzG","img","VGWI6T");
                products = calling.products;
                return products;
            }
            else if(a.equals("Clothing and Accessories") || a.equals("Footwear") || a.equals("Sunglasses")){
                Calling calling = new Calling();
                calling.call(1,strings[0],"IIdQZO _1SSAGr","a","_2mylT6 _3Ockxk","_1vC4OE","_3auQ3N","img","VGWI6T");
                products = calling.products;
                return products;
            }
            else {
                Calling calling = new Calling();
                calling.call(1,strings[0],"_3liAhj","a","_2cLu-l","_1vC4OE","_3auQ3N","img","VGWI6T");
                products = calling.products;
                return products;
            }

        }catch (Exception e){
            return null;
        }
    }


}
