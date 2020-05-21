package com.techhunters.borrowmyproducts.service;

import com.techhunters.borrowmyproducts.dto.ProductDTO;
import com.techhunters.borrowmyproducts.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> listAll();

    ProductDTO findById(int id);

    ProductDTO findByName(String name);

    void save(ProductDTO productDTO);

    void delete(ProductDTO productDTO);

    List<ProductDTO> listByCategoryName(String name);

    List<ProductDTO> listByUserId(int id);

    List<Product> listAvailableProductsByCategory(String catName, int id);

    List<ProductDTO> listAvailableProductsByCategoryLocation(String catName, int id);

    Double distance(double lat1, double lat2, double lon1, double lon2);
}
