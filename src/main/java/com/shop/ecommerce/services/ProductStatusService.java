package com.shop.ecommerce.services;

import com.shop.ecommerce.entities.ProductStatus;

import java.util.List;

public interface ProductStatusService {

    List<ProductStatus> getProductStatusList();
    ProductStatus saveProductStatus(ProductStatus productStatus);  // Add this
}
