package com.bsale.test.leonardo.service;

import com.bsale.test.leonardo.payload.ResProducts;
import com.bsale.test.leonardo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;


    @Override
    public List<ResProducts> findAll() {
        return productRepo.findAllWithCategory().stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ResProducts> findByName(String name) {
        return productRepo.findByNameContainsWithCategory(name).stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ResProducts> findByPrice(Float minPrice, Float maxPrice) {
        return productRepo.findByPriceWithCategory(minPrice, maxPrice)
                .stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ResProducts> findByNameAndByPrice(String name, Float minPrice, Float maxPrice) {
        return productRepo.findByNameContainsAndPriceWithCategory(name,minPrice,maxPrice)
                .stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }
}
