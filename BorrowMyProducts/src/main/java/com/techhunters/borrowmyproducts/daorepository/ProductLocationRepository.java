package com.techhunters.borrowmyproducts.daorepository;

import com.techhunters.borrowmyproducts.entity.ProductLocation;
import org.springframework.data.repository.CrudRepository;

public interface ProductLocationRepository extends CrudRepository<ProductLocation, Integer> {

}
