package com.bsale.test.leonardo.search;

import com.bsale.test.leonardo.model.Product;

public class NameSpecification implements Specification<Product> {

    private String name;

    public NameSpecification(String name) {
        this.name = name;
    }

    @Override
    public Boolean isSatisfied(Product item) {
        if (name=="") {
            return true;
        }
        return item.getName().contains(name);
    }
}
