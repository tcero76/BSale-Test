package com.bsale.test.leonardo.service;

import com.bsale.test.leonardo.payload.ResProducts;

import java.util.List;

public interface ProductService {

    //    Servicio que entrega listado de productos completo.
    public List<ResProducts> findAll();

    //    Servicio que entrega listado de productos filtrado por nombre.
    public List<ResProducts> findByName(String name);

    //    Servicio que entrega listado de productos filtrado por categoría de precio.
    public List<ResProducts> findByPrice(Float minPrice, Float maxPrice);

    //    Servicio que entrega listado de productos filtrado por nombre y categoría de precio.
    public List<ResProducts> findByNameAndByPrice(String name, Float minPrice, Float maxPrice);
}
