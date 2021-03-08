package com.bsale.test.leonardo.payload;

import com.bsale.test.leonardo.model.Product;

public class ResProducts {

    private final Float price;
    private final Integer discount;
    private final String url_image;
    private final String name;
    private final Integer idcategory;
    private final String category;
    private final Integer id;

    public ResProducts(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory().getName();
        this.idcategory = product.getCategory().getId();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.url_image = product.getUrl_image();
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

    public String getName() {
        return name;
    }

    public Integer getIdcategory() {
        return idcategory;
    }

    public String getCategory() {
        return category;
    }

    public Integer getId() {
        return id;
    }
}
