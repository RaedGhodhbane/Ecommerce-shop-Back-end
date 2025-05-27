package com.shop.ecommerce.services;

import com.shop.ecommerce.entities.ProductStatus;
import com.shop.ecommerce.repositories.ProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStatusServiceImpl implements ProductStatusService {

    @Autowired
    private ProductStatusRepository productStatusRepository;

    @Override
    public List<ProductStatus> getProductStatusList() {
        return productStatusRepository.findAll();
    }

    @Override
    public ProductStatus saveProductStatus(ProductStatus productStatus) {
        return productStatusRepository.save(productStatus);
    }
}
