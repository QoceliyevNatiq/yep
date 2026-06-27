package com.ecommerce.yep.controller;

import com.ecommerce.yep.dto.ApiResponse;
import com.ecommerce.yep.dto.product.ProductRequest;
import com.ecommerce.yep.dto.product.ProductResponse;
import com.ecommerce.yep.service.ProductService;
import com.ecommerce.yep.util.SystemMessage;
import jakarta.servlet.ServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@Valid  @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.createProduct(productRequest);
        return ApiResponse.ok(SystemMessage.SUCCESS,response);
    }

    @GetMapping("{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse response = productService.getProductById(id);
        return ApiResponse.ok(SystemMessage.SUCCESS,response);
    }

    @PutMapping("{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request){
        ProductResponse response = productService.updateProduct(id, request);
        return ApiResponse.ok(SystemMessage.SUCCESS,response);
    }

    @DeleteMapping("{id}")
    public ApiResponse<ProductResponse> deleteProductById(@PathVariable Long id){
        productService.deleteProduct(id);
        return ApiResponse.ok(SystemMessage.SUCCESS,null);
    }

    @GetMapping
    public ApiResponse<Page<ProductResponse>> getAllProducts(
            @PageableDefault(size = 10, sort = "id") Pageable pageable){

        Page<ProductResponse> productResponses = productService.getAllProducts(pageable);
        return ApiResponse.ok(SystemMessage.SUCCESS,productResponses);
    }

    @GetMapping("/category/{categoryId}")
    public ApiResponse<Page<ProductResponse>> getProductsByCategory(@PathVariable Long categoryId, @PageableDefault(size = 10) Pageable pageable){
        Page<ProductResponse> productResponses = productService.getProductsByCategory(categoryId, pageable);
        return ApiResponse.ok(SystemMessage.SUCCESS,productResponses);
    }

    @GetMapping("/search")
    public ApiResponse<Page<ProductResponse>> getProductByName(@RequestParam String name, @PageableDefault(size = 10) Pageable pageable){

        Page<ProductResponse> responses = productService.getProductsByName(name,pageable);
        return ApiResponse.ok(SystemMessage.SUCCESS,responses);
    }



}
