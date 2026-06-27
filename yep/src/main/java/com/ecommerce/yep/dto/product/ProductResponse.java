package com.ecommerce.yep.dto.product;

import com.ecommerce.yep.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(

         Long id,
         String name,
         String description,
         BigDecimal price,
         Integer stock,
         String categoryName,
         Long categoryId,
         String imageUrl,
         boolean active,

         @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
         LocalDateTime createdDate,

         @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
         LocalDateTime updateTime
) {
}
