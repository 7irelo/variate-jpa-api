package com.variate.services;

import com.variate.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(Long id, ProductDto productDto);
    ProductDto patchProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
