package com.bsale.test.leonardo.payload;

import com.bsale.test.leonardo.model.Category;

public class ResCategory {

    private String name;

    private Integer idCategory;

    public ResCategory(Category category) {
        this.name = category.getName();
        this.idCategory = category.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }
}
