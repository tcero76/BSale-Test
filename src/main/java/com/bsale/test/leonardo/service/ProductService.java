package com.bsale.test.leonardo.service;

import com.bsale.test.leonardo.model.Product;

import java.util.Arrays;
import java.util.List;

public interface ProductService {

    List<Product> findAll();

    List<Product> findByName(String name);
}
