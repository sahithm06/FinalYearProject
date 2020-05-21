package com.techhunters.borrowmyproducts.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {


    private int productId;

    private UserDTO user;

    private CategoryDTO category;

    private String productName;

    private String manualProductName;

    private String productDescription;

    private int productCost;

    private String productStatus;

    private int quantity;

    private String image;

    private ProductLocationDTO productLocation;

}
