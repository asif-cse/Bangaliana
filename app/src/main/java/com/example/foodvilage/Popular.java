package com.example.foodvilage;

public class Popular {
    private String product_title, product_price, product_images;

    public Popular() {
    }

    public Popular(String product_title, String product_price, String product_images) {
        this.product_title = product_title;
        this.product_price = product_price;
        this.product_images = product_images;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_images() {
        return product_images;
    }

    public void setProduct_images(String product_images) {
        this.product_images = product_images;
    }
}
