package com.shop.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "product_quantity")
    private int productQuantity;

    @Column(name = "product_price")
    private int productPrice;

    @JsonIgnore  // Pour éviter les cycles
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartItems(Long id, String productName, Long productId, String productImg, int productQuantity, int productPrice) {
        this.id =id;
        this.productName = productName;
        this.productId = productId;
        this.productImg = productImg;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }
}
