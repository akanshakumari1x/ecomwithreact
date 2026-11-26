package com.example.sb_ecom.controller;

import com.example.sb_ecom.model.Product;
import com.example.sb_ecom.payload.ProductDTO;
import com.example.sb_ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/admin/categories/{categoryId}/product}")
    public ResponseEntity<ProductDTO>addProduct(@RequestBody Product product,
                                                 @PathVariable Long categoryId){

       ProductDTO productDTO =  productService.addProduct(categoryId, product);
       return productDTO;
    }


}
