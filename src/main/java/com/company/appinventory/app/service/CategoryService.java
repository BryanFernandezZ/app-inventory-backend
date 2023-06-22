package com.company.appinventory.app.service;

import com.company.appinventory.app.model.Category;
import com.company.appinventory.app.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<CategoryResponseRest> search();
    ResponseEntity<CategoryResponseRest> searchById(Long id);
    ResponseEntity<CategoryResponseRest> saveCategory(Category category);
    ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id);
}
