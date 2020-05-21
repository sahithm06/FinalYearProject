package com.techhunters.borrowmyproducts.daorepository;

import com.techhunters.borrowmyproducts.entity.ProductRequest;
import org.springframework.data.repository.CrudRepository;

public interface ProductRequestRepository extends CrudRepository<ProductRequest, Integer> {

}
