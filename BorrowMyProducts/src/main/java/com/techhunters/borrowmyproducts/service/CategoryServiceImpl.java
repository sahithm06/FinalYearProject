package com.techhunters.borrowmyproducts.service;

import com.techhunters.borrowmyproducts.daorepository.CategoryRepository;
import com.techhunters.borrowmyproducts.dto.CategoryDTO;
import com.techhunters.borrowmyproducts.entity.Category;
import com.techhunters.borrowmyproducts.objectmappers.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.convertToEntity(categoryDTO);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        for (Category category : categories) {
            CategoryDTO categoryDTO = categoryMapper.convertToDTO(category);
            categoryDTOs.add(categoryDTO);
        }
        return categoryDTOs;
    }

    @Override
    public CategoryDTO findById(int id) {
        return categoryMapper.convertToDTO(categoryRepository.findById(id).get());
    }

    @Override
    public CategoryDTO findByCategoryName(String name) {
        return categoryMapper.convertToDTO(categoryRepository.findByCategoryName(name));
    }

    @Override
    public void delete(CategoryDTO categoryDTO) {
        categoryRepository.delete(categoryMapper.convertToEntity(categoryDTO));
    }
}
