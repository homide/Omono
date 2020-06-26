package com.project.main;

import android.os.AsyncTask;

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
        try {
            Document doc = Jsoup.connect(strings[0]).get();
            Elements links = doc.getElementsByClass("_3O0U0u");
            Elements links1 = doc.getElementsByClass("_3liAhj");
            Elements fashions = doc.getElementsByClass("IIdQZO _1SSAGr");
            Elements maskssans = doc.getElementsByClass("_3liAhj");
            ArrayList<String> mainlist = new ArrayList<String>();
            link = strings[0];



            for (Element testlink1 : links) {
                String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                String permanent1 = null;

                Elements eltitle1 = testlink1.getElementsByClass("_3wU53n");

                if (eltitle1.size() > 0) {
                    for (Element link : links) {

                        Elements elLink = link.getElementsByTag("a");

                        Elements eltitle2 = testlink1.getElementsByClass("_3wU53n");

                        Elements elpricebefore = link.getElementsByClass("_3auQ3N _2GcJzG");

                        Elements elpriceafter = link.getElementsByClass("_1vC4OE _2rQ-NK");

                        Elements elproductimage = link.getElementsByClass("_3BTv9X");

                        Elements discount = link.getElementsByClass("VGWI6T");


                        for (Element titleOfProduct : eltitle2) {
                            temp1 = titleOfProduct.text();

                        }

                        //product original price loop
                        for (Element priceOfProductBefore : elpricebefore) {
                            if(priceOfProductBefore == null){
                                temp2 = "";
                            }else{
                                temp2 = "Price before: " + priceOfProductBefore.text();
                            }
                        }

                        //product discounted price loop
                        for (Element priceOfProductAfter : elpriceafter) {
                            if(temp3 == null){
                                temp3 = "Price: " + priceOfProductAfter.text();
                            }else{
                                temp3 = "Discounted price: " + priceOfProductAfter.text();
                            }

                        }

                        //discount in number loop
                        for (Element productdiscount : discount) {
                            if(discount == null){
                                temp4 = "";
                            }else{
                                temp4 = "Discount: " + productdiscount.text();
                            }

                        }

                        ArrayList<String> linkArray = new ArrayList<String>();
                        for (Element elementLink : elLink) {
                            String MainLink = elementLink.attr("href");
                            linkArray.add(MainLink);
                        }
                        for (int i = 0; i < (linkArray.size()); i++) {
                            temp5 = "https://www.flipkart.com" + linkArray.get(0);
                        }


                        for(Element elimage : elproductimage){
                            temp6 = elimage.attr("src");
                        }

                        if (elpricebefore.text()==null)
                        {
                            permanent1 = "Price :" + elpriceafter.text() + "\n" + temp4 + "\n";
                        }

                        else
                        {
                            permanent1 = temp2 + "\n"  + temp3 + "\n" + temp4 + "\n";

                        }
                        temptitlestore.add(temp1);
                        mainlist.add(permanent1);
                        tempurlstore.add(temp5);
                        tempimageurl.add(temp6);
                    }
                }
            }

            for (Element testlink2 : links1) {
                Elements Testrun = testlink2.getElementsByClass("_1rcHFq");
                String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                String permanent1 = null;

                if (Testrun.size() > 0) {

                    for (Element link1 : links1) {
                        Elements elLink1 = link1.getElementsByTag("a");

                        Elements eltitle2 = link1.getElementsByClass("_2cLu-l");


                        Elements elpricebefore1 = link1.getElementsByClass("_1vC4OE");


                        Elements elpriceafter1 = link1.getElementsByClass("_3auQ3N");

                        Elements elproductimage = link1.getElementsByClass("_3BTv9X");

                        Elements discount1 = link1.getElementsByClass("VGWI6T");


                        //product title loop
                        if (eltitle2.size() > 0) {
                            for (Element titleOfProduct : eltitle2) {
                                temp1 = titleOfProduct.text();

                            }

                            //product original price loop
                            for (Element priceOfProductBefore : elpricebefore1) {
                                temp2 = "Price before: " + priceOfProductBefore.text();
                            }

                            //product discounted price loop
                            for (Element priceOfProductAfter : elpriceafter1) {
                                temp3 = "Discounted price: " + priceOfProductAfter.text();
                            }

                            //discount in number loop
                            for (Element productdiscount : discount1) {
                                temp4 = "Discount: " + productdiscount.text();
                            }

                            ArrayList<String> linkArray = new ArrayList<String>();
                            for (Element elementLink : elLink1) {
                                String MainLink = elementLink.attr("href");
                                linkArray.add(MainLink);
                            }
                            for (int i = 0; i < 1; i++) {
                                temp5 = "https://www.flipkart.com" + linkArray.get(0);
                            }

                            for(Element elimage : elproductimage){
                                temp6 = elimage.attr("src");
                            }


                            if (elpricebefore1.text()==null)
                            {
                                permanent1 = "Price :" + elpriceafter1.text() + "\n" + temp4 + "\n";
                            }

                            else
                            {
                                permanent1 =temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

                            }
                            temptitlestore.add(temp1);
                            mainlist.add(permanent1);
                            tempurlstore.add(temp5);
                            tempimageurl.add(temp6);
                        }
                    }
                }
            }

            for (Element fashion : fashions) {
                //BatchUpdates
                Elements fashiontitle = fashion.getElementsByClass("_2mylT6");

                String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                String permanent1 = null;

                if (fashiontitle.size() > 0) {
                    for (Element fash : fashions) {

                        Elements flink = fash.getElementsByTag("a");

                        Elements fashiontitlem = fashion.getElementsByClass("_2mylT6");

                        Elements fpricebefore = fash.getElementsByClass("_3auQ3N");


                        Elements fpriceafter = fash.getElementsByClass("_1vC4OE");

                        Elements elproductimage = fash.getElementsByClass("_3togXc");

                        Elements fdiscount = fash.getElementsByClass("VGWI6T");


                        for (Element ftitle : fashiontitlem) {
                            temp1 = ftitle.text();

                        }

                        //product original price loop
                        for (Element fproductpricebefore : fpricebefore) {
                            temp2 = "Price before: " + fproductpricebefore.text();
                        }

                        //product discounted price loop
                        for (Element fproductproceafter : fpriceafter) {
                            temp3 = "Discounted price: " + fproductproceafter.text();

                        }

                        //discount in number loop
                        for (Element fproductdiscount : fdiscount) {
                            temp4 = "Discount: " + fproductdiscount.text();

                        }

                        for(Element elimage : elproductimage){
                            temp6 = elimage.attr("src");
                        }


                        if (fpricebefore.text()==null)
                        {
                            permanent1 = "Price :" + fpriceafter.text() + "\n" + temp4 + "\n";
                        }

                        else
                        {
                            permanent1 =temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

                        }
                        temptitlestore.add(temp1);
                        mainlist.add(permanent1);
                        tempurlstore.add(temp5);
                        tempimageurl.add(temp6);
                    }

                }

            }

            for (Element maska : maskssans) {
                Elements masktitle = maska.getElementsByClass("_2cLu-l");

                String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                String permanent1 = null;

                if (masktitle.size() > 0) {
                    for (Element mask : maskssans) {

                        Elements mlink = mask.getElementsByTag("a");

                        Elements masktitle1 = maska.getElementsByClass("_2cLu-l");


                        Elements mpricebefore = mask.getElementsByClass("_3auQ3N");


                        Elements mpriceafter = mask.getElementsByClass("_1vC4OE");

                        Elements elproductimage =  mask.getElementsByClass("Zhf2z-");


                        Elements mdiscount = mask.getElementsByClass("VGWI6T");

                        for (Element mtitle : masktitle1) {
                            temp1 = mtitle.text();

                        }

                        //product original price loop
                        for (Element mproductpricebefore : mpricebefore) {
                            temp2 = "Price before: " + mproductpricebefore.text();
                        }

                        //product discounted price loop
                        for (Element mproductproceafter : mpriceafter) {
                            temp3 = "Discounted price: " + mproductproceafter.text();

                        }

                        //discount in number loop
                        for (Element mproductdiscount : mdiscount) {
                            temp4 = "Discount: " + mproductdiscount.text();

                        }

                        for(Element elimage : elproductimage){
                            temp6 = elimage.attr("src");
                        }

                        ArrayList<String> linkArray = new ArrayList<String>();
                        for (Element melementLink : mlink) {
                            String fMainLink = melementLink.attr("href");
                            linkArray.add(fMainLink);
                        }
                        for (int i = 0; i < (linkArray.size()); i++) {
                            temp5 = "https://www.flipkart.com" + linkArray.get(0);
                        }


                        if (mpricebefore.text()==null)
                        {
                            permanent1 = "Price :" + mpriceafter.text() + "\n" + temp4 + "\n";
                        }

                        else
                        {
                            permanent1 =temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

                        }
                        temptitlestore.add(temp1);
                        mainlist.add(permanent1);
                        tempurlstore.add(temp5);
                        tempimageurl.add(temp6);

                    }
                }

            }
            return mainlist;
        } catch (Exception e) {
            ArrayList<String> exception = new ArrayList<String>();
            String ex = e.toString();
            exception.add(ex);
            return exception;
        }
    }
}
