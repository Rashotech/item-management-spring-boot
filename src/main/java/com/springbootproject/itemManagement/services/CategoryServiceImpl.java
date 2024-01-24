package com.springbootproject.itemManagement.services;

import com.springbootproject.itemManagement.models.Category;
import com.springbootproject.itemManagement.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(String name, String description) {
        Category category = new Category(name, description);
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getOneCategory(Long id) {
        Optional<Category> optionalItem = categoryRepository.findById(id);
        return optionalItem.orElse(null);
    }

    @Override
    public void updateCategory(Long id, Category category) {
        category.setId(id);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Integer getTotalCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.size();
    }
}
