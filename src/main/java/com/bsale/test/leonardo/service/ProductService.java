package com.bsale.test.leonardo.service;

import com.bsale.test.leonardo.model.Product;

import java.util.List;

public interface ProductService {

    //    Servicio que entrega listado de productos completo.
    public List<Product> findAll();

    //    Servicio que entrega listado de productos filtrado por nombre.
    public List<Product> findByName(String name);

    //    Servicio que entrega listado de productos filtrado por categoría de precio.
    public List<Product> findByPrice(float[] limites);

    //    Servicio que entrega listado de productos filtrado por nombre y categoría de precio.
    public List<Product> findByNameAndByPrice(String name, float[] limites);

    //    Servicio que entrega listado de productos filtrado por nombre y categoría de precio mínimo.
    public List<Product> findByNameAndByMinPrice(String name, float minPrice);

    //    Servicio que entrega listado de productos filtrado por categoría de precio mínimo.
    public List<Product> findByMinPrice(Float minPrice);
}
