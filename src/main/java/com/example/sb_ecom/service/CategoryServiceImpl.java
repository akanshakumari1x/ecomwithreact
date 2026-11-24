package com.example.sb_ecom.service;

import com.example.sb_ecom.exception.APIException;
import com.example.sb_ecom.exception.ResourceNotFoundException;
import com.example.sb_ecom.model.Category;
import com.example.sb_ecom.payload.CategoryDTO;
import com.example.sb_ecom.payload.CategoryResponse;
import com.example.sb_ecom.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl  implements  CategoryService {

    private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty())
            throw new APIException("No category created till now.");

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class)
;        Category categoryFromDb = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if(categoryFromDb != null)
            throw new APIException("Category with the name "+ categoryDTO.getCategoryName() +"already exist!!");
       Category savedCategory =  categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category", "CategoryId",categoryId));
        System.out.println(" cat" +category);

        categoryRepository.delete(category); //remove from list
        return modelMapper.map(category,CategoryDTO.class);
    }


    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category category = modelMapper.map(categoryDTO, Category.class);

        categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "CategoryId", categoryId)
        );
        category.setCategoryId(categoryId); // Tells JPA to UPDATE the existing row
        Category savedCategory =  categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
