package com.techhunters.borrowmyproducts.service;

import com.techhunters.borrowmyproducts.daorepository.ProductRequestRepository;
import com.techhunters.borrowmyproducts.dto.ProductDTO;
import com.techhunters.borrowmyproducts.dto.ProductRequestDTO;
import com.techhunters.borrowmyproducts.entity.ProductRequest;
import com.techhunters.borrowmyproducts.objectmappers.ProductRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductRequestServiceImpl implements ProductRequestService {

    @Autowired
    private ProductRequestRepository productRequestRepository;

    @Autowired
    private ProductRequestMapper productRequestMapper;

    @Override
    public List<ProductRequestDTO> listAll() {
        List<ProductRequestDTO> productRequestDTOs = new ArrayList<>();
        List<ProductRequest> productRequests = (List<ProductRequest>) productRequestRepository.findAll();
        for (ProductRequest productRequest : productRequests) {
            ProductRequestDTO productRequestDTO = productRequestMapper.convertToDTO(productRequest);
            productRequestDTOs.add(productRequestDTO);
        }
        return productRequestDTOs;
    }

    @Override
    public ProductRequestDTO findById(int id) {
        ProductRequest productRequest = productRequestRepository.findById(id).get();
        return productRequestMapper.convertToDTO(productRequest);

    }

    @Override
    public void save(ProductRequestDTO productRequestDTO) {
        ProductRequest productRequest = productRequestMapper.convertToEntity(productRequestDTO);
        productRequestRepository.save(productRequest);

    }

    @Override
    public void delete(ProductRequestDTO productRequestDTO) {
        ProductRequest productRequest = productRequestMapper.convertToEntity(productRequestDTO);
        productRequestRepository.delete(productRequest);

    }

    @Override
    public List<ProductRequestDTO> listByProduct(int id) {
        List<ProductRequestDTO> allProductRequests = listAll();
        List<ProductRequestDTO> productRequests = new ArrayList<>();
        for (ProductRequestDTO productRequestDTO : allProductRequests) {
            if (productRequestDTO.getProduct().getProductId() == id) {
                productRequests.add(productRequestDTO);
            }
        }
        return productRequests;
    }

    @Override
    public List<ProductRequestDTO> listByUser(String username) {
        List<ProductRequestDTO> allProductRequests = listAll();
        List<ProductRequestDTO> productRequests = new ArrayList<>();
        for (ProductRequestDTO productRequestDTO : allProductRequests) {
            if (productRequestDTO.getUser().getEmail().equals(username)) {
                productRequests.add(productRequestDTO);
            }
        }
        return productRequests;
    }

    @Override
    public List<Integer> listByUserId(int id) {
        List<ProductRequestDTO> allProductRequests = listAll();
        List<Integer> productRequests = new ArrayList<>();
        for (ProductRequestDTO productRequestDTO : allProductRequests) {
            if (productRequestDTO.getUser().getUserId() == id) {
                productRequests.add(productRequestDTO.getProduct().getProductId());
            }
        }
        return productRequests;
    }

    @Override
    public List<ProductDTO> listProductsByUser(String name) {
        List<ProductRequestDTO> requests = listByUser(name);
        List<ProductDTO> reqProducts = new ArrayList<>();
        for (ProductRequestDTO req : requests) {
            reqProducts.add(req.getProduct());
        }
        return reqProducts;
    }

    @Override
    public List<ProductRequestDTO> listByProductPending(int id) {
        List<ProductRequestDTO> pending = listByProduct(id);
        List<ProductRequestDTO> result = new ArrayList<>();
        for (ProductRequestDTO req : pending) {
            if (req.getStatus().equals("pending")) {

                log.info("username:" + req.getUser().getEmail());

                result.add(req);
            }
        }
        return result;
    }

}