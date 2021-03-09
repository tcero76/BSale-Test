package com.bsale.test.leonardo.service;

import com.bsale.test.leonardo.payload.ResProducts;

import java.util.List;

public interface ProductService {

    //    Servicio que entrega listado completo de productos.
    List<ResProducts> findAll();

    //    Servicio que entrega listado de productos filtrado por nombre.
    List<ResProducts> findByName(String name);
}
