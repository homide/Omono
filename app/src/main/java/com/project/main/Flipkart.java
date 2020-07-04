package com.project.main;

import android.os.AsyncTask;
import android.telecom.Call;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Flipkart extends AsyncTask<String, Void, ArrayList<String>> implements arraySave {
    ArrayList<String> temptitlestore = new ArrayList<>();
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> tempimageurl = new ArrayList<>();
    ArrayList<String> tempdiscount = new ArrayList<>();
    ArrayList<String> temppricebefore = new ArrayList<>();
    ArrayList<String> tag = new ArrayList<>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<String> s) {
        super.onPostExecute(s);
        arraySave.discountedprice.addAll(s);
        arraySave.discount.addAll(tempdiscount);
        arraySave.pricebefore.addAll(temppricebefore);
        arraySave.titleallproducts.addAll(temptitlestore);
        arraySave.producturl.addAll(tempurlstore);
        arraySave.imageurls.addAll(tempimageurl);
        arraySave.tag.addAll(tag);

    }

    @Override
    protected ArrayList<String> doInBackground(final String... strings) {
        try{
            Document doc = Jsoup.connect(strings[0]).get();
            Element s = doc.getElementsByClass("_1KHd47").get(1);
            String a = s.text();
            if(s.equals("Mobiles & Accessories") ||s.equals("Home Entertainment")){
                Calling calling = new Calling();
                calling.call(1,strings[0],"IIdQZO _1SSAGr","a","_2mylT6 _3Ockxk","_1vC4OE","_3auQ3N","img","VGWI6T");
                temptitlestore = calling.temptitlestore;
                tempimageurl= calling.tempimageurl;
                tempdiscount = calling.tempdiscount;
                temppricebefore = calling.temppricebefore;
                tag = calling.tag;
                ArrayList<String> urlstore = calling.tempurlstore;
                for(String t: urlstore){
                    String b = "https://www.flipkart.com" + t;
                    tempurlstore.add(b);
                }
                ArrayList<String> mainlist= calling.temppriceafter;
                return mainlist;
            }
            else if(s.equals("Clothing and Accessories") || s.equals("Footwear") || s.equals("Sunglasses")){
                Calling calling = new Calling();
                calling.call(1,strings[0],"IIdQZO _1SSAGr","a","_2mylT6 _3Ockxk","_1vC4OE","_3auQ3N","img","VGWI6T");
                temptitlestore = calling.temptitlestore;
                tempimageurl= calling.tempimageurl;
                tempdiscount = calling.tempdiscount;
                temppricebefore = calling.temppricebefore;
                tag = calling.tag;
                ArrayList<String> urlstore = calling.tempurlstore;
                for(String t: urlstore){
                    String b = "https://www.flipkart.com" + t;
                    tempurlstore.add(b);
                }
                ArrayList<String> mainlist= calling.temppriceafter;
                return mainlist;
            }
            else {
                Calling calling = new Calling();
                calling.call(1,strings[0],"IIdQZO _1SSAGr","a","_2mylT6 _3Ockxk","_1vC4OE","_3auQ3N","img","VGWI6T");
                temptitlestore = calling.temptitlestore;
                tempimageurl= calling.tempimageurl;
                tempdiscount = calling.tempdiscount;
                temppricebefore = calling.temppricebefore;
                tag = calling.tag;
                ArrayList<String> urlstore = calling.tempurlstore;
                for(String t: urlstore){
                    String b = "https://www.flipkart.com" + t;
                    tempurlstore.add(b);
                }
                ArrayList<String> mainlist= calling.temppriceafter;
                return mainlist;
            }

        }catch (Exception e){
            return null;
        }
    }


}
