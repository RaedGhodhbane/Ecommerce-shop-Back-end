package com.shop.ecommerce.services;

import com.shop.ecommerce.entities.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getProductCategoryList();

    ProductCategory createCategory(ProductCategory productCategory);

    ProductCategory updateCategory(Long id, ProductCategory productCategory);

    void deleteCategory(Long id);
}
