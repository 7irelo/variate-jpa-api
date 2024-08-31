package com.variate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "category_id_seq")
    public Long id;
    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    public String description;
    @Column(name = "image_url")
    public String imageUrl;
    public List<Product> products;
}
