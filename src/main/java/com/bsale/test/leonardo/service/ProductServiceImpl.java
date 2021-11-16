package com.bsale.test.leonardo.service;

import com.bsale.test.leonardo.model.Product;
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
    public List<Product> findAll() {
        return productRepo.findAllWithCategory();
    }

    //    Servicio que entrega listado de productos filtrado por nombre.
    @Override
    @Transactional
    public List<Product> findByName(String name) {
        return productRepo.findByNameContainsWithCategory(name);
    }

    //    Servicio que entrega listado de productos filtrado por categoría de precio.
    @Override
    @Transactional
    public List<Product> findByPrice(float[] limites) {
        return productRepo.findByPriceWithCategory(limites[0], limites[1]);
    }

    //    Servicio que entrega listado de productos filtrado por nombre y categoría de precio.
    @Override
    @Transactional
    public List<Product> findByNameAndByPrice(String name, float[] limites) {
        return productRepo.findByNameContainsAndPriceWithCategory(name,limites[0],limites[1]);
    }

    //    Servicio que entrega listado de productos filtrado por nombre y categoría de precio mínimo.
    @Override
    @Transactional
    public List<Product> findByNameAndByMinPrice(String name, float minPrice) {
        return productRepo.findByNameContainsAndMinPriceWithCategory(name,minPrice);
    }

    //    Servicio que entrega listado de productos filtrado por categoría de precio mínimo.
    @Override
    @Transactional
    public List<Product> findByMinPrice(Float minPrice) {
        return productRepo.findByMinPriceWithCategory(minPrice);
    }
}
