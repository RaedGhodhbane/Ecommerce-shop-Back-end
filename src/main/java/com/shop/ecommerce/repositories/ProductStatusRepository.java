package com.shop.ecommerce.repositories;

import com.shop.ecommerce.entities.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatusRepository extends JpaRepository<ProductStatus,Long> {
}
