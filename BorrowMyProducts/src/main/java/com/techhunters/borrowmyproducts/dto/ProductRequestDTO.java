package com.techhunters.borrowmyproducts.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequestDTO {

    private int productRequestId;

    private String status;

    private UserDTO user;

    private ProductDTO product;
}
