package com.bsale.test.leonardo.controller;

import com.bsale.test.leonardo.payload.ResProducts;
import com.bsale.test.leonardo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

//    Procesa requests a produtos filtrados por nombre.
    @GetMapping("/{name}/{price}/products")
    private ResponseEntity<List<ResProducts>> findByName(@PathVariable("name") String name,
                                                         @PathVariable("price") Integer selectPrice) {
        List<ResProducts> res =  productService.findAll();
        return ResponseEntity.ok(res);
    }

}
