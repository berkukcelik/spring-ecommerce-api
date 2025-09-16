package com.example.ecommerceapi.Controller;

import com.example.ecommerceapi.Service.CategoryService;
import com.example.ecommerceapi.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
    }
    @PostMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.update(category, id), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}