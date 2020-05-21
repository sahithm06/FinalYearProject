package com.techhunters.borrowmyproducts.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDTO {

    private int categoryId;

    private String categoryName;

    private String image;


}