package com.project.main;

import android.os.AsyncTask;
import android.os.Handler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Flipkart extends AsyncTask<String, Void, ArrayList<String>> {
    ArrayList<String> temptitlestore = new ArrayList<>();
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> tempimageurl = new ArrayList<>();
    ArrayList<String> titleallproducts = new ArrayList<String>();
    ArrayList<String> allproducts = new ArrayList<String>();
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
        String seemore = "SHOW MORE PRODUCTS ON FLIPKART......";
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
    protected ArrayList<String> doInBackground(final String... strings) {
        try{
            Document doc = Jsoup.connect(strings[0]).get();
            Elements links = doc.getElementsByClass("_3O0U0u");
            Elements links1 = doc.getElementsByClass("_3liAhj");
            Elements links2 = doc.getElementsByClass("IIdQZO _1SSAGr");
            Elements link3 = doc.getElementsByClass("_3liAhj");
            ArrayList<String> mainlist = null;

            for(Element testlink1 : links){
                Elements eltitle1 = testlink1.getElementsByClass("_3wU53n");
                if(eltitle1.size() > 0){
                    Calling calling = new Calling();
                    link = strings[0];
                    calling.call(1,strings[0],"_3O0U0u","a","_3wU53n","_1vC4OE _2rQ-NK","_3auQ3N _2GcJzG","_3BTv9X",
                            "VGWI6T");
                    temptitlestore = calling.temptitlestore;
                    tempimageurl = calling.tempimageurl;
                    tempurlstore = calling.tempurlstore;
                    mainlist= calling.mainlist;
                }
                else {
                    break;
                }
            }

            for(Element testlink2 : links1){
                Elements Testrun = testlink2.getElementsByClass("_1rcHFq");
                if(Testrun.size() > 0){
                    Calling calling = new Calling();
                    link = strings[0];
                    calling.call(1,strings[0],"_3liAhj","a","_2cLu-l","_3auQ3N","_1vC4OE","_3BTv9X","VGWI6T");
                    temptitlestore = calling.temptitlestore;
                    tempimageurl = calling.tempimageurl;
                    tempurlstore = calling.tempurlstore;
                    mainlist= calling.mainlist;
                }
                else{
                    break;
                }
            }
            for (Element fashion : links2){
                Elements fashiontitle = fashion.getElementsByClass("_2mylT6");
                if(fashiontitle.size() >0){
                    Calling calling = new Calling();
                    link = strings[0];
                    calling.call(1,strings[0],"IIdQZO _1SSAGr","a","_2mylT6","_1vC4OE","_3auQ3N","_3togXc","VGWI6T");
                    temptitlestore = calling.temptitlestore;
                    tempimageurl = calling.tempimageurl;
                    tempurlstore = calling.tempurlstore;
                    mainlist= calling.mainlist;
                }
                else {
                    break;
                }
            }
            for(Element maska : link3){
                Elements masktitle = maska.getElementsByClass("_2cLu-l");
                if(masktitle.size() >0){
                    Calling calling = new Calling();
                    link = strings[0];
                    calling.call(1,strings[0],"_3liAhj","a","_2cLu-l","_1vC4OE","_3auQ3N","Zhf2z-","VGWI6T");
                    temptitlestore = calling.temptitlestore;
                    tempimageurl = calling.tempimageurl;
                    tempurlstore = calling.tempurlstore;
                    mainlist= calling.mainlist;
                }
                else {
                    break;
                }
            }
            return mainlist;

        }catch (Exception e){
            System.out.println("Flipkart not working" + e);
            return null;
        }
    }
}
