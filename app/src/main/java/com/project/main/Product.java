package com.project.main;

public class Product {
    //(title, price before, discounted price, discount, image link, product link,tag )
    String title, priceBefore, discountedPrice, discount, imageLink, productLink, tag, ratingCount;
    float rating;
    public Product(String title, String priceBefore, String discountedPrice, String discount, String imageLink, String productLink, String tag,float rating,String ratingCount){
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriceBefore() {
        return priceBefore;
    }

    public void setPriceBefore(String priceBefore) {
        this.priceBefore = priceBefore;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
