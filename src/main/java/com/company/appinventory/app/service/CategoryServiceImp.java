package com.company.appinventory.app.service;

import com.company.appinventory.app.model.Category;
import com.company.appinventory.app.repository.CategoryRepository;
import com.company.appinventory.app.response.CategoryResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private CategoryRepository repository;

    public CategoryServiceImp(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest response = new CategoryResponseRest();

        try {
            List<Category> categories = repository.findAll();
            response.getCategoryResponse().setCategories(categories);
            response.setMetadata("Successful response", "00", LocalDateTime.now());
        } catch (Exception e) {
            response.setMetadata("Failed response", "-1", LocalDateTime.now());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
