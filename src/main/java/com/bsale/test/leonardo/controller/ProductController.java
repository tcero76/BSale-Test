package com.bsale.test.leonardo.controller;

import com.bsale.test.leonardo.model.Category;
import com.bsale.test.leonardo.model.Product;
import com.bsale.test.leonardo.payload.ResCategory;
import com.bsale.test.leonardo.payload.ResProducts;
import com.bsale.test.leonardo.search.*;
import com.bsale.test.leonardo.service.CategoryService;
import com.bsale.test.leonardo.service.ProductService;
import com.bsale.test.leonardo.util.Precios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private Filter<Product> filter;

    @Autowired
    private CategoryService categoryService;

    //    Procesa requests a produtos filtrados por nombre y por categorías de precio.
    @GetMapping("/products")
    private ResponseEntity<List<ResProducts>> findByName(@RequestParam("name") String name,
                                                         @RequestParam("price") Precios selectPrice,
                                                         @RequestParam("category") Integer idcategory) {
        // método destinado a entregar los productos filtrados según los criterios enviados en la request.
//        List<ResProducts> res = find(name,selectPrice);
        Category category = null;
        if(idcategory!=0) {
            category = categoryService.findById(idcategory);
        }

        List<ResProducts> res = filter.filter(productService.findAll(),
                        new AndSpecification<>(
                                new CatalogSpecification(category),
                                new AndSpecification<>(
                                        new PriceSpecification(selectPrice),
                                        new NameSpecification(name)
                                )
                            )
                        )
                .map(ResProducts::new).collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "/category", method = RequestMethod.OPTIONS)
    private ResponseEntity<List<ResCategory>> getCategory() {
        return ResponseEntity.ok(categoryService.findAll().stream()
                .map(ResCategory::new)
                .collect(Collectors.toList()));
    }

//    private List<Product> find(String name, Precios selectPrice) {
//        //Identifica el servicio que se debe utilizar para obtener los datos, por defecto extrae el listado completo.
//        if(!name.equals("") && selectPrice==Precios.todos) {
//            return productService.findByName(name);
//        } else if(!name.equals("") && selectPrice==Precios.alto){
//            return productService.findByNameAndByMinPrice(name,limites.get(selectPrice)[0]);
//        } else if(!name.equals("") && (selectPrice==Precios.bajo || selectPrice==Precios.medio)){
//            return  productService.findByNameAndByPrice(name,limites.get(selectPrice));
//        } else if(name.equals("") && selectPrice==Precios.alto){
//            return productService.findByMinPrice(limites.get(selectPrice)[0]);
//        } else if(name.equals("") && (selectPrice==Precios.bajo || selectPrice==Precios.medio)){
//            return productService.findByPrice(limites.get(selectPrice));
//        } else {
//            return productService.findAll();
//        }
//    }
}
