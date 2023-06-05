package com.company.appinventory.app.repository;

import com.company.appinventory.app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
