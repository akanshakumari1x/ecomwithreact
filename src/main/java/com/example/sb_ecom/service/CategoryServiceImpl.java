package com.example.sb_ecom.service;

import com.example.sb_ecom.exception.APIException;
import com.example.sb_ecom.exception.ResourceNotFoundException;
import com.example.sb_ecom.model.Category;
import com.example.sb_ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl  implements  CategoryService {

    private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;
    private Long nextId = 1L ;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty())
            throw new APIException("No category created till now.");
        return categories;
    }


    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null)
            throw new APIException("Category with the name "+ category.getCategoryName() +"already exist!!");
       // category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }


    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category", "CategoryId",categoryId));
        System.out.println(" cat" +category);

        categoryRepository.delete(category); //remove from list
        return "Category with categoryId: " + categoryId + " deleted successfully";
    }


    @Override
    public Category updateCategory(Category category, Long categoryId) {

        categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "CategoryId", categoryId)
        );
        Category savedCategory;

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;

    }
}
