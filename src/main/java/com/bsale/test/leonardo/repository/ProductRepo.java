package com.bsale.test.leonardo.repository;

import com.bsale.test.leonardo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    List<Product> findByNameStartsWith(String name);
}
