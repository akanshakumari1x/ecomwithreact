package com.example.sb_ecom.service;


import com.example.sb_ecom.model.Product;
import com.example.sb_ecom.payload.ProductDTO;
import com.example.sb_ecom.repository.CategoryRepository;
import com.example.sb_ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {





        return null;
    }
}
