package com.bsale.test.leonardo.service;


import com.bsale.test.leonardo.payload.ResProducts;

import java.util.List;

public interface ProductService {

    List<ResProducts> findAll();

    List<ResProducts> findByName(String name);
}
