package com.techhunters.borrowmyproducts.objectmappers;

import com.techhunters.borrowmyproducts.dto.ProductDTO;
import com.techhunters.borrowmyproducts.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Product convertToEntity(ProductDTO productDTO) {

        return modelMapper.map(productDTO, Product.class);
    }

    public ProductDTO convertToDTO(Product product) {

        return modelMapper.map(product, ProductDTO.class);
    }

}
