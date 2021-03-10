package com.bsale.test.leonardo.repository;

import com.bsale.test.leonardo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

//    Listado de productos completos con Categorías. Optimizada con Hibernate Statistics.
    @Query("select p,c from Product p left join p.category c")
    public List<Product> findAllWithCategory();

    //    Listado de productos filtrados por nombre con Categorías. Optimizada con Hibernate Statistics.
    @Query("select p,c from Product p left join p.category c where p.name like %:name%")
    public List<Product> findByNameContainsWithCategory(String name);

    @Query("select p,c from Product p left join p.category c where p.price>= :minPrice and p.price< :maxPrice")
    public List<Product> findByPriceWithCategory(Float minPrice, Float maxPrice);

    @Query("select p,c from Product p left join p.category c where p.name like %:name% and p.price>=:minPrice and p.price<:maxPrice")
    public List<Product> findByNameContainsAndPriceWithCategory(String name, Float minPrice, Float maxPrice);
}
