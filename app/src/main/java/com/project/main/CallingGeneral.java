package com.project.main;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;

public class CallingGeneral {
    ArrayList<Product> products = new ArrayList<>(); //(title, price before, discounted price, discount, image link, product link,tag )
    String link;

    public void call(int sNo,String websiteUrl,String productClass, String tagForLink, String titleClass, String discountedPriceClass, String originalPriceClass, String productImageClass,String productDiscount, String productRatingCount, String rating) throws IOException{
        Document doc = Jsoup.connect(websiteUrl).get(); //JSoup Element to get HTML Data from the corresponding website
        Elements links = doc.getElementsByClass(productClass); //JSoup Element to get HTML Data from the corresponding Class/Section
        link = websiteUrl;

        for (Element link : links) {
            String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null, temp6 = null,temp7 = null, temp9 = null;
            float temp8 = 0;
            Elements eltitle, elproductimage;

            /*
            These are the fixed values, no need to change them. From here ---->
             */
            Elements elLink = link.getElementsByTag(tagForLink);

            if(sNo == 2){
                eltitle = link.getElementsByTag(titleClass);
            }else{
                eltitle = link.getElementsByClass(titleClass);
            }

            Elements elpricebefore = link.getElementsByClass(originalPriceClass);

            Elements elpriceafter = link.getElementsByClass(discountedPriceClass);

            if(sNo == 2 ||sNo == 3|| sNo == 4 ||sNo == 5){
                elproductimage = link.getElementsByTag(productImageClass);
            }
            else {
                elproductimage = link.getElementsByClass(productImageClass);
            }

                Elements discount = link.getElementsByClass(productDiscount);
                for (Element productdiscount : discount) {
                    if(productdiscount == null){
                        temp4 = "";
                    }
                    else {
                        temp4 = productdiscount.text();
                    }
                }

            switch(sNo){
                case 1:
                    temp7 = "Flipkart";
                    break;
                case 2:
                    temp7 = "Shopclues";
                    break;
                case 3:
                    temp7 = "Snapdeal";
                    break;
                case 4:
                    temp7 = "Paytm";
                    break;
                case 5:
                    temp7 = "Amazon";
                    break;
            }


            //product title loop
            for (Element titleOfProduct : eltitle) {
                if(titleOfProduct == null){
                    temp1 = "";
                }
                else{
                    temp1 = titleOfProduct.text();
                }
            }

            //product original price loop
            for (Element priceOfProductBefore : elpricebefore) {
                if(priceOfProductBefore == null){
                    temp2 = "";
                }
                else {
                        temp2 = priceOfProductBefore.text();
                     }
                }

            //product discounted price loop
            for (Element priceOfProductAfter : elpriceafter) {
                if (priceOfProductAfter == null) {
                    temp3 = "";
                } else {
                        temp3 = "₹" + priceOfProductAfter.text();
                }
            }

            //image loop
            if(sNo == 3){
                for(Element elimage : elproductimage){
                    temp6 = elimage.attr("srcset");
                    break;
                }
            }
            if(sNo == 2){
                for(Element elimage : elproductimage){
                    temp6 = elimage.attr("data-img");
                }
            }
            if(sNo == 4 || sNo == 5){
                for(Element elimage : elproductimage){
                    temp6 = elimage.attr("src");
                }
            }


            //produuct URL loop
            ArrayList<String> linkArray = new ArrayList<>();
            for (Element elementLink : elLink) {
                String MainLink = elementLink.attr("href");
                linkArray.add(MainLink);
            }

            //URL storing
            if(sNo == 1){
                for (int j = 0; j < 1; j++) {
                    temp5 = "https://www.flipkart.com" + linkArray.get(0);
                }
            }
            if(sNo == 2){
                for (int j = 0; j < 1; j++) {
                    temp5 = "https:" + linkArray.get(0);
                }
            }
            if(sNo == 3){
                temp5 = linkArray.get(0);
            }
            if(sNo == 4) {
                for (int j = 0; j < 1; j++) {
                    temp5 = "https://paytmmall.com" + linkArray.get(0);
                }
            }
            if(sNo == 5){
                for (int j = 0; j < 1; j++) {
                    temp5 = "https://www.amazon.in"+ linkArray.get(0);
                }
            }
            /*
            Till here. Everything is good.
             */

            if(sNo ==1){
                Elements ratings = link.getElementsByClass(rating);
                for(Element ratingtemp: ratings){
                    temp8 = Float.parseFloat(ratingtemp.text());
                    break;
                }

                Elements ratingCounts = link.getElementsByClass(productRatingCount);
                for(Element ratingCount : ratingCounts ){
                    temp9 = ratingCount.text();
                }
            }

            if(sNo == 2){
                Elements ratings = link.getElementsByAttribute(rating);
                for(Element ratingtemp : ratings) {
                    String a = ratingtemp.attr("style");
                    if (a.endsWith("x")) {
                        String percentage = a.substring(a.indexOf(":") + 1, a.indexOf("p"));
                        float f = Float.parseFloat(percentage);
                        temp8 = (f / 70) * 5;
                        break;
                    }else{
                        temp8 = 0;
                    }
                }
                temp9 = "";
            }

            if(sNo == 3){
                Elements ratings = link.getElementsByClass(rating);
                for(Element ratingtemp : ratings){
                    String a = ratingtemp.attr("style");
                    String percentage = a.substring(a.indexOf(":")+1,a.indexOf(".")+2);
                    float f = Float.parseFloat(percentage);
                    temp8 = (f/100)*5;
                }
                Elements ratingCounts = link.getElementsByClass(productRatingCount);
                for(Element ratingCount : ratingCounts ){
                    temp9 = ratingCount.text();
                }

            }

            if(sNo == 4){
                temp8 = 0;
                temp9 = "";
            }

            if(sNo == 5){
                Elements ratings = link.getElementsByClass(rating);

                for(Element ratingtemp: ratings){
                    temp8 = Float.parseFloat(ratingtemp.text().substring(0,3));
                }

                Elements ratingCounts = link.getElementsByClass(productRatingCount).attr("dir","auto");
                for(Element ratingCount : ratingCounts ){
                    temp9 = "("+ratingCount.text()+")";
                    break;
                }
                Elements discountamz = link.getElementsByAttribute("dir").attr("dir","auto");
                for(Element el : discountamz){
                    String a = el.text();
                    if(a.endsWith("%)")){
                        temp4 = a.substring(a.indexOf("(")+1,a.indexOf(")"));
                        break;
                    }
                }
            }

            //(title, price before, discounted price, discount, image link, product link,tag )
            products.add(new Product(temp1,temp2,temp3,temp4,temp6,temp5,temp7,temp8,temp9));

        }
    }
}
