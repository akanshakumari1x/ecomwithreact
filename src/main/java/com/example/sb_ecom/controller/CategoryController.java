package com.example.sb_ecom.controller;

import com.example.sb_ecom.config.AppConstants;
import com.example.sb_ecom.model.Category;
import com.example.sb_ecom.payload.CategoryDTO;
import com.example.sb_ecom.payload.CategoryResponse;
import com.example.sb_ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name ="message", required = false) String message)
    //public ResponseEntity<String> echoMessage(@RequestParam(name ="message", defaultValue = "Hello world") String message)
    {
        return new ResponseEntity<>("Echoed message " + message, HttpStatus.OK);
    }

  //  @GetMapping("/api/public/categories")
    @RequestMapping(value = "/public/categories", method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse>getAllCategories(
            @RequestParam (name ="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam (name ="pageSize",  defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam (name ="sortBy",    defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam (name ="sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder)
    {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
   // @RequestMapping(value = "/public/categories", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO>createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO>deleteCategory(@PathVariable Long categoryId) {
        CategoryDTO deleteCategory = categoryService.deleteCategory(categoryId);
            return  new ResponseEntity<>(deleteCategory,HttpStatus.OK);
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO>updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId) {
        CategoryDTO savedCategory = categoryService.updateCategory(categoryDTO, categoryId);
        System.out.println("cat id "+ categoryId);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
        // return new ResponseEntity<>("Category updated with category id "+ categoryId  , HttpStatus.OK);
    }

}
