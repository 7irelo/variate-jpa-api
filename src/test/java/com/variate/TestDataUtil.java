package com.variate;

import com.variate.model.entities.Category;
import com.variate.model.entities.Product;

import java.util.ArrayList;

public final class TestDataUtil {
    private TestDataUtil() {

    }

    public static Category createTestCategoryA() {
        return Category.builder()
                .id(1L)
                .name("Electronics")
                .description("Gadgets and consoles")
                .imageUrl("electronics.jpg")
                .products(new ArrayList<>())
                .build();
    }


    public static Category createTestCategoryB() {
        return Category.builder()
                .id(2L)
                .name("Home and Kitchen")
                .description("Home appliances and kitchenware")
                .imageUrl("home_and_kitchen.jpg")
                .build();
    }

    public static Category createTestCategoryC() {
        return Category.builder()
                .id(3L)
                .name("Fashion and Beauty")
                .description("Fashion and beauty products")
                .imageUrl("fashion_and_beauty.jpg")
                .build();
    }

    public static Product createTestProductA(final Category category) {
        return Product.builder()
                .id(1L)
                .category(category)
                .name("Samsung A15 Blue")
                .description("A sleek smartphone with a powerful processor.")
                .price(5999F)
                .imageUrl("samsung_a15_blue.jpg")
                .onSale(true)
                .build();
    }

    public static Product createTestProductB(final Category category) {
        return Product.builder()
                .id(2L)
                .category(category)
                .name("Amazon Echo Show 10")
                .description("Smart display with Alexa and a rotating screen.")
                .price(8999F)
                .imageUrl("amazon_echo_show_10.jpg")
                .onSale(false)
                .build();
    }

    public static Product createTestProductC(final Category category) {
        return Product.builder()
                .id(3L)
                .category(category)
                .name("Bose SoundLink Revolve")
                .description("Portable Bluetooth speaker with 360-degree sound.")
                .price(2499F)
                .imageUrl("bose_soundlink_revolve.jpg")
                .onSale(true)
                .build();
    }
}
