package com.bsale.test.leonardo.repository;

import com.bsale.test.leonardo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    //    Listado de productos completos con Categorías. Optimizada con Hibernate Statistics.
    @Query("select p,c from Product p left join p.category c")
    public List<Product> findAllWithCategory();

    //    Listado de productos filtrados por nombre con Categorías. Optimizada con Hibernate Statistics.
    @Query("select p,c from Product p left join p.category c where p.name like %:name%")
    public List<Product> findByNameContainsWithCategory(String name);

    //    Listado de productos filtrados por categoría de precios y con Categorías. Optimizada con Hibernate Statistics.
    @Query("select p,c from Product p left join p.category c where p.price>= :minPrice and p.price< :maxPrice")
    public List<Product> findByPriceWithCategory(Float minPrice, Float maxPrice);

    //    Listado de productos filtrados por nombre y precios, y, con Categorías. Optimizada con Hibernate Statistics.
    @Query("select p,c from Product p left join p.category c where p.name like %:name% and p.price>=:minPrice and p.price<:maxPrice")
    public List<Product> findByNameContainsAndPriceWithCategory(String name, Float minPrice, Float maxPrice);

    //    Listado de productos filtrados por nombre y mínimo precio, y, con Categorías. Optimizada con Hibernate Statistics.
    @Query("select p,c from Product p left join p.category c where p.name like %:name% and p.price>=:minPrice")
    public List<Product> findByNameContainsAndMinPriceWithCategory(String name, Float minPrice);

    //    Listado de productos filtrados por categoría de mínimo precio y con Categorías. Optimizada con Hibernate Statistics.
    @Query("select p,c from Product p left join p.category c where p.price>=:minPrice")
    public List<Product> findByMinPriceWithCategory(Float minPrice);
}
