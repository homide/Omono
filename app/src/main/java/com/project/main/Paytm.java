package com.project.main;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Paytm extends AsyncTask<String, Void, ArrayList<String>> {
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> temptitlestore = new ArrayList<>();
    ArrayList<String> tempimageurl = new ArrayList<>();
    ArrayList<String> titleallproducts = new ArrayList<String>();
    ArrayList<String> allproducts = new ArrayList<String>(); // all products combine
    ArrayList<String> producturl = new ArrayList<String>();
    ArrayList<String> imageurls = new ArrayList<String>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<String> s) {
        String product, urlstore,title,imagelink;
        super.onPostExecute(s);
        for (int j = 0; j < 6; j++) {
            product = s.get(j);
            title = temptitlestore.get(j);
            titleallproducts.add(title);
            urlstore = tempurlstore.get(j);
            allproducts.add(product);
            producturl.add(urlstore);
            imagelink = tempimageurl.get(j);
            imageurls.add(imagelink);
        }
        String seemore = "SHOW MORE PRODUCTS ON PAYTM MALL......";
        String seemoreimage = "https://previews.123rf.com/images/brisker/brisker1605/brisker160500027/58489983-16-icons-of-different-products-categories-for-online-shops.jpg";
        allproducts.add("");
        titleallproducts.add(seemore);
        producturl.add(link);
        imageurls.add(seemoreimage);
    }

    public ArrayList<ArrayList> arrayReturn(){

        ArrayList<ArrayList> containsAllArray = new ArrayList<>();
        containsAllArray.add(titleallproducts);
        containsAllArray.add(allproducts);
        containsAllArray.add(producturl);
        containsAllArray.add(imageurls);
        return containsAllArray;
    }


    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        try{
            Calling calling = new Calling();
            link = strings[0];
            calling.call(strings[0],"_3WhJ","a","UGUy","_1kMS","dQm2",
                    "presentation","c-ax");
            temptitlestore = calling.temptitlestore;
            tempimageurl = calling.tempimageurl;
            tempurlstore = calling.tempurlstore;
            ArrayList<String> mainlist= calling.mainlist;
            return mainlist;
        }catch (Exception e){
            System.out.println("Paytm not working" + e);
            return null;
        }
    }
}
