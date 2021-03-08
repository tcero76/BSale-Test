package com.bsale.test.leonardo.payload;

import com.bsale.test.leonardo.model.Product;

public class ResProducts {

    private final Float price;
    private final Integer discount;
    private final String url_image;
    private final String name;
    private String category;
    private Integer id;

    public ResProducts(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory().getName();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.url_image = product.getUrl_image();
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public String getUrl_image() {
        return url_image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
