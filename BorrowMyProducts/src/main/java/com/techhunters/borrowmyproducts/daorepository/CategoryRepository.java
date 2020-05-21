package com.techhunters.borrowmyproducts.daorepository;

import com.techhunters.borrowmyproducts.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    public Category findByCategoryName(String name);

}
