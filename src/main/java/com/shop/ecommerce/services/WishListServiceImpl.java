package com.shop.ecommerce.services;

import com.shop.ecommerce.dto.request.WishListDto;
import com.shop.ecommerce.entities.Product;
import com.shop.ecommerce.entities.WishList;
import com.shop.ecommerce.repositories.WishListRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class WishListServiceImpl implements WishListService{

    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    ProductService productService;

    @Override
    public List<WishList> getAllProductByUserWishListId(Long id) {
        return wishListRepository.findAllWishListById(id);
    }

    @Override
    public void addProductToWishListByUser(WishListDto wishListDto) {
        // Fetch the product using the product ID from the DTO
        Optional<Product> productOptional = productService.getProductById(wishListDto.getProductId());
        // Check if the product is present
        if (productOptional.isPresent()) {
            // Extrait l'objet Product réel contenu dans l'Optional,
            // permettant de continuer à travailler avec cet objet.
            Product product = productOptional.get();
            log.info("Product found for userId -: " + wishListDto.getUserId());

            // Build the WishList object
            WishList wishListInstance = WishList.builder()
                    .userId(wishListDto.getUserId())
                    .product(product) // pass the actual product object, not
                    .build();
            // Save the WishList to the repository
            wishListRepository.save(wishListInstance);
            log.info("Product added to wishList successfully");
        } else {
            log.error("Product with ID " + wishListDto.getProductId() +
            " not found for userId " + wishListDto.getUserId());
            throw new RuntimeException("Product not found");
        }
    }


}
