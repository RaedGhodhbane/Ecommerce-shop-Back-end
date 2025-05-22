package com.shop.ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private String userid;
    private String productid;
    private String productName;
    private String productImg;
    private int productQuantity;
    private int productPrice;
}
