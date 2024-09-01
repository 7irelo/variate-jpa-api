package com.variate.repositories;

import com.variate.TestDataUtil;
import com.variate.model.entities.Category;
import com.variate.model.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRepositoryIntegrationTests {

    private ProductRepository underTest;

    @Autowired
    public ProductRepositoryIntegrationTests(ProductRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    void testThatProductCanBeCreatedAndRecalled() {
        Category category = TestDataUtil.createTestCategoryA();
        Product product = TestDataUtil.createTestProductA(category);
        underTest.save(product);
        Optional<Product> result = underTest.findById(product.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(product);
    }

    @Test
    void testThatMultipleProductsCanBeCreatedAndRecalled() {
        Category category = TestDataUtil.createTestCategoryA();

        Product productA = TestDataUtil.createTestProductA(category);
        underTest.save(productA);

        Product productB = TestDataUtil.createTestProductB(category);
        underTest.save(productB);

        Product productC = TestDataUtil.createTestProductC(category);
        underTest.save(productC);

        Iterable<Product> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(productA, productB, productC);
    }

    @Test
    void testThatProductCanBeUpdated() {
        Category category = TestDataUtil.createTestCategoryA();

        Product product = TestDataUtil.createTestProductA(category);
        underTest.save(product);

        product.setName("UPDATED");
        underTest.save(product);

        Optional<Product> result = underTest.findById(product.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(product);
    }

    @Test
    void testThatProductCanBeDeleted() {
        Category category = TestDataUtil.createTestCategoryA();

        Product product = TestDataUtil.createTestProductA(category);
        underTest.save(product);

        underTest.deleteById(product.getId());
        Optional<Product> result = underTest.findById(product.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetProductsWithPriceLessThan() {
        Category category = TestDataUtil.createTestCategoryA();

        Product productA = TestDataUtil.createTestProductA(category);
        underTest.save(productA);
        Product productB = TestDataUtil.createTestProductB(category);
        underTest.save(productB);
        Product productC = TestDataUtil.createTestProductC(category);
        underTest.save(productC);

        Iterable<Product> result = underTest.priceLessThan(7000F);
        assertThat(result).containsExactly(productA, productC);
    }
}
