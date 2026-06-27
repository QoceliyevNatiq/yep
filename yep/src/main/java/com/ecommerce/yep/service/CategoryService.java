package com.ecommerce.yep.service;


import com.ecommerce.yep.dto.CategoryRequest;
import com.ecommerce.yep.model.Category;

import java.util.List;


public interface CategoryService {
    void createCategory(CategoryRequest category);
    List<Category> findAll();
    Category getCategoryByName(String name);
    void updateCategory(Long id ,CategoryRequest category);
    void deleteCategory(Long id);

}
