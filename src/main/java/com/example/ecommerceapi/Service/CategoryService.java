package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.entities.Category;
import com.example.ecommerceapi.errorHandling.AlreadyExistException;
import com.example.ecommerceapi.errorHandling.EntityNotFoundException;
import com.example.ecommerceapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category) {
        if(categoryRepository.getCategoryBycategoryName(category.getCategoryName())!=null){
            throw new AlreadyExistException("Kateogri zaten mevcut");
        }
        var newCategory = new Category(category.getCategoryName(),category.getTags());
        return categoryRepository.save(newCategory);
    }
    public Category getById(Long id) {
        return categoryRepository.findById(id).get();
    }
    public Category getBycategoryName(String categoryName) {
        var category = categoryRepository.getCategoryBycategoryName(categoryName);
        if(category==null){
            throw new EntityNotFoundException("Kategori bulunamadı");
        }
        return  category;
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public void deleteById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new EntityNotFoundException("Bu ID'de Kategori bulunamadi");
        }
        categoryRepository.deleteById(id);
    }
    public Category update(Category category,Long id) {
        var updatedCategory = getById(id);
        updatedCategory.setCategoryName(category.getCategoryName());
        updatedCategory.setTags(category.getTags());
        updatedCategory.setUpdatedAt(LocalDateTime.now());
        return categoryRepository.save(updatedCategory);
    }
}