package com.shop.ecommerce.services;

import com.shop.ecommerce.dto.request.CartDto;
import com.shop.ecommerce.dto.response.DtoCart;
import com.shop.ecommerce.entities.Cart;
import com.shop.ecommerce.entities.CartItems;
import com.shop.ecommerce.repositories.CartItemsRepository;
import com.shop.ecommerce.repositories.CartRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService{
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemsRepository cartItemsRepository;

    @Transactional
    @Override
    public String addItemsToCart(CartDto cartDto) {
        // Check if the user already has a cart
        Optional<Cart> optionalCart = cartRepository.findByUserId(cartDto.getUserid());
        Cart cart;
        CartItems cartItems;

        if (optionalCart.isPresent()) {
            cart = optionalCart.get();

            // Check if the product already exists in the user's cart
            Optional<CartItems> optionalCartItems = cartItemsRepository.
                    findByCartAndProductId(cart, Long.valueOf(cartDto.getProductid()));
            if (optionalCartItems.isPresent()) {
                // Increment the quantity and update
                // the price if the product exists in the cart
                cartItems = optionalCartItems.get();
                cartItems.setProductQuantity(cartItems.getProductQuantity() + cartDto.getProductQuantity());

                // Update price as needed
                // cartItems.setProductPrince(cartItems.getProductPrice() * 2);
                // Save cartItem
                cartItemsRepository.save(cartItems);
                log.info("Product quantity updated in the cart.");
            } else {
                cartItems = CartItems.builder()
                        .productImg(cartDto.getProductImg())
                        .productId(Long.valueOf(cartDto.getProductid()))
                        .productPrice(cartDto.getProductPrice())
                        .productName(cartDto.getProductName())
                        .productQuantity(cartDto.getProductQuantity())
                        .cart(cart)
                        .build();
                cartItemsRepository.save(cartItems);
                log.info("New product added to the cart.");
            }
        } else {
            // Create a new cart for the user if it doen't exist
            cart = Cart.builder()
                    .userId(cartDto.getUserid())
                    .productTotalPrice(cartDto.getProductPrice())
                    .productTotalQuantity(cartDto.getProductQuantity())
                    .build();
            cartItems = CartItems.builder()
                    .productImg(cartDto.getProductImg())
                    .productId(Long.valueOf(cartDto.getProductid()))
                    .productPrice(cartDto.getProductPrice())
                    .productName(cartDto.getProductName())
                    .productQuantity(cartDto.getProductQuantity())
                    .cart(cart)
                    .build();
            List<CartItems> cartItemsList = new ArrayList<>();
            cartItemsList.add(cartItems);
            cart.setCartItemsList(cartItemsList);
            cartRepository.save(cart);
            log.info("New cart created and product added");
        }
        return "Item added to cart successfully.";
    }

    public List<DtoCart> getCartItems(String userId) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            // Fetch items in the cart
            Optional<List<CartItems>> optionalCartItems = cartItemsRepository.
                    findByCart(cart);
            if (optionalCartItems.isPresent()) {
                List<CartItems> cartItems = optionalCartItems.get();

                // Convert CartItems to DtoCart for response
                return cartItems.stream()
                        .map(item -> new DtoCart(
                                item.getId(),
                                item.getProductName(),
                                item.getProductId().toString(),
                                item.getProductImg(),
                                item.getProductQuantity(),
                                item.getProductPrice()))
                        .collect(Collectors.toList());
            } else {
                log.warn("Not items found in the cart for userId:" + userId);
                return new ArrayList<>(); // Return empty list if no items
            }
        } else {
            log.error("Cart not found for userId: " + userId);
            return new ArrayList<>(); // Return empty list if no cart found
        }
    }

    @Transactional // Le tout ou rien ( exemple : distributeur )
    @Override
    public void deleteProduct(String productId, Long itemId) {
        cartItemsRepository.deleteByIdAndProductId(itemId, Long.valueOf(productId));
        log.info("Product with id " + productId + "removed from cart pour l'item " + itemId);
    }

}
