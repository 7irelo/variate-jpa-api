package com.variate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.variate.model.entities.Category;
import com.variate.model.entities.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JacksonTests {

    @Test
    public void testThatObjectMapperCanCreateJsonFromJavaObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Category category = TestDataUtil.createTestCategoryA();
        Product product = TestDataUtil.createTestProductA(category);

        String result = objectMapper.writeValueAsString(product);
        assertThat(result).isEqualTo("{\"id\":1,\"category\":{\"id\":1,\"name\":\"Electronics\",\"description\":\"Gadgets and consoles\",\"products\":[],\"image\":\"electronics.jpg\"},\"name\":\"Samsung A15 Blue\",\"description\":\"A sleek smartphone with a powerful processor.\",\"price\":5999.0,\"manufactured\":null,\"onSale\":true,\"image\":\"samsung_a15_blue.jpg\"}");
    }

    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJson() throws JsonProcessingException {
        Category category = TestDataUtil.createTestCategoryA();
        Product product = TestDataUtil.createTestProductA(category);

        String json = "{\"id\":1,\"category\":{\"id\":1,\"name\":\"Electronics\",\"description\":\"Gadgets and consoles\",\"products\":[],\"image\":\"electronics.jpg\"},\"name\":\"Samsung A15 Blue\",\"description\":\"A sleek smartphone with a powerful processor.\",\"price\":5999.0,\"manufactured\":null,\"onSale\":true,\"image\":\"samsung_a15_blue.jpg\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Product result = objectMapper.readValue(json, Product.class);
        assertThat(result).isEqualTo(product);
    }
}
