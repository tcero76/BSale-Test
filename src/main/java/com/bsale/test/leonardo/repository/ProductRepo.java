package com.bsale.test.leonardo.repository;

import com.bsale.test.leonardo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("select p,c from Product p left join p.category c")
    public List<Product> findAllWithCategory();

    @Query("select p,c from Product p left join p.category c where p.name like %:name%")
    public List<Product> findByNameContainsWithCategory(String name);
}
