package com.company.appinventory.app.controller;

import com.company.appinventory.app.response.CategoryResponseRest;
import com.company.appinventory.app.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/category")
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CategoryResponseRest> searchCategories() {
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }
}
