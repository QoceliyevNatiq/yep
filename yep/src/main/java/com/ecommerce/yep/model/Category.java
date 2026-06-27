package com.ecommerce.yep.model;


import com.ecommerce.yep.dto.CategoryRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "categories")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    private String imageUrl;


    public void updateCategoryDetails(CategoryRequest category) {
        if (category.name() != null && !category.name().isEmpty()) {
            this.name = category.name();
        }
        if (category.description() != null) {
            this.description = category.description();
        }
        if (category.imageUrl() != null) {
            if (category.imageUrl().isEmpty()) {
                this.imageUrl = null;
            } else {
                // Əgər normal link göndəribsə
                this.imageUrl = category.imageUrl();
            }
        }
    }
 }
