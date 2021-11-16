package com.bsale.test.leonardo.repository;

import com.bsale.test.leonardo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
