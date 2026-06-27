package com.ecommerce.yep.service.impl;

import com.ecommerce.yep.dto.product.ProductRequest;
import com.ecommerce.yep.dto.product.ProductResponse;
import com.ecommerce.yep.exception.BaseException;
import com.ecommerce.yep.mapper.ProductMapper;
import com.ecommerce.yep.model.Category;
import com.ecommerce.yep.model.Product;
import com.ecommerce.yep.repo.CategoryRepo;
import com.ecommerce.yep.repo.ProductRepo;
import com.ecommerce.yep.service.ProductService;
import com.ecommerce.yep.util.SystemMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo repo;
    private final CategoryRepo categoryRepo;
    private final ProductMapper mapper;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepo.findById(request.categoryId())
                .orElseThrow(() -> new BaseException(SystemMessage.CATEGORY_NOT_EXISTS));

        Product product = mapper.toEntity(request,category);
        product = repo.save(product);
        return mapper.toResponse(product);

    }
    @Override
    public ProductResponse getProductById(Long id) {
        Product product = repo.findById(id)
                .orElseThrow(() ->new BaseException(SystemMessage.PRODUCT_NOT_FOUND));
        product = repo.save(product);
        return mapper.toResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = repo.findById(id)
                .orElseThrow(() ->new BaseException(SystemMessage.PRODUCT_NOT_FOUND));

        Category category = null;
        if(request.categoryId() != null){
            category = categoryRepo.findById(request.categoryId())
                            .orElseThrow(() ->new BaseException(SystemMessage.CATEGORY_NOT_EXISTS));
            mapper.updateProductFromRequest(request,category,product);
        }
        product = repo.save(product);
        return mapper.toResponse(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = repo.findById(id)
                .orElseThrow(() ->new BaseException(SystemMessage.PRODUCT_NOT_FOUND));

        product.setActive(false);
        repo.save(product);
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return repo.findAllByActiveTrue(pageable).map(mapper::toResponse);
    }

    @Override
    public Page<ProductResponse> getProductsByCategory(Long id, Pageable pageable) {
        if(!categoryRepo.existsById(id)){
            throw new BaseException(SystemMessage.CATEGORY_NOT_EXISTS);
        }
        return repo.findByCategoryIdAndActiveTrue(id,pageable).map(mapper::toResponse);
    }

    @Override
    public Page<ProductResponse> getProductsByName(String name, Pageable pageable) {

        return repo.findByNameContainingIgnoreCaseAndActiveTrue(name,pageable).map(mapper::toResponse);
    }
}
