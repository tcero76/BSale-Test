package com.bsale.test.leonardo.controller;

import com.bsale.test.leonardo.payload.ResProducts;
import com.bsale.test.leonardo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

//    Procesa requests a produtos filtrados por nombre y por categorías de precio.
    @GetMapping("/products")
    private ResponseEntity<List<ResProducts>> findByName(@RequestParam("name") String name,
                                                         @RequestParam("price") Integer selectPrice) {
        // Identifica los límites de cada categoría de precios.
        List<ResProducts> res = null;
        Float minPrice = 0f;
        Float maxPrice = 0f;
        switch (selectPrice) {
            case 1:
                maxPrice = 5000f;
                break;
            case 2:
                minPrice = 5000f;
                maxPrice = 10000f;
                break;
            case 3:
                minPrice = 10000f;
        }

        //Identifica el servicio que se debe utilizar para obtener los datos, por defecto extrae el listado completo.
        if(!name.equals("") && selectPrice.equals(0)) {
            res =  productService.findByName(name);
        } else if(!name.equals("") && selectPrice.equals(3)){
            res =  productService.findByNameAndByMinPrice(name,minPrice);
        } else if(!name.equals("") && (selectPrice.equals(1) || selectPrice.equals(2))){
            res =  productService.findByNameAndByPrice(name,minPrice,maxPrice);
        } else if(name.equals("") && selectPrice.equals(3)){
            res =  productService.findByMinPrice(minPrice);
        } else if(name.equals("") && (selectPrice.equals(1) || selectPrice.equals(2))){
            res =  productService.findByPrice(minPrice,maxPrice);
        } else {
            res =  productService.findAll();
        }
        return ResponseEntity.ok(res);
    }

}
