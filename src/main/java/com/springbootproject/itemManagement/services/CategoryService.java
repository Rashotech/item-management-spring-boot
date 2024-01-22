package com.springbootproject.itemManagement.services;

import com.springbootproject.itemManagement.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public void createCategory(String name, String description);
    List<Category> getAllCategories();
    public Optional<Category> getOneCategory(Long categoryId);
    public void updateCategory(Long id, Category category);
    public void deleteCategoryById(Long id);
    public Category getCategoryByName(String categoryName);
}
