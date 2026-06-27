package com.ecommerce.yep.service;

import com.ecommerce.yep.dto.CategoryRequest;
import com.ecommerce.yep.dto.product.ProductRequest;
import com.ecommerce.yep.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse createProduct(ProductRequest response);
    ProductResponse getProductById(Long id);
    ProductResponse updateProduct(Long id, ProductRequest response);
    void deleteProduct(Long id);

    Page<ProductResponse> getAllProducts(Pageable pageable);
    Page<ProductResponse> getProductsByCategory(Long id, Pageable pageable);
    Page<ProductResponse> getProductsByName(String name, Pageable pageable);

}
