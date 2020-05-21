package com.techhunters.borrowmyproducts.daorepository;

import com.techhunters.borrowmyproducts.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    User findByPhone(String phone);


}
