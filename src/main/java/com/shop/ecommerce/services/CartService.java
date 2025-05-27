package com.shop.ecommerce.services;

import com.shop.ecommerce.dto.request.CartDto;
import com.shop.ecommerce.dto.response.DtoCart;

import java.util.List;

public interface CartService {

    String addItemsToCart(CartDto cardDto);

    List<DtoCart> getCartItems(String userId);

    void deleteProduct(String productId, Long userId);
}
