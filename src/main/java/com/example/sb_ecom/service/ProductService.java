package com.example.sb_ecom.service;

import com.example.sb_ecom.model.Product;
import com.example.sb_ecom.payload.ProductDTO;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);
}
