package com.project.main;

import java.util.concurrent.ThreadLocalRandom;

public class CallingMain {
    public void callingmain(String searchtext){
        ThreadCall snap = new ThreadCall("https://www.snapdeal.com/search?keyword=" + searchtext + "&santizedKeyword=&catId=" +
                "&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&change" +
                "BackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy","Snapdeal" );
        ThreadCall shop = new ThreadCall("https://www.shopclues.com/search?q=" +searchtext +"&sc_z=2222&z=0&count=10", "ShopClues");
        ThreadCall pay = new ThreadCall("https://www.paytmmall.com/shop/search?q=" +searchtext+ "&from=organic&child_site_id=6","Paytm");
        ThreadCall flip = new ThreadCall("https://www.flipkart.com/search?q=" + searchtext+ "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off", "Flipkart");
        ThreadCall amz = new ThreadCall("https://www.amazon.in/s?k="+searchtext+"&ref=nb_sb_noss_2","Amazon");
        snap.start();
        shop.start();
        pay.start();
//        flip.start();
        amz.start();
    }

    public void callingfashion(String searchtext){

        ThreadCall pay = new ThreadCall("https://www.paytmmall.com/shop/search?q=" +searchtext+ "&from=organic&child_site_id=6","Paytm");
        pay.start();

        ThreadCall snap = new ThreadCall("https://www.snapdeal.com/search?keyword=" + searchtext + "&santizedKeyword=&catId=" +
                "&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&change" +
                "BackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy","Snapdeal" );
        snap.start();

        ThreadCall shop = new ThreadCall("https://www.shopclues.com/search?q=" +searchtext +"&sc_z=2222&z=0&count=10", "ShopClues");
        shop.start();

        ThreadCall flip = new ThreadCall("https://www.flipkart.com/search?q=" + searchtext+ "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off", "Flipkart");
        flip.start();

        ThreadCall amz = new ThreadCall("https://www.amazon.in/s?k="+searchtext+"&ref=nb_sb_noss_2","Amazon");
        amz.start();

        ThreadCall ajio = new ThreadCall("https://www.ajio.com/search/?text=" + searchtext, "AJIO");
        ThreadCall myn = new ThreadCall("https://www.myntra.com/" + searchtext, "Myntra");

        ThreadCall koov = new ThreadCall("https://www.koovs.com/" + searchtext, "Koovs");
        koov.start();

        ThreadCall bew = new ThreadCall("https://www.bewakoof.com/search/" + searchtext + "?ga_q=" + searchtext, "Bewakoof");
        bew.start();
//        ajio.start();
//        myn.start();
    }

    public void callingmedicines(String searchtext){

    }
}
