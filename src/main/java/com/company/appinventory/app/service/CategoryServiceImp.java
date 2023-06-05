package com.company.appinventory.app.service;

import com.company.appinventory.app.model.Category;
import com.company.appinventory.app.repository.CategoryRepository;
import com.company.appinventory.app.response.CategoryResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();

        try {
            Optional<Category> category = repository.findById(id);
            if (category.isPresent()) {
                categoryList.add(category.get());
                response.getCategoryResponse().setCategories(categoryList);
                response.setMetadata("Successful response", "00", LocalDateTime.now());
            } else {
                response.getCategoryResponse().setCategories(null);
                response.setMetadata("Empty response", "-1", LocalDateTime.now());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Failed response", "-1", LocalDateTime.now());
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
