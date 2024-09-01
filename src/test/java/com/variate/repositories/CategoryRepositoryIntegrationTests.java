package com.variate.repositories;

import com.variate.TestDataUtil;
import com.variate.model.entities.Category;
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
public class CategoryRepositoryIntegrationTests {

    private CategoryRepository underTest;


    @Autowired
    public CategoryRepositoryIntegrationTests(CategoryRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatCategoryCanBeCreatedAndRecalled() {
        Category category = TestDataUtil.createTestCategoryA();
        underTest.save(category);
        Optional<Category> result = underTest.findById(category.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(category);
    }

    @Test
    void testThatMultipleCategoriesCanBeCreatedAndRecalled() {
        Category categoryA = TestDataUtil.createTestCategoryA();
        underTest.save(categoryA);
        Category categoryB = TestDataUtil.createTestCategoryB();
        underTest.save(categoryB);
        Category categoryC = TestDataUtil.createTestCategoryC();
        underTest.save(categoryC);

        Iterable<Category> result =  underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(categoryA, categoryB, categoryC);
    }

    @Test
    void testThatCategoryCanBeUpdated() {
        Category category = TestDataUtil.createTestCategoryA();
        underTest.save(category);

        category.setName("UPDATED");
        underTest.save(category);

        Optional<Category> result = underTest.findById(category.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(category);
    }

    @Test
    void testThatCategoryCanDeleted() {
        Category category = TestDataUtil.createTestCategoryA();
        underTest.save(category);
        underTest.deleteById(category.getId());
        Optional<Category>result = underTest.findById(category.getId());
        assertThat(result).isEmpty();
    }
}
