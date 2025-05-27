package com.shop.ecommerce.services;

import com.shop.ecommerce.dto.request.WishListDto;
import com.shop.ecommerce.entities.WishList;

import java.util.List;

public interface WishListService {

    List<WishList> getAllProductByUserWishListId(Long id);

    void addProductToWishListByUser(WishListDto wishListDto);
}
