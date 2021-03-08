package com.bsale.test.leonardo.controller;

import com.bsale.test.leonardo.payload.ResProducts;
import com.bsale.test.leonardo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    private ResponseEntity<List<ResProducts>> findAll() {
        List<ResProducts> res = productService.findAll();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{name}/products")
    private ResponseEntity<List<ResProducts>> findByName(@PathVariable("name") String name) {
        List<ResProducts> res =  productService.findByName(name);
        return ResponseEntity.ok(res);
    }

}
