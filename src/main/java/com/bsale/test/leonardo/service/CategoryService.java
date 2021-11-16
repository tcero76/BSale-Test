package com.bsale.test.leonardo.service;

import com.bsale.test.leonardo.model.Category;
import com.bsale.test.leonardo.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Transactional
    public Category findById(Integer idcategory) {
        return categoryRepo.findById(idcategory)
                .orElseThrow(() -> new RuntimeException());
    }

    @Transactional
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }
}
