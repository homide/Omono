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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriceBefore(String priceBefore) {
        this.priceBefore = priceBefore;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public String getPriceBefore() {
        return priceBefore;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getProductLink() {
        return productLink;
    }

    public String getTag() {
        return tag;
    }
}
