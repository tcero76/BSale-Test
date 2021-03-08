package com.bsale.test.leonardo.controller;

import com.bsale.test.leonardo.model.Product;
import com.bsale.test.leonardo.payload.ResProducts;
import com.bsale.test.leonardo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    private List<ResProducts> findAll() {
        return productService.findAll().stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{name}/products")
    private List<ResProducts> findByName(@PathVariable("name") String name) {
        return productService.findByName(name).stream()
                .map(ResProducts::new)
                .collect(Collectors.toList());
    }

}
