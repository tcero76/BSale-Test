package com.bsale.test.leonardo.search;

import com.bsale.test.leonardo.model.Category;
import com.bsale.test.leonardo.model.Product;

public class CatalogSpecification implements Specification<Product> {

    private Category category;

    public CatalogSpecification(Category category) {
        this.category = category;
    }

    @Override
    public Boolean isSatisfied(Product item) {
        if (category==null)
            return true;
        return category.equals(item.getCategory());
    }
}
