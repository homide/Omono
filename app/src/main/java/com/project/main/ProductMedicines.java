package com.project.main;

public class ProductMedicines {
    String title, priceBefore, discountedPrice, discount, imageLink, productLink, tag, ratingCount;
    float rating;
    public ProductMedicines(String title, String priceBefore, String discountedPrice, String discount, String imageLink, String productLink, String tag,float rating,String ratingCount){
        this.title = title;
        this.priceBefore = priceBefore;
        this.discountedPrice = discountedPrice;
        this.discount = discount;
        this.imageLink = imageLink;
        this.productLink = productLink;
        this.tag = tag;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }
}
