package com.variate.repositories;

import com.variate.model.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> priceLessThan(float price);
}
