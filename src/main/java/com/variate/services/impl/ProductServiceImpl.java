package com.variate.services.impl;

import com.variate.mappers.impl.ProductMapper;
import com.variate.model.dto.ProductDto;
import com.variate.repositories.ProductRepository;
import com.variate.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        var product = productMapper.mapFrom(productDto);
        return productMapper.mapTo(productRepository.save(product));
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::mapTo)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        var product = productMapper.mapFrom(productDto);
        product.setId(id);
        return productMapper.mapTo(productRepository.save(product));
    }

    @Override
    public ProductDto patchProduct(Long id, ProductDto productDto) {
        var existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (productDto.getName() != null) {
            existingProduct.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            existingProduct.setDescription(productDto.getDescription());
        }
        if (productDto.getPrice() != null) {
            existingProduct.setPrice(productDto.getPrice());
        }
        if (productDto.getImageUrl() != null) {
            existingProduct.setImageUrl(productDto.getImageUrl());
        }
        return productMapper.mapTo(productRepository.save(existingProduct));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
