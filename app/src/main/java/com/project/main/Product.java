package com.project.main;

public class Product {
    //(title, price before, discounted price, discount, image link, product link,tag )
    String title, priceBefore, discountedPrice, discount, imageLink, productLink, tag;
    public Product(String title, String priceBefore, String discountedPrice, String discount, String imageLink, String productLink, String tag){
        this.title = title;
        this.priceBefore = priceBefore;
        this.discountedPrice = discountedPrice;
        this.discount = discount;
        this.imageLink = imageLink;
        this.productLink = productLink;
        this.tag = tag;
    }
}
