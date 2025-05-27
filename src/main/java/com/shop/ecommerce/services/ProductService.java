package com.shop.ecommerce.services;

import com.shop.ecommerce.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> getProductByStatusId(Long id, int page, int size);
    List<Product> getProductByStatusIdLimit(Long id);
    Optional<Product> getProductById(Long id);
    Page<Product> searchProductByName(String name, int page, int size);
    List<Product> getRandomProductByCategoryIdLimit(Long id);

    //Add the createProduct method
    Product createProduct(Product product);
}
