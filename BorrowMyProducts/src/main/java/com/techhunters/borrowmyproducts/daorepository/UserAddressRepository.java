package com.techhunters.borrowmyproducts.daorepository;

import com.techhunters.borrowmyproducts.entity.UserAddress;
import org.springframework.data.repository.CrudRepository;

public interface UserAddressRepository extends CrudRepository<UserAddress, Integer> {

}
