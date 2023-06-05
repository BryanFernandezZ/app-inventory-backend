package com.company.appinventory.app.controller;

import com.company.appinventory.app.model.Category;
import com.company.appinventory.app.response.CategoryResponseRest;
import com.company.appinventory.app.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/category")
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<CategoryResponseRest> searchCategories() {
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }

    @RequestMapping(path = "/categories/{id}", method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response;
    }

    @RequestMapping(path = "/categories", method = RequestMethod.POST, consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<CategoryResponseRest> saveCategory(@RequestBody Category category) {
        ResponseEntity<CategoryResponseRest> response = service.saveCategory(category);
        return response;
    }
}
