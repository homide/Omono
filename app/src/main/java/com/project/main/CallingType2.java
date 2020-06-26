package com.project.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CallingType2 {
    ArrayList<String> temptitlestore = new ArrayList<>();
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> tempimageurl = new ArrayList<>();
    ArrayList<String> mainlist = new ArrayList<String>();
    String link;

    public void call(String websiteUrl,String productClass, String tagForLink, String titleClass, String discountedPriceClass, String originalPriceClass, String productImageTag,String productDiscount) throws IOException {

        Document doc = Jsoup.connect(websiteUrl).get();
        Elements links = doc.getElementsByClass(productClass);
        link = websiteUrl;


        for (Element link : links) {
            String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null, temp6 = null;
            String permanent1 = null;

            Elements elLink = link.getElementsByTag(tagForLink);

            Elements eltitle = link.getElementsByClass(titleClass);

            Elements elpricebefore = link.getElementsByClass(originalPriceClass);

            Elements elpriceafter = link.getElementsByClass(discountedPriceClass);

            Elements elproductimage = link.getElementsByTag(productImageTag);

            Elements discount = link.getElementsByClass(productDiscount);


            //product title loop
            for (Element titleOfProduct : eltitle) {
                temp1 = titleOfProduct.text();
            }

            //product original price loop
            for (Element priceOfProductBefore : elpricebefore) {
                temp2 = "Price before: " + priceOfProductBefore.text();
            }

            //product discounted price loop
            for (Element priceOfProductAfter : elpriceafter) {
                temp3 = "Discounted price: " + priceOfProductAfter.text();
            }

            //discount in number loop
            for (Element productdiscount : discount) {
                temp4 = "Discount: " + productdiscount.text();
            }

            for(Element elimage : elproductimage){
                temp6 = elimage.attr("src");
            }

            ArrayList<String> linkArray = new ArrayList<String>();
            for (Element elementLink : elLink) {
                String MainLink = elementLink.attr("href");
                linkArray.add(MainLink);
            }

            for (int j = 0; j < 1; j++) {
                temp5 = linkArray.get(0);
            }

            if (elpricebefore.text()==null)
            {
                permanent1 = "\n"+ "Price :" + elpriceafter.text() + "\n" + temp4 + "\n";
            }

            else
            {
                permanent1 ="\n" + temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

            }
            temptitlestore.add(temp1);
            mainlist.add(permanent1);
            tempurlstore.add(temp5);
            tempimageurl.add(temp6);

        }
    }
}
