//Refer to MainActivity, Main2Activity and Calling for commenting references

package com.project.main;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Paytm extends AsyncTask<String, Void, ArrayList<String>> {

    //ArrayList for corresponding objects
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
        //loop to get objects ig
        for (int j = 0; j < 5; j++) {
            product = s.get(j);
            title = temptitlestore.get(j);
            titleallproducts.add(title);
            urlstore ="https://www.paytmmall.com" +tempurlstore.get(j);
            //adding characters of objects to variable arraylists
            allproducts.add(product);
            producturl.add(urlstore);
            imagelink = tempimageurl.get(j);
            imageurls.add(imagelink);
        }
        //Extra adding to variable arraylists
        String seemore = "SHOW MORE PRODUCTS ON PAYTM MALL......";
        String seemoreimage = "https://previews.123rf.com/images/brisker/brisker1605/brisker160500027/58489983-16-icons-of-different-products-categories-for-online-shops.jpg";
        allproducts.add("");
        titleallproducts.add(seemore);
        producturl.add(link);
        imageurls.add(seemoreimage);
    }

    public ArrayList<ArrayList> arrayReturn(){

        //Adding all arraylists to a single arraylist for output result

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

            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(4,strings[0],"_3WhJ","a","UGUy","_1kMS","dQm2",
                    "img","c-ax");
            //initialising and referencing Calling method variables
            temptitlestore = calling.temptitlestore;
            tempurlstore = calling.tempurlstore;
            tempimageurl = calling.tempimageurl;
            ArrayList<String> mainlist= calling.mainlist;
            return mainlist;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Paytm not working" + e);
            return null;
        }
    }
}
