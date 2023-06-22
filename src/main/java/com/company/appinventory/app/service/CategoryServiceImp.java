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

    private final CategoryRepository repository;

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
            response.setMetadata("Successful response", "00", LocalDateTime.now(), "Categorias encontradas");
        } catch (Exception e) {
            response.setMetadata("Failed response", "-1", LocalDateTime.now(), "Error al buscar las categorias");
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
                response.setMetadata("Successful response", "00", LocalDateTime.now(), "Categoria encontrada");
            } else {
                response.getCategoryResponse().setCategories(null);
                response.setMetadata("Empty response", "-1", LocalDateTime.now(), "La categoria no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Failed response", "-1", LocalDateTime.now(), "Error al buscar la categoria");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> saveCategory(Category category) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();

        try {
            Category categorySaved = repository.save(category);

            if (categorySaved != null) {
                categoryList.add(categorySaved);
                response.getCategoryResponse().setCategories(categoryList);
                response.setMetadata("Successful response", "00", LocalDateTime.now(), "Categoria guardada");
            } else {
                response.getCategoryResponse().setCategories(null);
                response.setMetadata("Bad response", "-1", LocalDateTime.now(), "Categoria no guardada");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetadata("Failed response", "-1", LocalDateTime.now(), "Error al guardar categoria");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> categoryList = new ArrayList<>();

        try {
            Optional<Category> categorySearch = repository.findById(id);

            if (categorySearch.isPresent()) {
                //TODO: SE PROCEDERA A ACTUALIZAR LA CATEGORIA
                categorySearch.get().setName(category.getName());
                categorySearch.get().setDescription(category.getDescription());

                Category categoryToUpdate = repository.saveAndFlush(categorySearch.get());

                if (categoryToUpdate != null) {
                    categoryList.add(categoryToUpdate);
                    response.getCategoryResponse().setCategories(categoryList);
                    response.setMetadata("Successful response", "00", LocalDateTime.now(), "Categoria actualizada");
                } else {
                    response.setMetadata("Failed response", "-1", LocalDateTime.now(), "Categoria no actualizada");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.getCategoryResponse().setCategories(null);
                response.setMetadata("Empty response", "-1", LocalDateTime.now(), "Categoria no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("Failed response", "-1", LocalDateTime.now(), "Error al actualizar categoria");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
