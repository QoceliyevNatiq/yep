package com.ecommerce.yep.mapper;


import com.ecommerce.yep.dto.product.ProductRequest;
import com.ecommerce.yep.dto.product.ProductResponse;
import com.ecommerce.yep.model.Category;
import com.ecommerce.yep.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request, Category category) {

        if (category == null) {
            return null;
        }

        Product product = new Product();
        product.setCategory(category);
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setActive(true);
        product.setImageUrl(request.imageUrl());
        product.setStock(request.stock());
        return product;
    }

    public void updateProductFromRequest(ProductRequest request, Category category, Product product) {
        if(request == null || product == null) {
            return;
        }
        if(request.name() != null && request.name().isEmpty()) {
            product.setName(request.name());

        }
        if(request.description() != null && !request.description().isEmpty()) {
            product.setDescription(request.description());
        }

        if(request.price() != null) {
            product.setPrice(request.price());
        }
        if(request.imageUrl() != null && !request.imageUrl().isEmpty()) {
            product.setImageUrl(request.imageUrl());
        }

        if(request.stock() != null) {
            product.setStock(request.stock());
        }

        if (category != null) {
            product.setCategory(category);
        }
    }

    public ProductResponse toResponse(Product product) {

        if (product == null) {
            return null;
        }

        Long categoryId = (product.getCategory() != null) ? product.getCategory().getId() : null;
        String categoryName = (product.getCategory() != null) ? product.getCategory().getName() : null;

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl(),
                categoryId,
                categoryName,
                !product.isActive(),
                product.getLastModifiedDate(),
                product.getCreatedDate()
        );


    }
}
