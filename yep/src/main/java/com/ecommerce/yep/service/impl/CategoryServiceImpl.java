package com.ecommerce.yep.service.impl;

import com.ecommerce.yep.dto.CategoryRequest;
import com.ecommerce.yep.exception.BaseException;
import com.ecommerce.yep.model.Category;
import com.ecommerce.yep.repo.CategoryRepo;
import com.ecommerce.yep.service.CategoryService;
import com.ecommerce.yep.util.SystemMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo repo;
    @Override
    public void createCategory(CategoryRequest category) {
        if(repo.existsByName(category.name())){
            throw new BaseException(SystemMessage.CATEGORY_ALREADY_EXISTS);
        }

        Category newCategory = Category.builder()
                .name(category.name())
                .description(category.description())
                .imageUrl(category.imageUrl())
                .build();
        repo.save(newCategory);

    }

    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return repo.findByName(name)
                .orElseThrow(() -> new BaseException(SystemMessage.CATEGORY_NOT_EXISTS));
    }

    @Override
    public void updateCategory(Long id, CategoryRequest request) {
        Category category = repo.findById(request.id())
                        .orElseThrow(() -> new BaseException(SystemMessage.CATEGORY_NOT_EXISTS));

        category.updateCategoryDetails(request);

        repo.save(category);

    }

    @Override
    public void deleteCategory(Long id) {

        if(!repo.existsById(id)){
            throw new BaseException(SystemMessage.CATEGORY_NOT_EXISTS);
        }
        repo.deleteById(id);

    }

}
