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


    //    Servicio que entrega listado de productos completo.
    @Override
    @Transactional
    public List<ResProducts> findAll() {
        return productRepo.findAllWithCategory().stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }

    //    Servicio que entrega listado de productos filtrado por nombre.
    @Override
    @Transactional
    public List<ResProducts> findByName(String name) {
        return productRepo.findByNameContainsWithCategory(name).stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }

    //    Servicio que entrega listado de productos filtrado por categoría de precio.
    @Override
    @Transactional
    public List<ResProducts> findByPrice(Float minPrice, Float maxPrice) {
        return productRepo.findByPriceWithCategory(minPrice, maxPrice)
                .stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }

    //    Servicio que entrega listado de productos filtrado por nombre y categoría de precio.
    @Override
    @Transactional
    public List<ResProducts> findByNameAndByPrice(String name, Float minPrice, Float maxPrice) {
        return productRepo.findByNameContainsAndPriceWithCategory(name,minPrice,maxPrice)
                .stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }

    //    Servicio que entrega listado de productos filtrado por nombre y categoría de precio mínimo.
    @Override
    @Transactional
    public List<ResProducts> findByNameAndByMinPrice(String name, Float minPrice) {
        return productRepo.findByNameContainsAndMinPriceWithCategory(name,minPrice)
                .stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }

    //    Servicio que entrega listado de productos filtrado por categoría de precio mínimo.
    @Override
    @Transactional
    public List<ResProducts> findByMinPrice(Float minPrice) {
        return productRepo.findByMinPriceWithCategory(minPrice)
                .stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }
}
