package com.project.main;

public class CallingMain {
    public void callingmain(String searchtext){
        ThreadCall snap = new ThreadCall("https://www.snapdeal.com/search?keyword=" + searchtext + "&santizedKeyword=&catId=" +
                "&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&change" +
                "BackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy","Snapdeal" );
        ThreadCall shop = new ThreadCall("https://www.shopclues.com/search?q=" +searchtext +"&sc_z=2222&z=0&count=10", "ShopClues");
        ThreadCall pay = new ThreadCall("https://www.paytmmall.com/shop/search?q=" +searchtext+ "&from=organic&child_site_id=6","Paytm");
        ThreadCall flip = new ThreadCall("https://www.flipkart.com/search?q=" + searchtext+ "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off", "Flipkart");
//        snap.start();
//        shop.start();
        pay.start();
//        flip.start();
    }
}
