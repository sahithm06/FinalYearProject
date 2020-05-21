package com.techhunters.borrowmyproducts.objectmappers;

import com.techhunters.borrowmyproducts.dto.CategoryDTO;
import com.techhunters.borrowmyproducts.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Category convertToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    public CategoryDTO convertToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
