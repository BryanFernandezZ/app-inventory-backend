package com.company.appinventory.app.service;

import com.company.appinventory.app.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<CategoryResponseRest> search();
}
