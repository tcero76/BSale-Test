package com.bsale.test.leonardo.service;

import com.bsale.test.leonardo.model.Product;
import com.bsale.test.leonardo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepo.findByNameStartsWith(name);
    }
}
