package com.ecommerce.yep.controller;


import com.ecommerce.yep.dto.ApiResponse;
import com.ecommerce.yep.dto.CategoryRequest;
import com.ecommerce.yep.model.Category;
import com.ecommerce.yep.service.CategoryService;
import com.ecommerce.yep.util.SystemMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @DeleteMapping("{id}")
    public ApiResponse<String> delete(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ApiResponse.ok(SystemMessage.CATEGORY_SUCCES_DELETE);
    }

    @PutMapping("{id}")
    public ApiResponse<String> update(@PathVariable Long id, @RequestBody CategoryRequest request){
        categoryService.updateCategory(id, request);
        return ApiResponse.ok(SystemMessage.CATEGORY_SUCCES_UPDATE);
    }

    @PostMapping
    public ApiResponse<String> create(@RequestBody CategoryRequest request){
        categoryService.createCategory(request);
        return ApiResponse.ok(SystemMessage.CATEGORY_SUCCES_CREATED);
    }

    @GetMapping("/search")
    public ApiResponse<Category> getCategory(@RequestParam String name){
        Category category = categoryService.getCategoryByName(name);
        return ApiResponse.ok(SystemMessage.SUCCESS, category);
    }

    @GetMapping
    public ApiResponse<List<Category>> getAll(){
        List<Category> categories = categoryService.findAll();
        return ApiResponse.ok(SystemMessage.SUCCESS, categories);
    }

}
